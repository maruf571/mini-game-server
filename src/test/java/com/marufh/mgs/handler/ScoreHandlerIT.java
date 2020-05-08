package com.marufh.mgs.handler;

import com.marufh.mgs.MiniGameServer;
import com.marufh.mgs.model.Session;
import com.marufh.mgs.service.SessionService;
import com.marufh.mgs.service.impl.SessionServiceImpl;
import com.marufh.mgs.service.UserScoreService;
import com.marufh.mgs.service.impl.UserScoreServiceImpl;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ScoreHandlerIT {

    static MiniGameServer mgs = new MiniGameServer();
    DefaultHttpClient httpClient = new DefaultHttpClient();
    SessionService sessionService = SessionServiceImpl.getInstance();
    UserScoreService userScoreService = UserScoreServiceImpl.getInstance();

    @BeforeAll
    public static void init() {
        mgs.start();
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

    @Test
    public void should_get_forbidden_403() throws IOException {

        HttpPost postReq = new HttpPost("http://localhost:8000/200/score?sessionkey=ASFDGHJKL");
        StringEntity payload = new StringEntity("500");
        postReq.setEntity(payload);
        HttpResponse response = httpClient.execute(postReq);

        Assertions.assertEquals(403, response.getStatusLine().getStatusCode());
    }

    @Test
    public void should_get_bad_request_400() throws IOException {

        Session session = sessionService.create(12);
        HttpPost postReq = new HttpPost("http://localhost:8000/200/score?sessionkey=ASFDGHJKL");
        StringEntity payload = new StringEntity("5001222222222222222");
        postReq.setEntity(payload);
        HttpResponse response = httpClient.execute(postReq);

        Assertions.assertEquals(400, response.getStatusLine().getStatusCode());
    }

}
