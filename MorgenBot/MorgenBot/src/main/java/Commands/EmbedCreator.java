package Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public abstract class EmbedCreator {

	public static Random rnd = new Random();
	final static String[] MorgenLine = {"Да, я богатый уёбок",
			"У-У-У-У",
			"Я когда-нибудь уйду",
			"Мерседес, бля, белый",
			"Новый мерен",
			"ХА, наебал!",
			"Сделал деньги на даунах",
			"Это вертолёт",
			"Боевой ковбой",
			"Бля, буду крабсбургером",
			"Деньги, деньги, деньги",
			"Моё ебало - коробка",
			"Я просто сижу на жопе, кайфую",
			"В халате на яхте",
			"Всё, что поднял - потратил",
			"Я из купюрок веер собрал",
			"Тут, бля, очень красиво"};
//16


	public static void TrackEmbed(AudioTrack track, String link, MessageChannel channel) {
		Date date = new Date();
		long time = date.getTime();
		int index = link.indexOf('=') + 1;
		if (index == 0)
		{
			index = link.lastIndexOf('/') + 1;
		}
		System.out.println(index);
		String thumbnail = link.substring(index);
		System.out.println(thumbnail);
		thumbnail = "https://img.youtube.com/vi/" + thumbnail + "/maxresdefault.jpg";
		System.out.println(thumbnail);
		int r = rnd.nextInt(16);
		EmbedBuilder embedMusic = new EmbedBuilder()
				.setTitle(track.getInfo().title)
				.setDescription(Utils.DurationFormatLong(track.getInfo().length))
				.setAuthor("от " + track.getInfo().author)
				.addField("", link,true)
				//.addInlineField("An inline field", "More text")
				//.addInlineField("Another inline field", "Even more text")
				.setColor(2895667)
				.setFooter(MorgenLine[r] + " " + Utils.DurationFormatLong(time), "https://sun9-75.userapi.com/impg/GCjYWYxKOJibJF2E_voTY42dBbaVG8cuEb7U9g/yQqbKn9lCHQ.jpg?size=463x509&quality=95&sign=f75408c71da1900281d0a60c7508794c&c_uniq_tag=xw9dNUUaxrMtWQC_FjadXdlhZMDc_EyxDDtt-7E3Csk&type=album")
				.setImage(thumbnail);
				//.setThumbnail(new File("C:/Users/Bastian/Pictures/kitten2.png"));
		channel.sendMessageEmbeds(embedMusic.build()).queue();
	}

}
