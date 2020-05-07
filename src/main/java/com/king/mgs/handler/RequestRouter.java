package com.king.mgs.handler;


import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RequestRouter {
    private static final Logger log = Logger.getLogger(RequestRouter.class.getName());
    private static final Map<Pattern, BaseRequestHandler> map = new HashMap<>();

    public RequestRouter() {
        log.info("creating object RequestRouter");
        map.put(LoginHandler.pattern, new LoginHandler());
        map.put(HighScoreHandler.pattern, new HighScoreHandler());
        map.put(ScoreHandler.pattern, new ScoreHandler());
    }

    public BaseRequestHandler handleRequest(HttpExchange httpExchange) {
        String url = httpExchange.getRequestURI().getPath();
        log.info("request router is getting handler for url: " + url);

        Matcher matcher;
        for (Map.Entry<Pattern, BaseRequestHandler> entry: map.entrySet()) {
            matcher = entry.getKey().matcher(url);
            if(matcher.find()) {
                return entry.getValue();
            }
        }
        return null;
    }
}
