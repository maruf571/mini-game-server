package com.king.mgs.service.impl;

import com.king.mgs.model.Session;
import com.king.mgs.service.SessionService;


import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class SessionServiceImpl implements SessionService {
    private static final Logger log = Logger.getLogger(SessionServiceImpl.class.getName());
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private static final int TEN_MIS_IN_MS = 10 * 60 * 1000;
    private static final Random random = new Random();

    private static SessionServiceImpl instance;

    private SessionServiceImpl() {
    }

    public static SessionServiceImpl getInstance() {
        if(instance == null) {
            instance = new SessionServiceImpl();
        }
        return instance;
    }

    @Override
    public Session create(int userId) {
        log.info("creating session");
        String sessionKey = getRandom();
        Session session = new Session(sessionKey, System.currentTimeMillis(), userId);
        sessionMap.put(sessionKey, session);
        return session;
    }

    @Override
    public Session getSessionByKey(String sessionKey) {
        return sessionMap.get(sessionKey);
    }

    @Override
    public boolean valid(String sessionKey) {
        Session session = sessionMap.get(sessionKey);
        if(session == null) {
            return false;
        }
        return System.currentTimeMillis() - session.getTimestamp() <= TEN_MIS_IN_MS;
    }

     private static String getRandom() {
        return random.ints(65, 91)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
