package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.Wolf;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

import java.awt.*;

public class Attack implements ServerCommand {

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

                Wolf attackedwolf = wolfesService.getWolfFromUserId(mentionedMember.getIdLong());
                Wolf ownWolf = wolfesService.getWolfFromUserId(member.getIdLong());

                if (ownWolf.getEnergie() <= 0) {
                    channel.sendMessage("You have to Sleep! To low Energie!").queue();
                    return;
                }
                if (ownWolf.getHealth() <= 20) {
                    channel.sendMessage("You are to weak to fight!").queue();
                    return;
                }

                if (ownWolf.getHungry() <= 20) {
                    channel.sendMessage("You are to weak to fight!").queue();
                    return;
                }

                attackedwolf.setHealth(attackedwolf.getHealth() - 12);
                ownWolf.setEnergie(attackedwolf.getEnergie() -  20);
                ownWolf.setHungry(attackedwolf.getHungry() -  10);


                EmbedBuilder ebf = new EmbedBuilder();
                ebf.setTitle("You attack " + mentionedMember.getUser().getAsTag(), null);
                ebf.setColor(Color.green);
                ebf.setColor(new Color(0xF40C0C));
                ebf.setColor(new Color(255, 0, 54));
                ebf.addField("Health from other Wolf:", "" + attackedwolf.getHealth(), false);
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