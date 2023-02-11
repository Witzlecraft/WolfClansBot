package wolfscriptbot.util;

import java.awt.Color;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class EmbedBox {

    public static EmbedBuilder eb = new EmbedBuilder();

    public static void createBoxIMG(TextChannel channel, String Title, String description, String imgWebAdress, Color color, int hexcolor) {


        eb.setTitle(Title, null);
        eb.setColor(color);
        eb.setColor(new Color(hexcolor));
        //eb.setColor(new Color(255, 0, 54));
        eb.setThumbnail(imgWebAdress);

        eb.setDescription(description);

        channel.sendMessageEmbeds(eb.build()).queue();
    }


    public static void createBox(TextChannel channel, String Title, String description, Color color, int hexcolor) {

        eb.setTitle(Title, null);
        eb.setColor(color);
        eb.setColor(new Color(hexcolor));
        //eb.setColor(new Color(255, 0, 54));

        eb.setDescription(description);

        channel.sendMessageEmbeds(eb.build()).queue();
    }


}