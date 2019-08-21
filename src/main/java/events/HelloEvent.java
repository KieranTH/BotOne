package events;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {


    /*
    --- test event ---
     */
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        String[] messageSent = event.getMessage().getContentRaw().split(" ");
        String name = event.getMember().getUser().getAsMention();
        if(messageSent[0].equalsIgnoreCase("hello"))
        {
            if(!event.getMember().getUser().isBot())
            {
                event.getChannel().sendMessage("Hello " + name).queue();
            }
        }
    }
}
