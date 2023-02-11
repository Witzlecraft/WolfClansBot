package wolfscriptbot.commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.object.bot.Bot;
import wolfscriptbot.util.EmbedBox;

public class Commands implements ServerCommand {

    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        if(member.hasPermission(channel, Permission.MESSAGE_MANAGE) || member.getIdLong() == new Bot().getAuthorId()) {
            String[] args = message.getContentDisplay().split(" ");
            EmbedBuilder eb = new EmbedBuilder();

            if(args.length == 1) {

                eb.setTitle("Befehls-Liste", null);
                eb.setDescription("Bot by MorrisBr");

                eb.setColor(Color.red);
                eb.setColor(new Color(0xF40C0C));
                eb.setColor(new Color(255, 0, 54));
                eb.setThumbnail(member.getGuild().getIconUrl());


                eb.addField("Verwaltung", "**�** !debug\n"
                                + "**�** !chatmute-user <USERID>\n"
                                + "**�** !chatmute-server <SERVERID>\n"
                        , false);

                channel.sendMessageEmbeds(eb.build()).queue();


            }

        } else {
            EmbedBox.createBox(channel, "Zugriff verweigert!", "Du hast dazu keine Rechte!", Color.red, 0xF40C0C);
        }

    }

}