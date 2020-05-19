package com.marufh.mgs.handler;

import com.marufh.mgs.model.Session;
import com.marufh.mgs.service.impl.SessionServiceImpl;
import com.sun.net.httpserver.HttpExchange;

import java.util.logging.Logger;
import java.util.regex.Pattern;

public class LoginHandler extends BaseRequestHandler {

    private static final Logger log = Logger.getLogger(LoginHandler.class.getName());
    public static final Pattern pattern = Pattern.compile("/(\\d+)/login$");
    private final SessionServiceImpl sessionServiceImpl;

    public LoginHandler() {
        log.info("creating object LoginHandler ");
        this.sessionServiceImpl = SessionServiceImpl.getInstance();
    }

    @Override
    public void handleGet(HttpExchange httpExchange) {
        log.info("handling login request");
        int userId = getPathVariable(httpExchange, pattern, 1);
        Session session = sessionServiceImpl.create(userId);
        sendRespond(httpExchange, HttpStatus.OK.code, session.getSessionId());
    }
}
