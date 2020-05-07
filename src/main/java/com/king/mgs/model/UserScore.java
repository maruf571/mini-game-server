package com.king.mgs.model;

import java.util.Objects;

public final class UserScore {

    private final int userId;

    private final int score;

    public UserScore(int userId, int score) {
        this.userId = userId;
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "UserScore{" +
                "userId=" + userId +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserScore userScore = (UserScore) o;
        return userId == userScore.userId &&
                score == userScore.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, score);
    }
}
