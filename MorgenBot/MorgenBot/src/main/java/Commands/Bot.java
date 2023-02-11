package Commands;


import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;


public class Bot {



    public static void main(String[] args) {
        JDABuilder jdaBuilder = JDABuilder.createDefault("MTA3MTMxODMxMDg2NTYwNDczOA.GYR_-N.sSMj_Ebp3vQxu0vTi25oTPVxgrs4dHp_YOADFc");
        jdaBuilder.enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        jdaBuilder.setBulkDeleteSplittingEnabled(false);
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES);
        CommandManager commandManager = new CommandManager();
        jdaBuilder.addEventListeners(commandManager);


        jdaBuilder.build();
    }


    public static Listener listener() {
        Listener listener = new Listener();
        return listener;
    }
}
