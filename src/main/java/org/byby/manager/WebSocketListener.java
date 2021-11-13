package org.byby.manager;

import io.vertx.mutiny.core.eventbus.EventBus;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.websocket.*;

@ClientEndpoint
public class WebSocketListener {
    private static final Logger LOG = Logger.getLogger(WebSocketListener.class);

    @Inject
    EventBus eventBus;

    @OnOpen
    public void open(Session session) {
        LOG.info(">>> Open session WS");
    }

    @OnMessage
    void message(String msg) {
        eventBus.publish("messages", msg);
    }
    @OnClose
    public void close(Session session) {
        LOG.info(">>> Close session WS");
    }
}
