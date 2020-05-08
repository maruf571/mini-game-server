package com.marufh.mgs.handler;

import com.marufh.mgs.MiniGameServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginHandlerIT {

    DefaultHttpClient httpClient = new DefaultHttpClient();
    static MiniGameServer mgs = new MiniGameServer();

    @BeforeAll
    public static void init() {
        mgs.start();
    }

    @AfterAll
    public static void end() {
        mgs.stop();
    }

    @Test
    public void should_return_session() throws IOException {
        HttpGet getReq = new HttpGet("http://localhost:8000/1/login");
        HttpResponse response = httpClient.execute(getReq);
        String body = EntityUtils.toString(response.getEntity());

        assertNotNull(body);
        assertEquals(200, response.getStatusLine().getStatusCode());
        assertEquals(10, body.length());
    }

    @Test
    public void should_return_new_session_key() throws IOException {
        HttpGet getReq1 = new HttpGet("http://localhost:8000/1/login");
        HttpResponse response1 = httpClient.execute(getReq1);
        String body1 = EntityUtils.toString(response1.getEntity());

        HttpGet getReq2 = new HttpGet("http://localhost:8000/1/login");
        HttpResponse response2 = httpClient.execute(getReq2);
        String body2 = EntityUtils.toString(response2.getEntity());

        assertNotNull(body1);
        assertNotNull(body2);
        assertNotEquals(body1, body2);
    }
}
