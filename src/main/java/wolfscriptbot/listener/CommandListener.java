package wolfscriptbot.listener;


import java.awt.Color;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wolfscriptbot.main.Main;
import wolfscriptbot.util.EmbedBox;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.isFromType(ChannelType.TEXT)) {

            Member member = event.getMember();
            TextChannel channel = event.getTextChannel();
            String message = event.getMessage().getContentDisplay();
            String prefix = "?";

            if(message.startsWith(prefix)) {
                String[] args = message.substring(1).split(" ");

                if(args.length > 0) {
                    if(!Main.INSTANCE.getCmdMan().perform(args[0], member, channel, event.getMessage())) {
                        //EmbedBox.createBox(channel, "Fehler", "**Fehler:** " + message + " konnte nicht gefunden werden!", Color.red, 100);
                    }
                }
            }
        }
    }
}