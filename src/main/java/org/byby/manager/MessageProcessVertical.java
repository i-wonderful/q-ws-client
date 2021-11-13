package org.byby.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import org.byby.model.Data;
import org.byby.model.Item;
import org.byby.model.PriceItem;
import org.byby.model.Result;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Обработчик сообщения,
 * (конвертациф в json, фильтры, сотрировка, и тд)
 */
@ApplicationScoped
public class MessageProcessVertical extends AbstractVerticle {
    private static final Logger LOGGER = Logger.getLogger(MessageProcessVertical.class);

    @Override
    public Uni<Void> asyncStart() {
        System.out.println(">> Start vertical");
        return vertx.eventBus().consumer("messages")
                .handler(objectMessage -> {
                    LOGGER.debug(">>> ASYNC HANDLER " + objectMessage.body());
                    mapJson(objectMessage.body().toString())
                            .map(Item::getData)
                            .map(this::filter10Prices)
                            .ifPresent(result -> {
                                vertx.eventBus().publish("ticks", toJson(result));
                            });
                })
                .completionHandler();
    }

    private Result filter10Prices(Data data) {
        double[][] asks = data.getAsks();
        double[][] bids = data.getBids();
        List<PriceItem> lowestAsks = Arrays.asList(asks).stream()
                .map(values -> new PriceItem(values[0], values[1]))
                .sorted(Comparator.comparing(PriceItem::getPrice))
                .limit(10)
                .collect(Collectors.toList());

        List<PriceItem> biggestBid = Arrays.asList(bids).stream()
                .map(values -> new PriceItem(values[0], values[1]))
                .sorted(Comparator.comparing(PriceItem::getPrice).reversed())
                .limit(10)
                .collect(Collectors.toList());

        return new Result(lowestAsks, biggestBid);
    }

    private Optional<Item> mapJson(String json) {
        try {
            Item item = new ObjectMapper().readValue(json, Item.class);
            return Optional.of(item);
        } catch (JsonProcessingException e) {
            LOGGER.error(">>> Error parse " + json, e);
            return Optional.empty();
        }
    }

    private String toJson(Result item) {
        try {
            return new ObjectMapper().writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}