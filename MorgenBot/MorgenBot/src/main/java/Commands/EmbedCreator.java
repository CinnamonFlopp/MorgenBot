package Commands;


import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;


import java.util.Random;

import static Commands.ButtonListeners.ResPaus;
import static Commands.ButtonListeners.sendButtons;
import static Commands.MorgenLines.MorgenLine;

public abstract class EmbedCreator {


	public static void TrackEmbed(AudioTrack track, String link, MessageChannel channel) {
		int index = link.indexOf('=') + 1;
		int index1 = link.indexOf('&');
		String thumbnail;
		if (index == 0)
		{
			index = link.lastIndexOf('/') + 1;
		}
		System.out.println(index);
		if (index1 == -1)
		{
			thumbnail = link.substring(index);
		}
		else
		{

			thumbnail = link.substring(index,index1);
		}
		System.out.println(thumbnail);
		thumbnail = "https://img.youtube.com/vi/" + thumbnail + "/maxresdefault.jpg";
		System.out.println(thumbnail);
		EmbedBuilder embedMusic = new EmbedBuilder()
				.setTitle(Utils.DurationFormatLong(track.getInfo().length))
				.setDescription("[" + track.getInfo().title + "]" + "(" + link + ")")
				.setAuthor("от " + track.getInfo().author)
				//.addField("", link,true)
				//.addInlineField("An inline field", "More text")
				//.addInlineField("Another inline field", "Even more text")
				.setColor(2895667)
				.setFooter(MorgenLine() , "https://sun9-75.userapi.com/impg/GCjYWYxKOJibJF2E_voTY42dBbaVG8cuEb7U9g/yQqbKn9lCHQ.jpg?size=463x509&quality=95&sign=f75408c71da1900281d0a60c7508794c&c_uniq_tag=xw9dNUUaxrMtWQC_FjadXdlhZMDc_EyxDDtt-7E3Csk&type=album")
				.setImage(thumbnail);
				//.setThumbnail(new File("C:/Users/Bastian/Pictures/kitten2.png"));
		channel.sendMessageEmbeds(embedMusic.build()).setActionRow(ResPaus()).queue();
	}
	public static void HelpEmbed(MessageChannel channel)
	{
		EmbedBuilder embedMusic = new EmbedBuilder()
				.setTitle("Я бот - Морген")
				.setDescription("""
						**Умею всё, но я занятой, так что отвечаю только на эти сообщения:**\s
						**!play** *ссылка или поиск по ютубу* - и я сыграю любой трек, но особенно буду рад собственным хитам\s
						**!skip** - я скипну трек
						**!stop** - и я оффну
						**!repeat** - буду повторять очередь, пока ты снова не скажешь мне **!repeat**\s
						**!clear** *X число* - удаляю последние X сообщений в канале\s
						**!pause** - чтобы я поставил на паузу и **!resume** чтобы продолжить\s
						**!help** - чтобы я объяснил, как я работаю\s
						Чтобы я отвечал, ты обязательно должен быть в голосовом канале!""")
				//.setAuthor("от " + track.getInfo().author)
				//.addField("", link,true)
				//.addInlineField("An inline field", "More text")
				//.addInlineField("Another inline field", "Even more text")
				.setColor(2895667)
				.setFooter(MorgenLine(), "https://sun9-75.userapi.com/impg/GCjYWYxKOJibJF2E_voTY42dBbaVG8cuEb7U9g/yQqbKn9lCHQ.jpg?size=463x509&quality=95&sign=f75408c71da1900281d0a60c7508794c&c_uniq_tag=xw9dNUUaxrMtWQC_FjadXdlhZMDc_EyxDDtt-7E3Csk&type=album")
				.setImage("https://api.bashinform.ru/attachments/b093eab1197b7efe7ed8db2d984687d24c9e0a56/store/crop/74/102/816/410/1600/0/0/c6884272a8b546cb8a9b6ee1807c4a42283bc24b5c7c5230e7134cda9927/627f901a65664534e05ec52af7e559db.jpg");
		//.setThumbnail(new File("C:/Users/Bastian/Pictures/kitten2.png"));
		channel.sendMessageEmbeds(embedMusic.build()).queue();
	}
	public static void PlayListEmbed(AudioPlaylist playlist, String link, MessageChannel channel)
	{
		int index = link.indexOf('=') + 1;
		int index1 = link.indexOf('&');
		if (index == 0)
		{
			index = link.lastIndexOf('/') + 1;
		}
		System.out.println(index);
		String thumbnail = link.substring(index,index1);
		System.out.println(thumbnail);
		thumbnail = "https://img.youtube.com/vi/" + thumbnail + "/maxresdefault.jpg";
		System.out.println(thumbnail);
		EmbedBuilder embedMusic = new EmbedBuilder()
				.setTitle(playlist.getName())
				.setDescription("В плейлисте: " + playlist.getTracks().size() + " треков")
				//.setAuthor("от " + playlist.getInfo().author)
				.addField("", link,true)
				//.addInlineField("An inline field", "More text")
				//.addInlineField("Another inline field", "Even more text")
				.setColor(2895667)
				.setFooter(MorgenLine(), "https://sun9-75.userapi.com/impg/GCjYWYxKOJibJF2E_voTY42dBbaVG8cuEb7U9g/yQqbKn9lCHQ.jpg?size=463x509&quality=95&sign=f75408c71da1900281d0a60c7508794c&c_uniq_tag=xw9dNUUaxrMtWQC_FjadXdlhZMDc_EyxDDtt-7E3Csk&type=album")
				.setImage(thumbnail);
		//.setThumbnail(new File("C:/Users/Bastian/Pictures/kitten2.png"));
		channel.sendMessageEmbeds(embedMusic.build()).setActionRow(ResPaus()).queue();
	}
	public static void MorgenEmbed(MessageChannel channel)
	{
		Random rnd = new Random();
		int r = rnd.nextInt(Utils.MorgenPhoto.MorgenLength());
		EmbedBuilder embedMusic = new EmbedBuilder()
				.setTitle("Какой ты сегодня Морген:")
				.setDescription(Utils.MorgenPhoto.MorgenGetStatus(r))
				.setAuthor("")
				//.addField("", link,true)
				//.addInlineField("An inline field", "More text")
				//.addInlineField("Another inline field", "Even more text")
				.setColor(2895667)
				.setFooter(MorgenLine(), "https://sun9-75.userapi.com/impg/GCjYWYxKOJibJF2E_voTY42dBbaVG8cuEb7U9g/yQqbKn9lCHQ.jpg?size=463x509&quality=95&sign=f75408c71da1900281d0a60c7508794c&c_uniq_tag=xw9dNUUaxrMtWQC_FjadXdlhZMDc_EyxDDtt-7E3Csk&type=album")
				.setImage(Utils.MorgenPhoto.MorgenGetPhoto(r));
		//.setThumbnail(new File("C:/Users/Bastian/Pictures/kitten2.png"));
		channel.sendMessageEmbeds(embedMusic.build()).queue();
	}

}
