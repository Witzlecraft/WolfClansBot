package wolfscriptbot.object.bot;

import java.util.HashMap;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class Roles {

    private HashMap<Member, Role> roles = new HashMap<Member, Role>();


    public HashMap<Member, Role> getRoles() {
        return roles;
    }

    public void setRoles(HashMap<Member, Role> roles) {
        this.roles = roles;
    }

//	public Role getRole(Member member) {
//		getRoles().remove(member);
//		for (Role roles : member.getRoles()) {
//			getRoles().put(member, roles);
//			break;
//		}
//		return getRoles().get(member);
//	}

    public Role getRole(Member member) {
        return member.getRoles().get(0);
    }

}