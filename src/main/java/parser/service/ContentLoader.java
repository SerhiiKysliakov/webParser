package parser.service;

import org.jsoup.Jsoup;

import java.io.IOException;

public class ContentLoader {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
            +" AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
    public static final String REFFER_VALUE = "https://www.aboutyou.de/c/maenner/bekleidung-20290";
    public static final String COOKIE_NAME = "_uetsid";
    public static final String COOKIE_VALUE = "f7a9cdb0e46f11eb802ad5f3857b55da";

    public String getContent(String url) throws IOException {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent(USER_AGENT)
                .cookie(COOKIE_NAME, COOKIE_VALUE)
                .referrer(REFFER_VALUE)
                .execute().body();
    }
}
