package com.marufh.mgs.service;

import com.marufh.mgs.model.Session;

public interface SessionService {

    Session create(int userId);

    Session getSessionByKey(String sessionKey);

    boolean valid(String sessionKey);
}
