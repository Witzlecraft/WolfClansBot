package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.Animal;
import wolfscriptbot.object.Wolf;
import wolfscriptbot.services.AnimalService;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

import java.awt.*;

public class Move implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();


    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 3) {

            Wolf wolf = wolfesService.getWolfFromUserId(member.getIdLong());

            if (wolf.getEnergie() < 0) {
                channel.sendMessage("You have to Sleep! To low Energie!").queue();
                return;
            }

            switch (args[1]) {
                case "up":
                    wolf.setX(wolf.getX()-Integer.parseInt(args[2]));
                    break;
                case "down":
                    wolf.setX(wolf.getX()+Integer.parseInt(args[2]));
                    break;
                case "left":
                    wolf.setY(wolf.getY()-Integer.parseInt(args[2]));
                    break;
                case "right":
                    wolf.setY(wolf.getY()+Integer.parseInt(args[2]));
                    break;
                default:
                    break;
            }

            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setTitle("Your Moved", null);
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));
            ebf.addField("Position:", "X: " + wolf.getX() + "\nY: " + wolf.getY(), false);
            channel.sendMessageEmbeds(ebf.build()).queue();


            wolf.setEnergie(wolf.getEnergie() -5);
        }







        if(args.length == 2) {

            Wolf wolf = wolfesService.getWolfFromUserId(member.getIdLong());

            if (wolf.getEnergie()-5 < 0) {
                channel.sendMessage("You have to Sleep! To low Energie!").queue();
                return;
            }
            wolf.setEnergie(wolf.getEnergie() -5);

            switch (args[1]) {
                case "up":
                    wolf.setX(wolf.getX()-1);
                    break;
                case "down":
                    wolf.setX(wolf.getX()+1);
                    break;
                case "left":
                    wolf.setY(wolf.getY()-1);
                    break;
                case "right":
                    wolf.setY(wolf.getY()+1);
                    break;
                default:
                    break;
            }

            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setTitle("Your Moved", null);
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));
            ebf.addField("Position:", "X: " + wolf.getX() + "\nY: " + wolf.getY(), false);
            channel.sendMessageEmbeds(ebf.build()).queue();

            wolf.setEnergie(wolf.getEnergie() -5);
        }


    }


    public void sendPrivateMessage(User user, MessageEmbed embedContent) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embedContent).queue();
        });
    }



}