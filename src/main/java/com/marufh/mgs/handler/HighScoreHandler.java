package com.marufh.mgs.handler;

import com.marufh.mgs.service.UserScoreService;
import com.marufh.mgs.service.impl.UserScoreServiceImpl;
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
    public void handleGet(HttpExchange httpExchange) {
        int level = getPathVariable(httpExchange, pattern, 1);
        String scores = scoreService.getHighScore(level);
        sendRespond(httpExchange, HttpStatus.OK.code, scores);
    }

}
