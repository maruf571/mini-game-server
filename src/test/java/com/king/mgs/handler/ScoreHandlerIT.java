package com.king.mgs.handler;

import com.king.mgs.MiniGameServer;
import com.king.mgs.model.Session;
import com.king.mgs.service.SessionService;
import com.king.mgs.service.SessionServiceImpl;
import com.king.mgs.service.UserScoreService;
import com.king.mgs.service.UserScoreServiceImpl;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ScoreHandlerIT {

    static DefaultHttpClient httpClient;
    static MiniGameServer mgs = new MiniGameServer();
    static SessionService sessionService;
    static UserScoreService userScoreService;

    @BeforeAll
    public static void init() {
        mgs.start();
        httpClient = new DefaultHttpClient();
        sessionService = SessionServiceImpl.getInstance();
        userScoreService = UserScoreServiceImpl.getInstance();
    }

    @AfterAll
    public static void end() {
        mgs.stop();
    }

    @Test
    public void should_create_score() throws IOException {

        Session session = sessionService.create(123);
        HttpPost postReq = new HttpPost("http://localhost:8000/100/score?sessionkey=" + session.getSessionId());
        StringEntity payload = new StringEntity("500");
        postReq.setEntity(payload);
        httpClient.execute(postReq);

        String scores = userScoreService.getHighScore(100);
        Assertions.assertEquals("123=500", scores);
    }

}
