package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.Wolf;
import wolfscriptbot.object.bot.Bot;
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
        } else if(args.length == 3) {

            if(!(member.getIdLong() == new Bot().getAuthorId())) {
                channel.sendMessage("Only the Bot Author can this!").queue();
                return;
            }

            if (message.getMentionedMembers().isEmpty()) return;

            Member mentionedMember = message.getMentionedMembers().get(message.getMentionedMembers().size()-1);
            if (!wolfesService.isUserAWolf(mentionedMember.getIdLong())) {
                wolfesService.registerAWolf(mentionedMember.getIdLong());
            }

            Wolf wolf = wolfesService.getWolfFromUserId(mentionedMember.getIdLong());

            if(clanService.isInClan(mentionedMember.getIdLong())) {
                channel.sendMessage("He is in a Clan!").queue();
                return;
            }

            wolf.setClan(clanService.createClan(mentionedMember.getIdLong(), args[1]));
            wolf.getClan().getMembers().add(wolf);

            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setTitle("You created the Clan: " + wolf.getClan().getName(), null);
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));
            ebf.addField("Owner: ", "ID: " + Main.shardMan.retrieveUserById(wolf.getUserid()).complete().getAsTag(), false);
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