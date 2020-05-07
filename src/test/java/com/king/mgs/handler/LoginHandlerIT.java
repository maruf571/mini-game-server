package com.king.mgs.handler;

import com.king.mgs.MiniGameServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginHandlerIT {

    static DefaultHttpClient httpClient;
    static MiniGameServer mgs = new MiniGameServer();

    @BeforeAll
    public static void init() {
        mgs.start();
        httpClient = new DefaultHttpClient();
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

}
