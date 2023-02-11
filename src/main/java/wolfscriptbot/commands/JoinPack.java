package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.Clan;
import wolfscriptbot.object.Wolf;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

import java.awt.*;

public class JoinPack implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();

    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 2) {

            if (!message.getMentionedMembers().isEmpty()) {

                Member mentionedMember = message.getMentionedMembers().get(message.getMentionedMembers().size()-1);
                if (!wolfesService.isUserAWolf(mentionedMember.getIdLong())) {
                    wolfesService.registerAWolf(mentionedMember.getIdLong());
                }

                if (!clanService.isInClan(mentionedMember.getIdLong())) {
                    channel.sendMessage("He have no Clan!").queue();
                    return;
                }

                if (clanService.isInClan(member.getIdLong())) {
                    channel.sendMessage("You have a Clan!").queue();
                    return;
                }


                Wolf ownWolf = wolfesService.getWolfFromUserId(member.getIdLong());
                Clan clan = clanService.getClanOwnerFromUserId(mentionedMember.getIdLong());
                clan.getMembers().add(ownWolf);
                clanService.getClans().put(mentionedMember.getIdLong() , clan);
                ownWolf.setClan(clan);



                EmbedBuilder ebf = new EmbedBuilder();
                ebf.setTitle("You Joined the Pack: " + ownWolf.getClan().getName(), null);
                ebf.setColor(Color.green);
                ebf.setColor(new Color(0xF40C0C));
                ebf.setColor(new Color(255, 0, 54));
                ebf.addField("Owner of Clan:", "" + mentionedMember.getUser().getAsTag(), false);
                channel.sendMessageEmbeds(ebf.build()).queue();
            }






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