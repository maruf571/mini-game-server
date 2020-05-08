package com.marufh.mgs.service;

public interface UserScoreService {

    void createScore(int level, int score, int userId);

    String getHighScore(int level);
}
