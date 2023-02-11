package wolfscriptbot.listener;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.bot.Bot;

public class ReadyListener extends ListenerAdapter {

//	public static List<String> serverName = new ArrayList<>();
//	public static List<TextChannel> textChannels = new ArrayList<>();

    @Override
    public void onReady(ReadyEvent event) {
        new Bot().sendActivited();
        System.out.println("System start event has started!");
    }

}