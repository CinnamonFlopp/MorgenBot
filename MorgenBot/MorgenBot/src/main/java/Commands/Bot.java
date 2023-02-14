package Commands;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.Timer;
import java.util.TimerTask;

import static Commands.MorgenLines.MorgenLine;


public class Bot {



    public static void main(String[] args) {
        JDABuilder jdaBuilder = JDABuilder.createDefault("MTA3MTMxODMxMDg2NTYwNDczOA.G4INmH.Rezw7vkDcoBhKQdpdIpcuLUYJuyaq27DorenXs");
        jdaBuilder.enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE,CacheFlag.ACTIVITY);
        jdaBuilder.setActivity(Activity.listening(MorgenLine()));
        jdaBuilder.setBulkDeleteSplittingEnabled(false);
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES,GatewayIntent.GUILD_PRESENCES);
        CommandManager commandManager = new CommandManager();
        jdaBuilder.addEventListeners(commandManager);
        jdaBuilder.build();

    }




    public static Listener listener() {
        Listener listener = new Listener();
        return listener;
    }
}
