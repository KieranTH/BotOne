import commands.Calculate;
import commands.nicknamer;
import events.HelloEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {


    public static void main(String[] args) throws Exception
    {
        JDA jda = new JDABuilder("NjExODg2Njc2ODcyMjY1NzQ5.XV2U4Q.8uniovFfsYpQTS-zGkVKr01HIuQ").build();

        jda.addEventListener(new HelloEvent());
        //jda.addEventListener(new nicknameChanger());
        jda.addEventListener(new Calculate());
        jda.addEventListener(new nicknamer());
    }
}
