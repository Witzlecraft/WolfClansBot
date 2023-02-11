package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.Wolf;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

import java.awt.*;

public class Sleep implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();

    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 1) {

            Wolf wolf = wolfesService.getWolfFromUserId(member.getIdLong());

            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));

            ebf.setTitle("Sleeping . . .", null);
            ebf.addField("Now is Instant Sleep:", "You have instantly 100% Energie", false);
            channel.sendMessageEmbeds(ebf.build()).queue();

            wolf.setEnergie(100);
        }

    }

    public void sendPrivateMessage(User user, MessageEmbed embedContent) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embedContent).queue();
        });
    }
}