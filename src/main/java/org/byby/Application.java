package org.byby;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.core.Vertx;
import org.byby.manager.MessageProcessVertical;
import org.byby.manager.WebSocketManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class Application {
    private static final Logger LOG = Logger.getLogger(Application.class);

    public void init(@Observes StartupEvent startupEvent,
                     Vertx vertx,
                     MessageProcessVertical vertical,
                     WebSocketManager webSocketManager) {
        LOG.info(">>> Init");
        vertx.deployVerticle(vertical).await().indefinitely();
        webSocketManager.init();
        LOG.info(">>> Main Init Ready");
    }






}
