package Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TrackScheduler extends AudioEventAdapter
    {
        public final AudioPlayer audioPlayer;
        public final BlockingQueue<AudioTrack> queue;
        public MessageChannel channel;
        public boolean repeating = false;
        public TrackScheduler(AudioPlayer audioPlayer, Guild guild)
        {
            channel = guild.getSystemChannel();
            this.audioPlayer = audioPlayer;
            this.queue = new LinkedBlockingDeque<>();
        }

        public void queue(AudioTrack track)
        {
            if(!this.audioPlayer.startTrack(track, true))
            {
                this.queue.offer(track);
            }
        }

        public void nextTrack()
        {

            this.audioPlayer.startTrack(this.queue.poll(), false);
            AudioTrack track = audioPlayer.getPlayingTrack();
            String trackUrl = track.getInfo().uri;
            channel.sendMessage("Теперь играет: ").queue();
            EmbedCreator.TrackEmbed(track, trackUrl, channel);


        }

        @Override
        public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
            if(endReason.mayStartNext)
            {
                if (endReason.mayStartNext)
                {
                    if (this.repeating)
                    {
                        audioPlayer.startTrack(track.makeClone(),false);
                        return;
                    }
                }
                nextTrack();
            }
        }





}


