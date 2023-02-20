package Commands;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.Random;

public abstract class Utils {
    public static String DurationFormatLong(long Duration) {

        return DurationFormatUtils.formatDuration(Duration, "HH:mm:ss");
    }

    public static class MorgenPhoto {


        final static String[][] MorgenPhoto = {{"https://i.pinimg.com/originals/50/31/c2/5031c23f2ea31862cd853683c830ce5b.jpg", "Оружейный?"},
                {"https://cdnn1.img.sputniknews-uz.com/img/07e4/0c/0a/15572675_244:0:2975:2048_1920x0_80_0_0_32686ff563f8796598c3cde99fbc6691.jpg", "Довольный"},
                {"https://informing.ru/uploads/posts/2022-10/1665934792_2e5e9df32aba10435f7d66fa9eacdb9e24257afa.webp", "Задумчивый"},
                {"https://cdnn21.img.ria.ru/images/07e5/07/1b/1743148683_0:126:1080:936_1600x0_80_0_0_0db286f5186541507e5686e012fe5a2a.jpg", "Грустный"},
                {"https://pitaniemalysha.ru/wp-content/uploads/2022/02/morgenshtern-ukraina-1536x996.jpeg", "Суровый"},
                {"https://u.9111s.ru/uploads/202112/07/5f2ec1a642a98007d2e7151eff840ef4.jpg", "Болтливый"},
                {"https://phonoteka.org/uploads/posts/2022-09/1663807845_8-phonoteka-org-p-oboi-morgenshtern-instagram-8.jpg","Дикий"},
                {"https://tvcenter.ru/wp-content/uploads/2023/01/b6fdf84d284f44a9930c8a332c24.jpg","Дерзкий"},
                {"https://phonoteka.org/uploads/posts/2022-09/1663807871_5-phonoteka-org-p-oboi-morgenshtern-instagram-5.jpg","Модный"},
                {"https://dooralei.ru/wp-content/uploads/2021/09/morgenshtern-priedet-v-ukrainu.jpeg","Крутой"},
                {"https://icdn.lenta.ru/images/2021/04/29/17/20210429174044537/square_1280_b4000c9e016a94f4ad27a8c3b5745c7b.jpg", "Игривый"},
                {"https://s0.rbk.ru/v6_top_pics/resized/1440xH/media/img/8/88/756165027546888.jpg","Деловой"},
                {"https://static.mk.ru/upload/entities/2022/07/01/17/photoreportsImages/detailPicture/07/43/8c/bc/6aa41b9cb874627b33c2615ca5ac3862.jpg","Нацик"},
                {"https://static.news.ru/photo/d55c7fd2-c8e8-11eb-ab4d-96000091f725_1024.jpg","Гэнгста"},
                {"https://mir-s3-cdn-cf.behance.net/project_modules/1400/799501113573949.602ad7813b3c9.png","Дед-инсайд"},
                {"https://www.kleo.ru/img/news/FE01K.jpg", "Возмущённый"},
                {"https://uchastniki.com/wp-content/uploads/2021/01/morgenshtern-3.jpg","Молодой"},
                {"https://ic.pics.livejournal.com/misscaprizzz/85343571/691960/691960_original.jpg", "Богатый"},
                {"https://www.timeout.ru/wp-content/uploads/2021/08/screenshot_2021-08-30-11-48-02-01.png", "Боевой"},
                {"https://avatars.dzeninfra.ru/get-zen_doc/2907131/pub_5f7e1dc91e2da6289e11ab87_5f7e1dd91e2da6289e11c460/scale_1200", "Стрёмный"}};
        public static String MorgenGetPhoto(int r)
        {
            return MorgenPhoto[r][0];
        }
        public static String MorgenGetStatus(int r)
        {
            return MorgenPhoto[r][1];
        }
        public static int MorgenLength()
        {
            return MorgenPhoto.length;
        }

    }
}
