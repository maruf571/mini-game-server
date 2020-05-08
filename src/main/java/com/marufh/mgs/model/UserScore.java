package com.marufh.mgs.model;

import java.util.Objects;

public final class UserScore {

    private final int userId;

    private final int score;

    private final int level;

    public UserScore(int userId, int score, int level) {
        this.userId = userId;
        this.score = score;
        this.level = level;
    }

    public int getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "UserScore{" +
                "userId=" + userId +
                ", score=" + score +
                ", level=" + level +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserScore userScore = (UserScore) o;
        return userId == userScore.userId &&
                score == userScore.score &&
                level == userScore.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, score, level);
    }
}
