package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");

        for (Element td : row) {
            Element parent = td.parent();
            var job = parent.child(1).text();
            var author = parent.child(2).text();
            var time = parent.child(5).text();
            var shows = parent.child(4).text();
            var link = td.child(0);
            System.out.println("Вакансия: " + job);
            System.out.println("Автор: " + author);
            System.out.println("Дата: " + time);
            System.out.println("Просмотры: " + shows);
            System.out.println("Ссылка: " + link.attr("href"));
            System.out.println("===============================================");
        }
    }
}
