package wolfscriptbot.commands.admin;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.Clan;
import wolfscriptbot.object.Wolf;
import wolfscriptbot.object.bot.Bot;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

import java.awt.*;

public class WolfClans implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();


    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(!(member.getIdLong() == new Bot().getAuthorId())) {
            channel.sendMessage("Only the Bot Author can this!").queue();
        }

        if(args.length == 1) {
            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setTitle("Clans", null);
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));
            ebf.addField("--------------------", "", false);
            for (Clan clan : clanService.getClans().values()) {
                ebf.addField("Clan Name:", clan.getName(), false);
                ebf.addField("Owner: ", Main.shardMan.retrieveUserById(clan.getOwnerid()).complete().getAsTag() + " (" + clan.getOwnerid() + ")", false);
                ebf.addField("Member count:", clan.getMembers().size()+"", false);
                ebf.addField("--------------------", "", false);
            }
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