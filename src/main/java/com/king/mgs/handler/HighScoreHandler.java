package com.king.mgs.handler;

import com.king.mgs.service.UserScoreService;
import com.king.mgs.service.impl.UserScoreServiceImpl;
import com.sun.net.httpserver.HttpExchange;

import java.util.logging.Logger;
import java.util.regex.Pattern;

public class HighScoreHandler extends BaseRequestHandler {
    private static final Logger log = Logger.getLogger(HighScoreHandler.class.getName());
    public static final Pattern pattern = Pattern.compile("/(\\d+)/highscorelist$");
    private final UserScoreService scoreService;

    public HighScoreHandler() {
        log.info("creating object HighScoreHandler");
        scoreService = UserScoreServiceImpl.getInstance();
    }

    @Override
    void handleGet(HttpExchange httpExchange) {
        int level = getPathVariable(httpExchange, pattern, 1);
        String scores = scoreService.getHighScore(level);
        sendRespond(httpExchange, HttpStatus.OK.code, scores);
    }

    @Override
    void handlePost(HttpExchange httpExchange) {
        sendRespond(httpExchange, HttpStatus.METHOD_NOT_ALLOWED.code, HttpStatus.METHOD_NOT_ALLOWED.message);
    }
}
