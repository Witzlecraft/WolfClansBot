package wolfscriptbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import wolfscriptbot.commands.types.ServerCommand;
import wolfscriptbot.main.Main;
import wolfscriptbot.object.Wolf;
import wolfscriptbot.services.ClanService;
import wolfscriptbot.services.WolfesService;

import java.awt.*;

public class Attack implements ServerCommand {

    private ClanService clanService = Main.INSTANCE.getClanService();
    private WolfesService wolfesService = Main.INSTANCE.getWolfesService();

    //https://www.roblox.com/users/profile?username=herkoffer
    @Override
    public void performCommand(Member member, TextChannel channel, Message message) {

        String[] args = message.getContentDisplay().split(" ");

        if(args.length == 2) {

            if (!message.getMentionedMembers().isEmpty()) {

                Member mentionedMember = message.getMentionedMembers().get(message.getMentionedMembers().size()-1);


                if (!wolfesService.isUserAWolf(mentionedMember.getIdLong())) {
                    wolfesService.registerAWolf(mentionedMember.getIdLong());
                }

                Wolf attackedwolf = wolfesService.getWolfFromUserId(mentionedMember.getIdLong());
                Wolf ownWolf = wolfesService.getWolfFromUserId(member.getIdLong());

                if(!(Math.abs(attackedwolf.getX()-ownWolf.getX()) <= 4) && Math.abs(attackedwolf.getY()-ownWolf.getY()) <= 4) {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("The wolf track could not be found!", null);
                    eb.setColor(Color.green);
                    eb.setColor(new Color(0xF40C0C));
                    eb.setColor(new Color(255, 0, 54));
                    eb.setThumbnail("https://images.gutefrage.net/media/fragen/bilder/mal-ne-frage-mein-kumpel-meinte-das-waere-ne-wolfsspur-und-jetzt-will-ich-wissen-ob-er-recht-hat/0_big.jpg?v=1445786389000");
                    channel.sendMessageEmbeds(eb.build()).queue();
                    return;
                }

                if (ownWolf.getClan() != null && ownWolf.getClan().getMembers().contains(attackedwolf)) {
                    channel.sendMessage("Really? Are you really such a dirty traitor?").queue();
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

                if (ownWolf.getHungry() <= 20) {
                    channel.sendMessage("You are to hungry to fight!").queue();
                    return;
                }

                if (!attackedwolf.isDead()) {
                    attackedwolf.setHealth(attackedwolf.getHealth() - 12);
                    ownWolf.setEnergie(ownWolf.getEnergie() - 20);
                    ownWolf.setHungry(ownWolf.getHungry() - 10);
                }

                if (attackedwolf.getHealth() <= 0) {
                    if (attackedwolf.isDead()) {
                        channel.sendMessage("Hey, the wolf is already Dead!").queue();
                    } else {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle(mentionedMember.getUser().getAsTag() + " is now Dead!", null);
                        eb.setColor(Color.green);
                        eb.setColor(new Color(0xF40C0C));
                        eb.setColor(new Color(255, 0, 54));
                        eb.addField("Health from other Wolf:", "" + attackedwolf.getHealth(), false);
                        eb.setImage("https://media.istockphoto.com/id/1428727002/photo/a-wolf-or-dog-lies-on-the-sand-basks-in-the-sun.jpg?s=612x612&w=0&k=20&c=QrBp7KM9febfVQdsOd85jp56lkvGDhOZYVOE2Xw7icQ=");
                        channel.sendMessageEmbeds(eb.build()).queue();
                        attackedwolf.setDead(true);
                    }
                    return;
                }

                EmbedBuilder ebf = new EmbedBuilder();
                ebf.setTitle("You attack " + mentionedMember.getUser().getAsTag(), null);
                ebf.setColor(Color.green);
                ebf.setColor(new Color(0xF40C0C));
                ebf.setColor(new Color(255, 0, 54));
                ebf.addField("Health from other Wolf:", "" + attackedwolf.getHealth(), false);
                channel.sendMessageEmbeds(ebf.build()).queue();


            }






            System.out.println();
        }// else {
        //	EmbedBox.createBox(channel, "Fehler!", "Gebe eine ID vom User an!", Color.green, 0xF40C0C);
        //}


    }


    public void sendPrivateMessage(User user, MessageEmbed embedContent) {
        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embedContent).queue();
        });
    }



}