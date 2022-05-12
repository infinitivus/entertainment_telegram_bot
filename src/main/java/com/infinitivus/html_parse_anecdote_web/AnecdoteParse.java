package com.infinitivus.html_parse_anecdote_web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class AnecdoteParse {
    public String anecdote() {
        Document doc;
        String anecdote=null;
        try {
            doc = Jsoup.connect("https://www.anekdot.ru/random/anekdot").get();
            anecdote = doc.select("div.text").first().text();
        }catch (IOException e){
            e.printStackTrace();
        }
        return anecdote;
    }
}
