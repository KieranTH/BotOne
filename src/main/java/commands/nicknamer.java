package commands;
import java.io.*;
import java.util.List;
import java.util.Random;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;



public class nicknamer extends ListenerAdapter {

    /*---- Edits Nickname ----
    @param nicknameChange
     */
    public void onGuildMessageReceived(GuildMessageReceivedEvent e)
    {
        //--- file full of names ---
        String myFile = "nicknames.txt";
        //--- catching exception ---
        try {
                //--- bufferReader to read data in file --- converting to ArrayList ----
                BufferedReader abc = new BufferedReader(new FileReader(myFile));
                List<String> lines = new ArrayList<String>();

                //---- Array for Nicknames ----
                String[] nicknames;
                nicknames = new String[64];

                //---- while loop for adding data to array ----
                String line;
                while ((line = abc.readLine()) != null)
                {
                    lines.add(line);
                }
                abc.close();
                nicknames = lines.toArray(new String[]{});


            Random rand = new Random();
            int name = rand.nextInt(nicknames.length);

            //--- event ---
            //e.getMember().modifyNickname(nicknames[name]).queue();


            //--- command ---
            String[] message = e.getMessage().getContentRaw().split(" ");
            if (message[0].equalsIgnoreCase(".n") && message[1].equalsIgnoreCase("nick")) {
                if (message.length != 0 && (!e.getMember().getUser().isBot())) {
                    e.getMember().modifyNickname(nicknames[name]).queue();
                }
            }


        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (net.dv8tion.jda.api.exceptions.HierarchyException ex)
        {
            e.getChannel().sendMessage("Error -  cannot modify roles above or equal to mine... cunt").queue();
            System.out.println("Cannot modify Users Roles");
        }
    }
}
