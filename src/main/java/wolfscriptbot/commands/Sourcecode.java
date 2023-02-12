package wolfscriptbot.commands;

import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.object.Animal;
import wolfscriptbot.services.AnimalService;
import wolfscriptbot.services.MapService;

public class Sourcecode implements ServerCommand {


    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 1) {
            sendPrivateMessage(member.getUser(), "**Sourcecode:** https://github.com/Witzlecraft/WolfClansBot/");
        }
    }


    public void sendPrivateMessage(User user, String message) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(message).queue();
        });
    }



}