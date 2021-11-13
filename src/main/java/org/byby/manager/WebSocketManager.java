package org.byby.manager;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;

@ApplicationScoped
public class WebSocketManager {
    private static final Logger LOG = Logger.getLogger(WebSocketManager.class);
    private static final int MAX_CONNECTION_ATTEMPTS = 3;

    @ConfigProperty(name = "ws.url")
    private String wsUrl;

    @ConfigProperty(name = "subscribe.message")
    private String subscribeMessage;

    Session session;

    public void init() {
        int countConnect = 0;
        boolean success;
        while (!(success = connectWebSocket()) && countConnect++ < MAX_CONNECTION_ATTEMPTS) ;
        if (!success) {
            LOG.error(">>> Error connect websocket. Max Attempts " + MAX_CONNECTION_ATTEMPTS);
            return;
        }

        subscribe(session);
    }

    public boolean connectWebSocket() {
        LOG.info(">>> Connect WebSocket ");
        URI uri = URI.create(wsUrl);
        try {
            session = ContainerProvider.getWebSocketContainer().connectToServer(WebSocketListener.class, uri);
        } catch (Exception e) {
            LOG.error(">>> Error create socket connection");
            return false;
        }
        return session.isOpen();
    }

    public void subscribe(Session session) {
        session.getAsyncRemote().sendText(subscribeMessage);
    }

    @PreDestroy
    public void preDestroy() throws IOException {
        LOG.info(">>> PreDestroy");
        session.close();
    }
}
