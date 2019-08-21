package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Calculate extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e)
    {

        String[] message = e.getMessage().getContentRaw().split(" ");
        if (message[0].equalsIgnoreCase("!c") && message.length == 1)
        {
            e.getChannel().sendMessage("Calculator Ready!").queue();
        }
        else if (message[0].equalsIgnoreCase("!c") && message[1].equalsIgnoreCase("add"))
        {
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 + num2)).queue();

        }
        else if(message[0].equalsIgnoreCase("!c") && message[1].equalsIgnoreCase("sub"))
        {
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: " + (num1 - num2)).queue();
        }
        else if(message[0].equalsIgnoreCase("!c") && message[1].equalsIgnoreCase("div"))
        {
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: "+ (num1 / num2)).queue();
        }
        else if(message[0].equalsIgnoreCase("!c") && message[1].equalsIgnoreCase("multiply"))
        {
            int num1 = Integer.parseInt(message[2]);
            int num2 = Integer.parseInt(message[3]);
            e.getChannel().sendMessage("The Result is: "+ (num1 * num2)).queue();
        }
        else
        {
            e.getChannel().sendMessage("Wrong Paramaters - Incorrect use of Command");
        }
























        //--- backup code ---
        /*if(message[1].equalsIgnoreCase("add"))
            {
                e.getChannel().sendMessage("Add Ready!").queue();
                result = num1 + num2;
                e.getChannel().sendMessage("The Result is: " + result).queue();
            }
            else if(message[1].equalsIgnoreCase("sub"))
            {
                e.getChannel().sendMessage("Subtract Ready!").queue();
                result = num1 - num2;
                e.getChannel().sendMessage("The Result is: "+result).queue();
            }
            else if(message[1].equalsIgnoreCase("multi"))
            {
                e.getChannel().sendMessage("Multiply Ready!").queue();
                result = num1 * num2;
                e.getChannel().sendMessage("The Result is: "+result).queue();
            }
            else if(message[1].equalsIgnoreCase("div"))
            {
                e.getChannel().sendMessage("Divide Ready!").queue();
                result = num1 / num2;
                e.getChannel().sendMessage("The Result is: "+result).queue();
            }
            else
                {
                e.getChannel().sendMessage("invalid command").queue();
            }*/
    }

}


