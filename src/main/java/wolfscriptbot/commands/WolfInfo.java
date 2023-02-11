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
import java.util.Arrays;

public class WolfInfo implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();


    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 1) {
            Wolf wolf = wolfesService.getWolfFromUserId(member.getIdLong());
            sendInfo(wolf, channel);
        } else if(args.length == 2) {

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

            sendInfo(wolf, channel);
        }


    }


    private void sendInfo(Wolf wolf, TextChannel channel) {
        EmbedBuilder ebf = new EmbedBuilder();
        ebf.setTitle("WolfInfo", null);
        ebf.setColor(Color.green);
        ebf.setColor(new Color(0xF40C0C));
        ebf.setColor(new Color(255, 0, 54));
        ebf.addField("Health", wolf.getHealth()+"", false);
        ebf.addField("Enegie", wolf.getEnergie()+"", false);

        if (wolf.getClan() == null) {
            ebf.addField("Clan", "have no clan", false);
        } else {
            ebf.addField("Clan", wolf.getClan().getName(), false);
        }

        ebf.addField("Pets Ammount", wolf.getPets()+"", false);
        ebf.addField("Position",  "X: " + wolf.getX() + ", Y: " + wolf.getY(), false);
        ebf.addField("Hungry",  wolf.getHungry()+"", false);
        channel.sendMessageEmbeds(ebf.build()).queue();
    }


    public void sendPrivateMessage(User user, MessageEmbed embedContent) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embedContent).queue();
        });
    }



}