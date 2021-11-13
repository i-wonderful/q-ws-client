package org.byby.manager;

import io.vertx.core.Vertx;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class RouteInit {
    private static final Logger LOG = Logger.getLogger(RouteInit.class);
    @Inject
    Vertx vertx;

    public void init(@Observes Router router) {
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        sockJSHandler.bridge(new SockJSBridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddress("ticks")));
        router.route("/eventbus/*").handler(sockJSHandler);
        LOG.info(">>> RouteInit Ready");
    }
}
