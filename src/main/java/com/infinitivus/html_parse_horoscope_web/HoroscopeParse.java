package com.infinitivus.html_parse_horoscope_web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HoroscopeParse {

    public String horoscope(String link) {
        Document doc;
        String horoscope = null;
        try {
            doc = Jsoup.connect("https://1001goroskop.ru/?znak=" + link).get();
            horoscope = doc.select("p").first().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return horoscope;
    }

    public InlineKeyboardMarkup setInlineButton() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonAries = new InlineKeyboardButton();
        buttonAries.setText("Овен");
        buttonAries.setCallbackData("aries");
        InlineKeyboardButton buttonTaurus = new InlineKeyboardButton();
        buttonTaurus.setText("Телец");
        buttonTaurus.setCallbackData("taurus");
        InlineKeyboardButton buttonTwins = new InlineKeyboardButton();
        buttonTwins.setText("Близнецы");
        buttonTwins.setCallbackData("gemini");
        InlineKeyboardButton buttonCancer = new InlineKeyboardButton();
        buttonCancer.setText("Рак");
        buttonCancer.setCallbackData("cancer");
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(buttonAries);
        row1.add(buttonTaurus);
        row1.add(buttonTwins);
        row1.add(buttonCancer);
        InlineKeyboardButton buttonLion = new InlineKeyboardButton();
        buttonLion.setText("Лев");
        buttonLion.setCallbackData("leo");
        InlineKeyboardButton buttonVirgin = new InlineKeyboardButton();
        buttonVirgin.setText("Дева");
        buttonVirgin.setCallbackData("virgo");
        InlineKeyboardButton buttonScales = new InlineKeyboardButton();
        buttonScales.setText("Весы");
        buttonScales.setCallbackData("libra");
        InlineKeyboardButton buttonScorpio = new InlineKeyboardButton();
        buttonScorpio.setText("Скорпион");
        buttonScorpio.setCallbackData("scorpio");
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(buttonLion);
        row2.add(buttonVirgin);
        row2.add(buttonScales);
        row2.add(buttonScorpio);
        InlineKeyboardButton buttonSagittarius = new InlineKeyboardButton();
        buttonSagittarius.setText("Стрелец");
        buttonSagittarius.setCallbackData("sagittarius");
        InlineKeyboardButton buttonCapricorn = new InlineKeyboardButton();
        buttonCapricorn.setText("Козерог");
        buttonCapricorn.setCallbackData("capricorn");
        InlineKeyboardButton buttonAquarius = new InlineKeyboardButton();
        buttonAquarius.setText("Водолей");
        buttonAquarius.setCallbackData("aquarius");
        InlineKeyboardButton buttonFish = new InlineKeyboardButton();
        buttonFish.setText("Рыбы");
        buttonFish.setCallbackData("pisces");
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(buttonSagittarius);
        row3.add(buttonCapricorn);
        row3.add(buttonAquarius);
        row3.add(buttonFish);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        markup.setKeyboard(rowList);
        return markup;
    }
}
