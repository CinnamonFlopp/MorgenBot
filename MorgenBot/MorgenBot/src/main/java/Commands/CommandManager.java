package Commands;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Commands.ButtonListeners.sendButtons;


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
                    case "!play" -> {
                        String link = argument[1];
                        System.out.println(link);
                        final AudioManager audioManager = event.getGuild().getAudioManager();
                        audioManager.openAudioConnection(voiceChannel);
                        if (!link.startsWith("https")) {
                            link = " ";
                            for (int i = 1; i < argument.length; i++) {
                                link += argument[i];
                            }
                            link = "ytsearch:" + link;
                            System.out.println(link);

                        }
                        textChannel.sendMessage("Врубаю басы!").queue();
                        PlayerManager.getInstance().loadAndPlay(textChannel, link);
                        message.delete().queueAfter(0, TimeUnit.SECONDS);
                    }
                    case "!skip" -> {
                        if (audioPlayer.getPlayingTrack() == null) {
                            textChannel.sendMessage("Не музицирую").queue();
                            return;
                        }
                        textChannel.sendMessage("Cкипаю").queue();
                        musicManager.scheduler.nextTrack();
                    }
                    case "!stop" -> {
                        musicManager.scheduler.audioPlayer.stopTrack();
                        musicManager.scheduler.queue.clear();
                        textChannel.sendMessage("Оффаю с позором").queue();
                    }
                    case "!repeat" -> {
                        final boolean newRepeating = !musicManager.scheduler.repeating;
                        musicManager.scheduler.repeating = newRepeating;
                        textChannel.sendMessageFormat("Теперь я **%s**", newRepeating ? "повторяю" : "не повторяю").queue();
                    }
                    case "!help" -> EmbedCreator.HelpEmbed(textChannel);
                    case "!clear" -> {
                        if (argument.length != 2) {
                            textChannel.sendMessage("Слишком много всего, пиши проще!").queue();
                            return;
                        }
                        if (Integer.parseInt(argument[1]) > 99) {
                            textChannel.sendMessage("Нельзя удалить больше 99 сообщений за раз!").queue();
                            return;
                        }
                        if (!isNumber(argument[1])) {
                            textChannel.sendMessage("Ты написал не число, скорее всего, и я ничего не понял").queue();
                            return;
                        }
                        List<Message> messageList = textChannel.getHistory().retrievePast(Integer.parseInt(argument[1]) + 1).complete();
                        textChannel.purgeMessages(messageList);
                        textChannel.sendMessage("Удаляю последние " + argument[1] + " сообщений").queue();
                    }
                    case "!Лёха" ->
                            event.getChannel().sendMessage("Ты ждешь Лёху из армии?").setActionRow(sendButtons()).queue();
                    case "!pause" -> {
                        musicManager.scheduler.audioPlayer.setPaused(true);
                        textChannel.sendMessage("Приостановил").queue();
                    }
                    case "!resume" -> {
                        musicManager.scheduler.audioPlayer.setPaused(false);
                        textChannel.sendMessage("Продолжаем!").queue();
                    }
                    case "!morgen" -> {
                        EmbedCreator.MorgenEmbed(textChannel);
                    }
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
    private boolean isNumber(String msg)
    {
        try {
            Integer.parseInt(msg);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        if (event.getButton().getId().equals("1")) {

            event.reply( event.getUser().getName() + " - МЕГАПЛОХ!").queue();

        }else if (event.getButton().getId().equals("2")) {

            event.reply(event.getUser().getName() + " - Хорош!").queue();

        }

    }

}

