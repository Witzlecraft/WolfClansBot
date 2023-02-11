package wolfscriptbot.object;

public class Wolf {



    private int health = 100;
    private int pets = 0;
    private long userid = 0L;
    private int energie = 100;
    private Clan clan;
    private ClanRole clanRole = ClanRole.NONE;
    private int hungry = 100;
    private int x, y = 0;


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public int getHungry() {
        return hungry;
    }

    public void setClanRole(ClanRole clanRole) {
        this.clanRole = clanRole;
    }

    public ClanRole getClanRole() {
        return clanRole;
    }
}
