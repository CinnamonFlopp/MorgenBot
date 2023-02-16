package Commands;


import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ButtonListeners extends ListenerAdapter {



    public static List<Button> sendButtons()
    {
        List<Button> buttons = new ArrayList<>();
        buttons.add(Button.danger("1", "Нет"));
        buttons.add(Button.success("2", "Да!"));
        return buttons;
    }
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());

        if (event.getButton().getId().equals("1")) {

            event.reply("МЕГАПЛОХ!").queue();

        }
        if (event.getButton().getId().equals("2")) {

            event.reply("Хорош!").queue();

        }
        if (event.getButton().getId().equals("3")) {

            musicManager.scheduler.audioPlayer.setPaused(true);
            event.reply("Приостановил ⏸").queue();

        }
        if (event.getButton().getId().equals("4")) {

            musicManager.scheduler.audioPlayer.setPaused(false);
            event.reply("Продолжаем ▶").queue();

        }

    }
    public static List<Button> ResPaus()
    {
        List<Button> buttons = new ArrayList<>();
        buttons.add(Button.primary("3", "⏸"));
        buttons.add(Button.primary("4", "▶"));
        return buttons;
    }

}