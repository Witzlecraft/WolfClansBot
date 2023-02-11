package wolfscriptbot.main;

import com.mongodb.client.MongoDatabase;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import wolfscriptbot.CommandManager;
import wolfscriptbot.config.Config;
import wolfscriptbot.database.MongoManager;
import wolfscriptbot.listener.ChatListener;
import wolfscriptbot.listener.CommandListener;
import wolfscriptbot.listener.ReadyListener;
import wolfscriptbot.object.bot.Bot;
import wolfscriptbot.services.AnimalService;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    private MongoManager mongoManager;
    private MongoDatabase mongoDatabase;

    private ClanService clanService;
    private WolfesService wolfesService;
    private AnimalService animalService;

    public static Main INSTANCE;
    public static ShardManager shardMan;


    private CommandManager cmdMan;

    public static void main(String[] args)  {
        new Main();
    }


    public Main() {

        Config.load();

        INSTANCE = this;

        mongoManager = new MongoManager(this);
        mongoDatabase = mongoManager.getDatabase();

        wolfesService = new WolfesService();
        clanService = new ClanService();
        animalService = new AnimalService();

        animalService.createRandomAnimals();

        try {
            DefaultShardManagerBuilder builders = DefaultShardManagerBuilder.createDefault(new Bot().getBotToken());
            builders.setStatus(OnlineStatus.ONLINE);

            this.cmdMan = new CommandManager();

            builders.enableIntents(GatewayIntent.GUILD_MEMBERS);
            builders.enableIntents(GatewayIntent.GUILD_PRESENCES);
            builders.enableIntents(GatewayIntent.GUILD_MESSAGES);
            builders.addEventListeners(new CommandListener());
            builders.addEventListeners(new ChatListener());
            builders.addEventListeners(new ReadyListener());

            shardMan = builders.build();
            System.out.println("Der Bot wurde erfolgreich gestartet!");
            shutdown();
        } catch (LoginException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {

        new Thread(() -> {
            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while((line = reader.readLine()) != null) {

                    if(line.equalsIgnoreCase("!exit")) {
                        if(shardMan != null) {
                            shardMan.setStatus(OnlineStatus.OFFLINE);
                            shardMan.shutdown();
                            System.out.println("Der Bot wurde Herruntergefahren!");
                        }
                        reader.close();
                    } else {
                        System.out.println("Benutze **!exit** um den Bot auszuschalten!");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public MongoManager getMongoManager() {
        return mongoManager;
    }

    public CommandManager getCmdMan() {
        return cmdMan;
    }


    public ClanService getClanService() {
        return clanService;
    }

    public WolfesService getWolfesService() {
        return wolfesService;
    }

    public AnimalService getAnimalService() {
        return animalService;
    }
}