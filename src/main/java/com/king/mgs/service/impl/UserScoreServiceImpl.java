package com.king.mgs.service.impl;

import com.king.mgs.model.UserScore;
import com.king.mgs.service.UserScoreService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UserScoreServiceImpl implements UserScoreService {

    private static UserScoreServiceImpl instance;
    private static final int NUMBER_OF_SCORE = 15;
    private static final Map<Integer, SortedSet<UserScore>> levelMap = new ConcurrentHashMap<>();
    private static final Logger log = Logger.getLogger(UserScoreServiceImpl.class.getName());

    private UserScoreServiceImpl() {
    }

    public static UserScoreServiceImpl getInstance() {
        log.info("creating object UserScoreServiceImpl");
        if(instance == null) {
            instance = new UserScoreServiceImpl();
        }
        return instance;
    }


    @Override
    public  void createScore(int level, int score, int userId) {
        UserScore userScore = new UserScore(userId, score);
        if(!levelMap.containsKey(level)) {
            createNewLevelMap(userScore, level);
        } else {
            updateExistingLevelMap(userScore, level);
        }
    }

    @Override
    public  String getHighScore(int level) {
        return Optional.ofNullable(levelMap.get(level))
                .orElse(Collections.emptySortedSet())
                .stream()
                .map(us -> String.format("%s=%s", us.getUserId(), us.getScore())
                ).collect(Collectors.joining(","));
    }

    private void updateExistingLevelMap(UserScore userScore, int level) {
        SortedSet<UserScore> userScoreSortedSet = levelMap.get(level);

        UserScore userExist = getUserById(userScoreSortedSet, userScore.getUserId());
        if(userExist != null) {
            // If same user, same level come with different score, set uniqueness does not work
            userScoreSortedSet.remove(userExist);
        }

        if(userScoreSortedSet.size() < NUMBER_OF_SCORE) {
            userScoreSortedSet.add(userScore);
        } else if(userScore.getScore() >= userScoreSortedSet.last().getScore()) {
            userScoreSortedSet.add(userScore);
            userScoreSortedSet.remove(userScoreSortedSet.last());
        }
    }

    private void createNewLevelMap(UserScore userScore, int level) {
        SortedSet<UserScore> userScoreSortedSet = Collections.synchronizedSortedSet(
                new TreeSet<>(Comparator.comparing(UserScore::getScore)
                        .thenComparing(UserScore::getUserId))
                        .descendingSet()
        );
        userScoreSortedSet.add(userScore);
        levelMap.put(level, userScoreSortedSet);
    }

    private UserScore getUserById(SortedSet<UserScore> userScores, int userId) {
        return userScores.stream()
                .filter(userScore -> userId == userScore.getUserId())
                .findAny()
                .orElse(null);
    }

}
