package Commands;

import com.sedmelluq.discord.lavaplayer.player.*;
import com.sedmelluq.discord.lavaplayer.source.*;
import com.sedmelluq.discord.lavaplayer.tools.*;
import com.sedmelluq.discord.lavaplayer.track.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.*;

public class PlayerManager {

    private static PlayerManager INSTANCE;
    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager(){
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild){
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager, guild);
            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
            return guildMusicManager;
        });
    }

    public void loadAndPlay(MessageChannel channel, String trackUrl)
    {
        final GuildMusicManager musicManager = this.getMusicManager(channel.getJDA().getGuildById(403953125066670081L));

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                channel.sendMessage(String.format("Добавляю в очередь:")).queue();


                musicManager.scheduler.queue(track);
                EmbedCreator.TrackEmbed(track, trackUrl, channel);

            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                final  List<AudioTrack> tracks = playlist.getTracks();
                for (final AudioTrack track : tracks) {
                    musicManager.scheduler.queue(track);
                }
                channel.sendMessage(String.format("Ставлю плейлист: ")).queue();
                EmbedCreator.PlayListEmbed(playlist,trackUrl,channel);

            }

            @Override
            public void noMatches() {
                channel.sendMessage(String.format("Такого нет, не врубаю")).queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                channel.sendMessage(String.format("Ой-ой, не работает")).queue();
            }
        });
    }


    public static PlayerManager getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

}
