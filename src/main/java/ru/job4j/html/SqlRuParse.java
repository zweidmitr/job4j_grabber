package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private static final String URL = "https://www.sql.ru/forum/job-offers";
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) throws Exception {
        List<Post> listPosts = new ArrayList<>();
        Document doc = Jsoup.connect(link).get();
        Elements row = doc.select(".postslisttopic");
        for (Element td : row) {
            listPosts.add(detail(td.child(0).attr("href")));
        }
        return null;
    }

    @Override
    public Post detail(String url) throws Exception {
        Post post = new Post();
        Document doc = Jsoup.connect(url).get();
        Element header = doc.select(".messageHeader").get(0);
        Element footer = doc.select(".msgFooter").get(0);
        Element description = doc.select(".msgBody").get(1);

        LocalDateTime created = dateTimeParser.parse(footer.text().split(" \\[")[0]);
        post.setTitle(header.text());
        post.setDescription(description.text());
        post.setCreated(created);
        post.setLink(url);
        return post;
    }

    public static void main(String[] args) throws Exception {
        int tempCount = 1;
        List<Element> linkList = new ArrayList<>();
        while (tempCount < 6) {
            Document doc = Jsoup.connect(URL + tempCount).get();
            Elements row = doc.select(".postslisttopic");

            for (Element td : row) {
                Element parent = td.parent();
                var job = parent.child(1).text();
                var author = parent.child(2).text();
                var time = parent.child(5).text();
                var shows = parent.child(4).text();
                var link = td.child(0);
                linkList.add(link);
                System.out.println("Вакансия: " + job);
                System.out.println("Автор: " + author);
                System.out.println("Дата: " + time);
                System.out.println("Просмотры: " + shows);
                System.out.println("Ссылка: " + link.attr("href"));
                System.out.println("===============================================");
            }
            tempCount++;
        }
        System.out.println("===============================================");
        System.out.println(linkList.size());
        System.out.println(tempCount);
        System.out.println("===============================================");

        DateTimeParser dateTime = new SqlRuDateTimeParser();
        SqlRuParse sqlRuParse = new SqlRuParse(dateTime);
        Post testPost = sqlRuParse
                .detail("https://www.sql.ru"
                        + "/forum/1340570"
                        + "/java-engineer-middle-senior-minsk-remote-fulltime");
        List<Post> listik = sqlRuParse.list(URL);
        System.out.println(testPost);
        System.out.println("===============================================");

    }
}
