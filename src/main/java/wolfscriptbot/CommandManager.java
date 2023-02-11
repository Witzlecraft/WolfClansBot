package wolfscriptbot;

import java.util.concurrent.ConcurrentHashMap;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import wolfscriptbot.commands.*;
import wolfscriptbot.commands.admin.Exit;
import wolfscriptbot.commands.admin.WolfClans;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.bot.Bot;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

public class CommandManager {

    public ConcurrentHashMap<String, ServerCommand> commands;

    public CommandManager() {
        this.commands = new ConcurrentHashMap<>();
        this.commands.put("commands", new Commands());
        this.commands.put("cmds", new Commands());
        this.commands.put("attack", new Attack());
        this.commands.put("wolfinfo", new WolfInfo());
        this.commands.put("wolfclans", new WolfClans());
        this.commands.put("sleep", new Sleep());
        this.commands.put("move", new Move());
        this.commands.put("look", new LookAround());
        this.commands.put("createpack", new CreatePack());
        this.commands.put("leavepack", new LeavePack());
        this.commands.put("packinfo", new PackInfo());
        this.commands.put("joinpack", new JoinPack());
        this.commands.put("exit", new Exit());
        this.commands.put("getkey", new GetKey());
        Bot.commands.add("?");
    }

    public boolean perform(String command, Member member, TextChannel channel, Message message) {

        ServerCommand cmd;
        if((cmd = this.commands.get(command.toLowerCase())) != null) {

            ClanService clanService = Main.INSTANCE.getClanService();
            WolfesService wolfesService = Main.INSTANCE.getWolfesService();

            if (!wolfesService.isUserAWolf(member.getIdLong())) {
                wolfesService.registerAWolf(member.getIdLong());
            }

            if(!(channel.getIdLong() == 1073697135540834344L)) {
                return false;
            }

            cmd.performCommand(member, channel, message);
            return true;
        }

        return false;
    }

}