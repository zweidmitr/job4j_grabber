package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SqlRuParse {

    private static final String URL = "https://www.sql.ru/forum/job-offers/";

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
    }
}
