package wolfscriptbot.commands;

import java.awt.Color;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.bot.Bot;
import wolfscriptbot.util.EmbedBox;

public class Exit implements ServerCommand {

    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        if(member.getIdLong() == new Bot().getAuthorId()) {
            String[] args = message.getContentDisplay().split(" ");

            if(args.length == 1) {
                EmbedBox.createBox(channel, "INFO", "**Herunterfahren...**", Color.green, 0xF40C0C);

                Main.shardMan.setStatus(OnlineStatus.OFFLINE);
                Main.shardMan.shutdown();
                System.out.println("Der Bot wurde Herruntergefahren!");
            }

        } else {
            EmbedBox.createBox(channel, "No access!", "you dont have the permission", Color.red, 0xF40C0C);
        }

    }

}