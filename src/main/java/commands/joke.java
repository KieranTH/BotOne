package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class joke extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e)
    {
        try{

            BufferedReader in = new BufferedReader(new FileReader(new File("jokes.txt")));
            List<String> lines = new ArrayList<String>();

            String line;
            while((line = in.readLine()) != null)
            {
                lines.add(line);
                //System.out.println(line);
            }
            in.close();

            Random rand = new Random();
            int newJoke = rand.nextInt(lines.size() + 1);

            String[] message = e.getMessage().getContentRaw().split(" ");
            if(message[0].equalsIgnoreCase("?joke"))
            {
                if(newJoke == lines.size() + 1)
                {
                    e.getChannel().sendMessage("Ioan is a fucking good joke.").queue();
                }
                else{
                    line = lines.get(newJoke);
                    e.getChannel().sendMessage(line).queue();
                }

            }


        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
    }
}
