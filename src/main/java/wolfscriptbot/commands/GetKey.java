package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.object.Animal;
import wolfscriptbot.services.AnimalService;
import wolfscriptbot.services.MapService;

import java.awt.*;

public class GetKey implements ServerCommand {


    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 1) {
            MapService mapService = new MapService();
            AnimalService animalService = new AnimalService();
            animalService.createRandomAnimals();

            for (Animal animal : animalService.searchAnimals(7,7)) {
                channel.sendMessage(animal.getAnimalType().name() + "f").queue();
            }

        }



    }


    public void sendPrivateMessage(User user, MessageEmbed embedContent) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embedContent).queue();
        });
    }



}