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

public class Hunt implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();
    private AnimalService animalService = Main.INSTANCE.getAnimalService();

    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 2) {

            Animal attackedanimal = null;
            for (Animal animal : animalService.getAnimals()) {
                if(animal.getId() == Integer.parseInt(args[1])) {
                    attackedanimal = animal;
                }
            }
            if (attackedanimal == null) {
                channel.sendMessage("Animal not found!").queue();
                return;
            }

                Wolf ownWolf = wolfesService.getWolfFromUserId(member.getIdLong());

                if(!(Math.abs(ownWolf.getX()-attackedanimal.getX()) <= 1) && Math.abs(ownWolf.getY()-attackedanimal.getY()) <= 1) {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("The Animal track could not be found!", null);
                    eb.setColor(Color.green);
                    eb.setColor(new Color(0xF40C0C));
                    eb.setColor(new Color(255, 0, 54));
                    eb.setThumbnail("https://outdoorcamp.de/wp-content/uploads/2019/05/tierspuren-lesen.jpg");
                    channel.sendMessageEmbeds(eb.build()).queue();
                    return;
                }

                if (ownWolf.getEnergie() <= 0) {
                    channel.sendMessage("You have to Sleep! To low Energie!").queue();
                    return;
                }
                if (ownWolf.getHealth() <= 20) {
                    if(ownWolf.isDead()) {
                        channel.sendMessage("Oh, a ghost wolf wants revenge, too bad they're all plasma.").queue();
                    }
                    channel.sendMessage("You are to weak to fight!").queue();
                    return;
                }

                /*if (ownWolf.getHungry() <= 20) {
                    channel.sendMessage("You are to hungry to fight!").queue();
                    return;
                }*/



                if (attackedanimal.getHealth()-12 <= 0) {
                    if (attackedanimal.isDead()) {
                        channel.sendMessage("Hey, the Animal is already Dead!").queue();
                    } else {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle(attackedanimal.getAnimalType().name() + " is now Dead!", null);
                        eb.setColor(Color.green);
                        eb.setColor(new Color(0xF40C0C));
                        eb.setColor(new Color(255, 0, 54));
                        eb.addField("Health from other Animal:", "" + 0, false);
                        switch (attackedanimal.getAnimalType()) {
                            case DEER:
                                eb.setImage("https://www.swr.de/swraktuell/baden-wuerttemberg/karlsruhe/1671465846339,totes-reh-102~_v-16x9@2dM_-ad6791ade5eb8b5c935dd377130b903c4b5781d8.jpg");
                                break;
                        }
                        channel.sendMessageEmbeds(eb.build()).queue();
                        attackedanimal.setDead(true);
                        for (Wolf wolfHitter : attackedanimal.getHitters()) {
                            if(wolfHitter.getHungry()+50 <= 100) {
                                wolfHitter.setHungry(wolfHitter.getHungry()+50);
                            } else {
                                wolfHitter.setHungry(100);
                            }
                        }
                    }
                    return;
                } else {
                    if (!attackedanimal.isDead()) {

                        if(!attackedanimal.getHitters().contains(ownWolf))  {
                            attackedanimal.getHitters().add(ownWolf);
                        }

                        attackedanimal.setHealth(attackedanimal.getHealth() - 12);
                        ownWolf.setEnergie(ownWolf.getEnergie() - 20);
                        ownWolf.setHungry(ownWolf.getHungry() - 10);
                    }
                }



                EmbedBuilder ebf = new EmbedBuilder();
                ebf.setTitle("You attacked a " + attackedanimal.getAnimalType().name(), null);
                ebf.setColor(Color.green);
                ebf.setColor(new Color(0xF40C0C));
                ebf.setColor(new Color(255, 0, 54));
                ebf.addField("Health from other Animal:", "" + attackedanimal.getHealth(), false);
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