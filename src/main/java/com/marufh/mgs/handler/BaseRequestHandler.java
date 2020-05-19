package com.marufh.mgs.handler;


import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseRequestHandler {

    protected static int getPathVariable(HttpExchange httpExchange, Pattern pattern, int groupId) {
        Matcher matcher = pattern.matcher(httpExchange.getRequestURI().getPath());
        int pathVariable = -1;
        if(matcher.find()) {
            pathVariable =  Integer.parseInt(matcher.group(groupId));
        }
        return pathVariable;
    }

    protected static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    protected int readBodyAsInt(HttpExchange httpExchange) {
        int value = -1;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8)) ) {
            value = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    protected void sendRespond(HttpExchange exchange, int httpStatus, String response) {
        try {
            int contentLength = response.length();
            exchange.sendResponseHeaders(httpStatus, contentLength);
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void handlePost(HttpExchange httpExchange) {
        sendRespond(httpExchange, HttpStatus.METHOD_NOT_ALLOWED.code, HttpStatus.METHOD_NOT_ALLOWED.message);
    }


    protected void handleGet(HttpExchange httpExchange) {
        sendRespond(httpExchange, HttpStatus.METHOD_NOT_ALLOWED.code, HttpStatus.METHOD_NOT_ALLOWED.message);
    }



}
