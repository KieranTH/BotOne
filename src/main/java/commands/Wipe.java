package commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.w3c.dom.Text;

import java.util.List;

public class Wipe extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e)
    {
        String[] command = e.getMessage().getContentRaw().split(" ", 2);

        if("?wipe".equalsIgnoreCase(command[0]) && command.length == 2) {
            try {
                int delete = Integer.parseInt(command[1]) + 1;
                TextChannel channel = e.getChannel();
                MessageHistory msgHist = new MessageHistory(channel);
                List<Message> msgs;
                int i = 0;
                while (i < delete)
                {
                    msgs = msgHist.retrievePast(1).complete();
                    msgs.get(0).delete().queue();
                    i++;
                }
            } catch (NumberFormatException event) {
                e.getChannel().sendMessage("Invalid Number given, stop being a prick").queue();
            }




            /*for(int i = 0; i<e.getGuild().getMembers().size();i++)
            {
                if(command[1].equalsIgnoreCase(e.getGuild().getMembers().get(i).getNickname()))
                {
                    try{
                        int delete = Integer.parseInt(command[2]);
                        int j = 0;
                        System.out.println("while");

                        while(j<delete)
                        {
                            if(name.equalsIgnoreCase(e.getMessage().getMember().getNickname()))
                            {
                                e.getMessage().delete().queue();
                                j++;
                            }
                        }
                    }
                    catch(NumberFormatException event)
                    {
                        e.getChannel().sendMessage("Number given is not valid... stop being a stupid prick").queue();
                    }


                }
            }*/

            //int delete = Integer.parseInt(command[2]);
        }
    }
}
