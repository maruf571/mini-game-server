package com.marufh.mgs;

import com.marufh.mgs.handler.DispatchHandler;
import com.marufh.mgs.handler.RequestRouter;
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
        if (null == thread) {
            log.info("starting the server...");
            executorService = Executors.newFixedThreadPool(20);
            this.thread = new Thread(this);
            this.thread.start();
        } else {
            log.warning("server is already running");
        }

    }

    public void stop() {
        if(null != thread) {
            log.info("shutting down the server...");
            server.stop(1);
            executorService.shutdown();
            executorService = null;
        } else {
            log.warning("server is not running");
        }
    }


}
