import commands.*;
import commands.Music.Sound;
import events.HelloEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {


    public static void main(String[] args) throws Exception
    {
        JDA jda = new JDABuilder("NjExODg2Njc2ODcyMjY1NzQ5.XiokIg.5Ky3v3a8LAKnfsgCpj8IyCVWcYM").build();

        jda.addEventListener(new HelloEvent());
        //jda.addEventListener(new nicknameChanger());
        jda.addEventListener(new Calculate());
        jda.addEventListener(new nicknamer());
        jda.addEventListener(new joke());
        jda.addEventListener(new Sound());
        jda.addEventListener(new IoanSpam());
        jda.addEventListener(new Wipe());

        System.out.println("Bot Starting");
    }
}
