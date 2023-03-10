package Commands;

import com.sedmelluq.discord.lavaplayer.player.*;
import net.dv8tion.jda.api.entities.Guild;

public class GuildMusicManager {

    public final AudioPlayer audioPlayer;
    public final TrackScheduler scheduler;
    private final AudioPlayerSendHandler sendHandler;

    public GuildMusicManager(AudioPlayerManager manager, Guild guild){
        this.audioPlayer = manager.createPlayer();

        this.scheduler = new TrackScheduler(this.audioPlayer, guild);
        this.audioPlayer.addListener(this.scheduler);
        this.sendHandler = new AudioPlayerSendHandler(this.audioPlayer);
    }

    public AudioPlayerSendHandler getSendHandler(){
        return sendHandler;
    }

}
