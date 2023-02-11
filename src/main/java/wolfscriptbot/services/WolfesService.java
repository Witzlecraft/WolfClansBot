package wolfscriptbot.services;

import wolfscriptbot.object.Wolf;

import java.util.HashMap;

public class WolfesService {

    private HashMap<Long, Wolf> wolfes = new HashMap<>();

    public Wolf getWolfFromUserId(Long userId) {
        return wolfes.get(userId);
    }

    public boolean isUserAWolf(Long userID) {
        return wolfes.containsKey(userID);
    }

    public Wolf registerAWolf(Long userID) {
        Wolf wolf = new Wolf();
        wolf.setUserid(userID);
        wolfes.put(userID, wolf);
        return wolfes.get(userID);
    }

    public HashMap<Long, Wolf> getWolfes() {
        return wolfes;
    }
}