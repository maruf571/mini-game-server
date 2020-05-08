package com.marufh.mgs.model;

public class Session {

    private final String sessionId;

    private final long timestamp;

    private final int userId;

    public Session(String sessionId, long timestamp, int userId) {
        this.sessionId = sessionId;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId='" + sessionId + '\'' +
                ", timestamp=" + timestamp +
                ", userId='" + userId + '\'' +
                '}';
    }
}
