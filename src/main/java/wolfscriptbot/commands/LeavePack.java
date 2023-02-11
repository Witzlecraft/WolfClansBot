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

public class LeavePack implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();

    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 1) {

            Wolf wolf = wolfesService.getWolfFromUserId(member.getIdLong());

            if (!clanService.isInClan(member.getIdLong())) {
                channel.sendMessage("You are not in a Clan!").queue();
                return;
            }

            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));

            if(wolf.getClan().isWolfClanOwner(wolf)) {
                clanService.deleteClan(wolf.getUserid());
                ebf.setTitle("You leaved the Clan: " + wolf.getClan().getName(), null);
                ebf.addField("Info: ", "Owner have Leave!", false);
                ebf.addField("Info: ", "All Members is now Kicked from Clan!", false);
            } else {
                wolf.getClan().removeMember(wolf);
                ebf.setTitle("You leaved the Clan: " + wolf.getClan().getName(), null);
                ebf.addField("", "", false);
            }
            channel.sendMessageEmbeds(ebf.build()).queue();
        }

    }

    public void sendPrivateMessage(User user, MessageEmbed embedContent) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embedContent).queue();
        });
    }
}