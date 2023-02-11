package wolfscriptbot.services;

import wolfscriptbot.object.Clan;
import wolfscriptbot.object.Wolf;

import java.util.HashMap;

public class ClanService {

    //private HashMap<Long, String> joinedClans;

    //Owner - Clan
    private HashMap<Long, Clan> clans = new HashMap<Long, Clan>();

    public Clan getClanOwnerFromUserId(Long userId) {
        return clans.get(userId);
    }

    public Clan createClan(Long userId, String clanName) {
        Clan clan = new Clan();
        clan.setOwnerid(userId);
        clan.setName(clanName);
        clans.put(userId, clan);
        return clans.get(userId);
    }

    public void deleteClan(Long userId) {
        clans.remove(userId);
    }

    public boolean isInClan(Long userId) {
        for (Clan clan : clans.values()) {
            for (Wolf wolf : clan.getMembers()) {
                if(wolf.getUserid() == userId) {
                    return true;
                }
            }
        }
        return clans.containsKey(userId);
    }

    public HashMap<Long, Clan> getClans() {
        return clans;
    }
}