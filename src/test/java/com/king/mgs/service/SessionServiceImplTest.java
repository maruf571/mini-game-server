package com.king.mgs.service;

import com.king.mgs.model.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SessionServiceImplTest {

    SessionServiceImpl sessionServiceImpl = SessionServiceImpl.getInstance();

    @Test
    public void should_create_session() {
        Session session = sessionServiceImpl.create(123);
        assertEquals(123, session.getUserId());
        assertNotNull(session.getSessionId());
        assertEquals(10, session.getSessionId().length());
    }


    @Test
    public void should_get_session() {
        Session newSession = sessionServiceImpl.create(123);
        Session session = sessionServiceImpl.getSessionByKey(newSession.getSessionId());
        assertEquals(newSession, session);
    }

    @Test
    public void should_validate_session() {
        Session newSession = sessionServiceImpl.create(123);
        assertTrue(sessionServiceImpl.valid(newSession.getSessionId()));
    }

}
