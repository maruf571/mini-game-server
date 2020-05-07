package com.king.mgs;

import com.king.mgs.handler.DispatchHandler;
import com.king.mgs.handler.RequestRouter;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MiniGameServer implements Runnable {

    private static final int HTTP_PORT = 8000;
    private static final String HTTP_HOST = "localhost";
    private static final Logger log = Logger.getLogger(MiniGameServer.class.getName());

    private Thread thread;
    private HttpServer server;
    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    @Override
    public void run() {
        try {
            server = HttpServer.create(new InetSocketAddress(HTTP_HOST, HTTP_PORT), 0);
            server.createContext("/", new DispatchHandler(new RequestRouter()));
            server.setExecutor(executorService);
            server.start();
            log.info("server is running on port " + HTTP_PORT);
        } catch (IOException exception) {
            log.warning("server is failed to start");
        }
    }

    public void start() {
        log.info("starting the server...");
        if (null == thread) {
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void stop() {
        log.info("shutting down the server...");
        server.stop(1);
        executorService.shutdown();
        executorService = null;
    }


}
