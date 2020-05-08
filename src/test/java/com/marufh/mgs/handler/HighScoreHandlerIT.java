package com.marufh.mgs.handler;

import com.marufh.mgs.MiniGameServer;
import com.marufh.mgs.model.Session;
import com.marufh.mgs.service.SessionService;
import com.marufh.mgs.service.UserScoreService;
import com.marufh.mgs.service.impl.SessionServiceImpl;
import com.marufh.mgs.service.impl.UserScoreServiceImpl;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HighScoreHandlerIT {

    DefaultHttpClient httpClient = new DefaultHttpClient();
    static MiniGameServer mgs = new MiniGameServer();
    static SessionService sessionService = SessionServiceImpl.getInstance();
    static UserScoreService userScoreService = UserScoreServiceImpl.getInstance();;

    @BeforeAll
    public static void init() {
        mgs.start();
    }

    @AfterAll
    public static void end() {
        mgs.stop();
    }

    @Test
    public void should_get_max_score_for_a_level() throws IOException {

        Session session1 = sessionService.create(1);
        Session session2 = sessionService.create(2);
        Session session3 = sessionService.create(3);
        Session session4 = sessionService.create(4);
        Session session5 = sessionService.create(5);
        Session session6 = sessionService.create(6);
        Session session7 = sessionService.create(7);
        Session session8 = sessionService.create(8);
        Session session9 = sessionService.create(9);
        Session session10 = sessionService.create(10);
        Session session11 = sessionService.create(11);
        Session session12 = sessionService.create(12);
        Session session13 = sessionService.create(13);
        Session session14 = sessionService.create(14);
        Session session15 = sessionService.create(15);
        Session session16 = sessionService.create(16);
        Session session17 = sessionService.create(17);
        Session session18 = sessionService.create(18);
        Session session19 = sessionService.create(19);
        Session session20 = sessionService.create(20);

        userScoreService.createScore(6, 100, session1.getUserId());
        userScoreService.createScore(6, 200, session2.getUserId());
        userScoreService.createScore(6, 300, session3.getUserId());
        userScoreService.createScore(6, 400, session4.getUserId());
        userScoreService.createScore(6, 500, session5.getUserId());
        userScoreService.createScore(6, 600, session6.getUserId());
        userScoreService.createScore(6, 700, session7.getUserId());
        userScoreService.createScore(6, 800, session8.getUserId());
        userScoreService.createScore(6, 900, session9.getUserId());
        userScoreService.createScore(6, 1000, session10.getUserId());
        userScoreService.createScore(6, 1100, session11.getUserId());
        userScoreService.createScore(6, 1200, session12.getUserId());
        userScoreService.createScore(6, 1300, session13.getUserId());
        userScoreService.createScore(6, 1400, session14.getUserId());
        userScoreService.createScore(6, 1500, session15.getUserId());
        userScoreService.createScore(6, 1600, session16.getUserId());
        userScoreService.createScore(6, 1700, session17.getUserId());
        userScoreService.createScore(6, 1800, session18.getUserId());
        userScoreService.createScore(6, 1900, session19.getUserId());
        userScoreService.createScore(6, 2000, session20.getUserId());

        HttpGet getReq1 = new HttpGet("http://localhost:8000/6/highscorelist");
        HttpResponse response1 = httpClient.execute(getReq1);
        String body1 = EntityUtils.toString(response1.getEntity());
        String[] arr = body1.split(",");


        Assertions.assertEquals(15, arr.length);
        Assertions.assertEquals("20=2000", arr[0]);
        Assertions.assertEquals("10=1000", arr[10]);
    }

    @Test
    public void should_get_empty_result() throws IOException {
        HttpGet getReq1 = new HttpGet("http://localhost:8000/571/highscorelist");
        HttpResponse response1 = httpClient.execute(getReq1);
        String body1 = EntityUtils.toString(response1.getEntity());
        Assertions.assertEquals("", body1);
    }

    @Test
    public void should_get_one_result() throws IOException {
        Session session1 = sessionService.create(1);
        userScoreService.createScore(571, 100, session1.getUserId());

        HttpGet getReq1 = new HttpGet("http://localhost:8000/571/highscorelist");
        HttpResponse response1 = httpClient.execute(getReq1);
        String body1 = EntityUtils.toString(response1.getEntity());
        Assertions.assertEquals("1=100", body1);
    }
}