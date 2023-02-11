package wolfscriptbot.listener;


import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wolfscriptbot.object.bot.Bot;
import wolfscriptbot.util.EmbedBox;

public class ChatListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        TextChannel channel;
        String message = event.getMessage().getContentDisplay();
        Message messageObject = event.getMessage();
        Date date = new Date();
        SimpleDateFormat dateDays = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateClock = new SimpleDateFormat("HH:mm:ss");
        for (String command : Bot.commands) {
            if(message.toLowerCase().startsWith(command)) {
                return;
            }
        }
    }

    public void sendPrivateMessage(User user, String content) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(content).queue();
        });
    }
}