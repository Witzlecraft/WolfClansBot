package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.Wolf;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

import java.awt.*;

public class CreatePack implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();

    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 2) {

            Wolf ownWolf = wolfesService.getWolfFromUserId(member.getIdLong());

            if(clanService.isInClan(member.getIdLong())) {
                channel.sendMessage("You are in a Clan!").queue();
                return;
            }

            ownWolf.setClan(clanService.createClan(member.getIdLong(), args[1]));
            ownWolf.getClan().getMembers().add(ownWolf);

            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setTitle("You created the Clan: " + ownWolf.getClan().getName(), null);
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));
            ebf.addField("Owner: ", "ID: " + member.getUser().getAsTag(), false);
            channel.sendMessageEmbeds(ebf.build()).queue();
        }






        System.out.println();
    }// else {
    //	EmbedBox.createBox(channel, "Fehler!", "Gebe eine ID vom User an!", Color.green, 0xF40C0C);
    //}





    public void sendPrivateMessage(User user, MessageEmbed embedContent) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embedContent).queue();
        });
    }



}