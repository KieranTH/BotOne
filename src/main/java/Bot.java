import commands.Calculate;
import commands.IoanSpam;
import commands.Music.Sound;
import commands.joke;
import commands.nicknamer;
import events.HelloEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {


    public static void main(String[] args) throws Exception
    {
        JDA jda = new JDABuilder("NjExODg2Njc2ODcyMjY1NzQ5.XiNrRw.F-B8zmXvFywG2y-f-2uhA9A5iLc").build();

        jda.addEventListener(new HelloEvent());
        //jda.addEventListener(new nicknameChanger());
        jda.addEventListener(new Calculate());
        jda.addEventListener(new nicknamer());
        jda.addEventListener(new joke());
        jda.addEventListener(new Sound());
        jda.addEventListener(new IoanSpam());
    }
}
