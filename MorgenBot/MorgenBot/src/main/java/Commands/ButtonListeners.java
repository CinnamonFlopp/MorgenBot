package Commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ButtonListeners extends ListenerAdapter {
    private final String[] BlackWords = {"Лёха", "Леха","Алекей"};

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {



        for (String blackWord : BlackWords) {
            if (event.getMessage().getContentRaw().contains(blackWord)) {

                event.getChannel().sendMessage("Ты ждешь Лёху из армии?").setActionRow(sendButtons()).queue();



            }
        }

    }
    public static List<Button> sendButtons()
    {
        List<Button> buttons = new ArrayList<>();
        buttons.add(Button.danger("1", "Нет"));
        buttons.add(Button.success("2", "Да!"));
        return buttons;
    }
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        if (event.getButton().getId().equals("1")) {

            event.reply("МЕГАПЛОХ!").queue();

        }else if (event.getButton().getId().equals("2")) {

            event.reply("Хорош!").queue();

        }

    }
}