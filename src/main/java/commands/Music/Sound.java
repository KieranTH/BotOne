package commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Sound extends ListenerAdapter {

    private final AudioPlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers;

    public Sound(){
        this.playerManager = new DefaultAudioPlayerManager();
        musicManagers = new HashMap();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }


    private synchronized GuildMusicManager getGuildAudioPlayer(Guild guild) {
        long guildId = Long.parseLong(guild.getId());
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split(" ", 2);

        if ("?play".equals(command[0]) && command.length == 2) {
            if("smoke".equalsIgnoreCase(command[1]))
            {
                loadAndPlay(event.getChannel(), "https://www.youtube.com/watch?v=eduEUIW8bjk");
            }
            else{
                loadAndPlay(event.getChannel(), command[1]);
            }

        } else if ("?skip".equals(command[0])) {
            TextChannel channel = event.getChannel();
            skipTrack(event.getChannel());
            channel.sendMessage("Skipping song").queue();
        }
        else if("?stop".equals(command[0]))
        {
            TextChannel channel = event.getChannel();
            Guild guild = channel.getGuild();
            AudioManager audioManager = guild.getAudioManager();

            if (audioManager.isConnected() && !audioManager.isAttemptingToConnect()) {
                for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
                    audioManager.closeAudioConnection();
                    channel.sendMessage("Leaving Voice Chat... cunt").queue();
                    break;
                }
            }
        }
        else if("?q".equals(command[0]))
        {
            getQueue(event.getChannel());
        }

        super.onGuildMessageReceived(event);
    }

    private void loadAndPlay(final TextChannel channel, final String trackUrl) {
        final GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());

        playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                channel.sendMessage("Adding to queue " + track.getInfo().title).queue();

                play(channel.getGuild(), musicManager, track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                channel.sendMessage("Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")").queue();

                play(channel.getGuild(), musicManager, firstTrack);
            }

            @Override
            public void noMatches() {
                channel.sendMessage("Nothing found by " + trackUrl).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                channel.sendMessage("Could not play: " + exception.getMessage()).queue();
            }
        });
    }

    private void play(Guild guild, GuildMusicManager musicManager, AudioTrack track) {
        connectToFirstVoiceChannel(guild.getAudioManager());

        musicManager.scheduler.queue(track);
    }

    private void skipTrack(TextChannel channel) {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        musicManager.scheduler.nextTrack();

        channel.sendMessage("Skipped to next track.").queue();
    }

    private void getQueue(TextChannel channel)
    {
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());
        //AudioTrack[] trackArray = (AudioTrack[]) musicManager.scheduler.queue.toArray();

        ArrayList<AudioTrack> list = musicManager.scheduler.listQueue();
        //channel.sendMessage("List size is: " + list.size()).queue();
        //channel.sendMessage("" + list.size()).queue();


        EmbedBuilder eb = new EmbedBuilder();
        //eb.setTitle("Playlist", "www.youtube.com/watch?v="+musicManager.scheduler.queue.element().getIdentifier());
        if(list.size()>0)
        {
            eb.setTitle("Queue", "https://www.youtube.com/watch?v="+list.get(0).getIdentifier());
            for(int i = 0; i<list.size();i++)
            {
                eb.addField(""+i+".", "Title: " + list.get(i).getInfo().title + " \nAuthour: " + list.get(i).getInfo().author, true);
                eb.setThumbnail("https://img.youtube.com/vi/"+ list.get(i).getIdentifier() + "/default.jpg");
            }
        }
        else
        {
            eb.setTitle("Playlist");
        }
        eb.setColor(Color.BLUE);
        channel.sendMessage(eb.build()).queue();
    }

    private static void connectToFirstVoiceChannel(AudioManager audioManager) {
        if (!audioManager.isConnected() && !audioManager.isAttemptingToConnect()) {
            for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
                audioManager.openAudioConnection(voiceChannel);
                break;
            }
        }
    }
}
