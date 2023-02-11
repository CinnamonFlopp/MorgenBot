package Commands;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;


public class CommandManager extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        try {
            if (!event.getAuthor().isBot()) {
                String[] argument = event.getMessage().getContentRaw().split(" ");
                Message message = event.getMessage();
                MessageChannel textChannel = event.getChannel();
                Member member = event.getMember();
                VoiceChannel voiceChannel = member.getVoiceState().getChannel().asVoiceChannel();
                final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                AudioPlayer audioPlayer = musicManager.audioPlayer;
                switch (argument[0]) {
                    case "!play":
                        String link = argument[1];
                        System.out.println(link);
                        final AudioManager audioManager = event.getGuild().getAudioManager();
                        audioManager.openAudioConnection(voiceChannel);
                        if (!link.startsWith("https"))
                        {
                            link = " ";
                            for (int i = 1; i < argument.length; i++)
                            {
                                link += argument[i];
                            }
                            link = "ytsearch:" + link;
                            System.out.println(link);

                        }
                        textChannel.sendMessage("Врубаю басы!").queue();
                        PlayerManager.getInstance().loadAndPlay(textChannel, link);

                        message.delete().queueAfter(1, TimeUnit.SECONDS);
                        break;
                    case "!skip":
                        if (audioPlayer.getPlayingTrack() == null) {
                            textChannel.sendMessage("Не музицирую").queue();
                            return;
                        }
                        musicManager.scheduler.nextTrack();
                        textChannel.sendMessage("Cкипаю").queue();
                        textChannel.sendMessage("Теперь играет: " + audioPlayer.getPlayingTrack().getInfo().title).queue();
                        break;
                    case "!stop":
                        musicManager.scheduler.audioPlayer.stopTrack();
                        musicManager.scheduler.queue.clear();

                        textChannel.sendMessage("Оффаю с позором").queue();
                        break;
                    case "!repeat":
                        final boolean newRepeating = !musicManager.scheduler.repeating;

                        musicManager.scheduler.repeating = newRepeating;

                        textChannel.sendMessageFormat("Теперь я **%s**", newRepeating ? "повторяю" : "не повторяю").queue();
                        break;
                    case  "!help":
                        textChannel.sendMessageFormat("Я - Бот - Морген \n" +
                                "Умею всё, но я занятой, так что отвечаю только на эти сообщения:\n" +
                                "!play *ссылка или поиск по ютубу*" +" - и я сыграю любой трек, но особенно буду рад собственным\n" +
                                "!skip - я скипну трек\n" +
                                "!stop - и я оффну\n" +
                                "!repeat - буду повторять очередь, пока ты снова не скажешь мне !repeat\n").queue();


                }
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            }
        }


        private boolean isUrl(String url)
    {
        try {
            new URI(url);
            return true;
        }
        catch (URISyntaxException e)
        {
            return false;
        }
    }

}

