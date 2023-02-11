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

public class LookAround implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();


    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 1) {

            EmbedBuilder ebf = new EmbedBuilder();
            ebf.setTitle("Founded Animals", null);
            ebf.setColor(Color.green);
            ebf.setColor(new Color(0xF40C0C));
            ebf.setColor(new Color(255, 0, 54));


            Wolf wolf = wolfesService.getWolfFromUserId(member.getIdLong());
            AnimalService animalService = Main.INSTANCE.getAnimalService();
            for (Animal animal : animalService.searchAnimals(wolf.getX(), wolf.getY())) {
                ebf.addField(animal.getAnimalType().name(), "ID: "+animal.getId(), false);
                ebf.addField("Health", animal.getHealth()+"", false);
                ebf.addField("Position", "X: " + animal.getX() + "\nY: " + animal.getY(), false);
            }
            for (Wolf wolfes : wolfesService.getWolfes().values()) {

                //double distance = Math.sqrt((wolfes.getX()-wolf.getX())*(wolfes.getX()-wolf.getX()) + (wolfes.getY()-wolf.getY())*(wolfes.getY()-wolf.getY()));
                //System.out.println(distance);

                if(Math.abs(wolfes.getX()-wolf.getX()) <= 4 && Math.abs(wolfes.getY()-wolf.getY()) <= 4 && wolfes.getUserid() != wolf.getUserid()) {
                    ebf.addField("Wolf", "Health: " + wolfes.getHealth() + "\nName: " + Main.shardMan.retrieveUserById(wolfes.getUserid()).complete().getAsTag(), false);
                    ebf.addField("Position", "X: " + wolfes.getX() + "\nY: " + wolfes.getY(), false);
                }
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