package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class nicknamer extends ListenerAdapter {

    /*---- Edits Nickname ----
    @param nicknameChange
     */
    public void onGuildMessageReceived(GuildMessageReceivedEvent e)
    {
        String[] message = e.getMessage().getContentRaw().split(" ");
        if(message[0].equalsIgnoreCase(".n"))
        {
            if (message.length != 0)
            {
                e.getMember().modifyNickname(message[1]).queue();
            }
        }
    }
}
