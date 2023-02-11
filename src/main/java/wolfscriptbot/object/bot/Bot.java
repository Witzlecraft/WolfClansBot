package wolfscriptbot.object.bot;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import wolfscriptbot.main.Main;

public class Bot {
	
	public static ArrayList<String> commands = new ArrayList<String>();


	private final String botToken = "";
	private final long botId = 1073691926026530816L;
	private final long groundServerId = 992906105049059390L;
	private final long authorId = 402507309676494848L;

	//private String globalChannelName = "globalchat";
	private String botInviteLink = "https://discord.com/api/oauth2/authorize?client_id=" + botId + "&permissions=8&scope=bot";
	private String serverInviteLink = "https://discord.gg/wolfscript";




	public void sendActivited() {
		Main.shardMan.setActivity(Activity.watching("on Wolf Clans"));
	}


	
	public TextChannel channelNameToChannel(Guild guild, String channelName) {
		TextChannel textChannel = guild.getTextChannelsByName(channelName,true).get(0);
		return textChannel;
	}
	
	
	
	

	public long getBotId() {
		return botId;
	}

	public String getBotToken() {
		return botToken;
	}

	public long getGroundServerId() {
		return groundServerId;
	}

	//public String getGlobalChannelName() {
	//	return globalChannelName;
	//}
	
	//public void setGlobalChannelName(String globalChannelName) {
	//	this.globalChannelName = globalChannelName;
	//}

	public String getBotInviteLink() {
		return botInviteLink;
	}

	public void setBotInviteLink(String botInviteLink) {
		this.botInviteLink = botInviteLink;
	}

	public String getServerInviteLink() {
		return serverInviteLink;
	}

	public void setServerInviteLink(String serverInviteLink) {
		this.serverInviteLink = serverInviteLink;
	}

	public long getAuthorId() {
		return authorId;
	}

}
