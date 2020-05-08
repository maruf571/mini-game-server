package com.marufh.mgs.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.logging.Logger;

public class DispatchHandler implements HttpHandler {

    private static final Logger log = Logger.getLogger(DispatchHandler.class.getName());
    private final RequestRouter requestRouter;

    public DispatchHandler(RequestRouter requestRouter) {
        log.info("creating object DispatchHandler");
        this.requestRouter = requestRouter;
    }

    /**
     * This method forward request to the RequestRouter
     * to find handler according to url pattern.
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        log.info("dispatcher handler handle the request");

        BaseRequestHandler httpHandler = requestRouter.handleRequest(httpExchange);
        if(httpHandler != null) {
            switch (httpExchange.getRequestMethod()) {
                case HttpMethod.GET:
                    httpHandler.handleGet(httpExchange);
                    break;
                case HttpMethod.POST:
                    httpHandler.handlePost(httpExchange);
                    break;
                default:
                    sendResponse(httpExchange, HttpStatus.NOT_IMPLEMENTED.code, HttpStatus.NOT_IMPLEMENTED.message);
            }
        } else {
            sendResponse(httpExchange, HttpStatus.NOT_FOUND.code, HttpStatus.NOT_FOUND.message);
        }
    }

    private void sendResponse(HttpExchange httpExchange, int  httpStatus, String message) throws IOException {
        int contentLength = message.length();
        httpExchange.sendResponseHeaders(httpStatus, contentLength);
        httpExchange.getResponseBody().write(message.getBytes());
        httpExchange.close();
    }

}
