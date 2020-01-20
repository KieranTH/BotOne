package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class IoanSpam extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] message = e.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase("?ioan"))
        {
            Member user  = e.getGuild().getMemberById("194187955059818497");
            for(int i = 0;i<1000;i++)
            {
                e.getChannel().sendMessage(user.getAsMention() + " has a small dick").queue();
            }



        }
    }
}
