package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;

import java.awt.*;

public class GetKey implements ServerCommand {


    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 1) {

            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setTitle("", null);
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));
            ebf.addField("", "", false);
            channel.sendMessageEmbeds(ebf.build()).queue();




            System.out.println();
        }// else {
        //	EmbedBox.createBox(channel, "Fehler!", "Gebe eine ID vom User an!", Color.green, 0xF40C0C);
        //}


    }


    public void sendPrivateMessage(User user, MessageEmbed embedContent) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embedContent).queue();
        });
    }



}