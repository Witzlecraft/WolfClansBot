package wolfscriptbot.object;

import java.util.ArrayList;

public class Clan {

    private String name = "";

    private long ownerid = 0;
    private ArrayList<Wolf> members = new ArrayList<>();


    public void addMember(Wolf wolf) {
        members.add(wolf);
    }
    public void removeMember(Wolf wolf) {
        members.remove(wolf);
    }

    public boolean isWolfClanOwner(Wolf wolf) {
        return ownerid == wolf.getUserid();
    }

    public ArrayList<Wolf> getMembers() {
        return members;
    }

    public long getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(long ownerid) {
        this.ownerid = ownerid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(ArrayList<Wolf> members) {
        this.members = members;
    }
}