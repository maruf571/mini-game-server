package com.king.mgs.service;

import com.king.mgs.model.Session;

public interface SessionService {

    Session create(int userId);

    Session getSessionByKey(String sessionKey);

    boolean valid(String sessionKey);
}
