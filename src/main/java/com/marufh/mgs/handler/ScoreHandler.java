package com.marufh.mgs.handler;

import com.marufh.mgs.model.Session;
import com.marufh.mgs.service.SessionService;
import com.marufh.mgs.service.impl.SessionServiceImpl;
import com.marufh.mgs.service.UserScoreService;
import com.marufh.mgs.service.impl.UserScoreServiceImpl;
import com.sun.net.httpserver.HttpExchange;

import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ScoreHandler extends BaseRequestHandler {
    private static final Logger log = Logger.getLogger(ScoreHandler.class.getName());
    public static final Pattern pattern = Pattern.compile("/(\\d+)/score$");
    private final UserScoreService userScoreService;
    private final SessionService sessionService;

    public ScoreHandler() {
        log.info("creating object ScoreHandler");
        this.userScoreService = UserScoreServiceImpl.getInstance();
        this.sessionService = SessionServiceImpl.getInstance();
    }


    @Override
    public void handlePost(HttpExchange httpExchange) {
        log.info("handling score request");
        int level = getPathVariable(httpExchange, pattern, 1);
        String sessionKey =  getQueryMap(httpExchange.getRequestURI().getQuery()).get("sessionkey");
        int score = readBodyAsInt(httpExchange);

        Session session = sessionService.getSessionByKey(sessionKey);
        if(session != null && sessionService.valid(sessionKey)) {
            userScoreService.createScore(level, score, session.getUserId());
            sendRespond(httpExchange, HttpStatus.OK.code,"");
        } else {
            sendRespond(httpExchange, HttpStatus.FORBIDDEN.code,HttpStatus.FORBIDDEN.message);
        }
    }
}
