package com.hspedu.chapter14.hw01;

import java.util.ArrayList;
import java.util.Collections;

public class hw01 {
    public static void main(String[] args) {
        News news1 = new News("新闻1111111");
        News news2 = new News("新闻2222222222222222222222222222222222222222");
        ArrayList<News> newsList = new ArrayList<News>();
        newsList.add(news1);
        newsList.add(news2);
        Collections.reverse(newsList);
        for (News news : newsList) {
            if (news.getTitle().length() > 15) {
                System.out.println(news.getTitle().substring(0, 15) + "...");
            } else {
                System.out.println(news);
            }
        }
    }
}

class News {
    private String title;
    private String content;

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return title;
    }
}