package com.marufh.mgs.service;

import com.marufh.mgs.model.Session;
import com.marufh.mgs.service.impl.SessionServiceImpl;
import com.marufh.mgs.service.impl.UserScoreServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserScoreServiceTest {

    static UserScoreService userScoreService;
    static SessionService sessionService;

    @BeforeAll
    public static void init() {
        userScoreService = UserScoreServiceImpl.getInstance();
        sessionService = SessionServiceImpl.getInstance();
    }

    @Test
    public void should_get_level_data() {
        Session session1 = sessionService.create(1);
        Session session2 = sessionService.create(2);
        Session session3 = sessionService.create(3);
        Session session4 = sessionService.create(4);
        Session session5 = sessionService.create(5);

        userScoreService.createScore(1, 100, session1.getUserId());
        userScoreService.createScore(1, 200, session2.getUserId());
        userScoreService.createScore(1, 300, session3.getUserId());
        userScoreService.createScore(1, 400, session4.getUserId());
        userScoreService.createScore(1, 500, session5.getUserId());

        userScoreService.createScore(2, 500, session1.getUserId());
        userScoreService.createScore(2, 400, session2.getUserId());
        userScoreService.createScore(2, 300, session3.getUserId());
        userScoreService.createScore(2, 200, session4.getUserId());
        userScoreService.createScore(2, 100, session5.getUserId());

        String level1  = userScoreService.getHighScore(1);
        String level2  = userScoreService.getHighScore(2);

        assertEquals("5=500,4=400,3=300,2=200,1=100", level1);
        assertEquals("1=500,2=400,3=300,4=200,5=100", level2);
    }

    @Test
    public void should_override_score_on_level() {
        Session session1 = sessionService.create(1);
        Session session2 = sessionService.create(2);

        userScoreService.createScore(3, 100, session1.getUserId());
        userScoreService.createScore(3, 200, session2.getUserId());
        // Override score on the same level with same user id
        userScoreService.createScore(3, 300, session1.getUserId());

        userScoreService.createScore(4, 500, session1.getUserId());
        userScoreService.createScore(4, 400, session2.getUserId());
        // Override score on the same level with same user id
        userScoreService.createScore(4, 600, session2.getUserId());

        String level1  = userScoreService.getHighScore(3);
        String level2  = userScoreService.getHighScore(4);

        assertEquals("1=300,2=200", level1);
        assertEquals("2=600,1=500", level2);
    }

    @Test
    public void should_return_empty_string_on_empty_level() {
        String level1  = userScoreService.getHighScore(5);
        assertEquals("", level1);
    }

    @Test
    public void should_return_only_15_scores() {
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

        String level6  = userScoreService.getHighScore(6);
        System.out.println(level6);
        String[] arr = level6.split(",");
        assertEquals(15, arr.length);
        assertEquals("20=2000", arr[0]);
        assertEquals("6=600", arr[14]);
    }


    @Test
    public void should_add_equal_values() {

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

        userScoreService.createScore(7, 100, session1.getUserId());
        userScoreService.createScore(7, 200, session2.getUserId());
        userScoreService.createScore(7, 300, session3.getUserId());
        userScoreService.createScore(7, 400, session4.getUserId());
        userScoreService.createScore(7, 500, session5.getUserId());
        userScoreService.createScore(7, 600, session6.getUserId());
        userScoreService.createScore(7, 700, session7.getUserId());
        userScoreService.createScore(7, 800, session8.getUserId());
        userScoreService.createScore(7, 900, session9.getUserId());
        // Next couple of score are equals
        userScoreService.createScore(7, 1000, session10.getUserId());
        userScoreService.createScore(7, 1000, session11.getUserId());
        userScoreService.createScore(7, 1000, session12.getUserId());
        userScoreService.createScore(7, 1000, session13.getUserId());
        userScoreService.createScore(7, 1000, session14.getUserId());
        userScoreService.createScore(7, 1000, session15.getUserId());
        userScoreService.createScore(7, 1000, session16.getUserId());
        userScoreService.createScore(7, 1000, session17.getUserId());
        userScoreService.createScore(7, 1000, session18.getUserId());
        userScoreService.createScore(7, 1000, session19.getUserId());
        userScoreService.createScore(7, 2000, session20.getUserId());

        String level7  = userScoreService.getHighScore(7);
        String[] arr = level7.split(",");
        assertEquals(15, arr.length);
        assertEquals("20=2000", arr[0]);
        assertEquals("6=600", arr[14]);
    }
}
