package com.infinitivus;

import com.infinitivus.get_inspiration_database.GetInspiration;
import com.infinitivus.html_parse_anecdote_web.AnecdoteParse;
import com.infinitivus.html_parse_horoscope_web.HoroscopeParse;
import com.infinitivus.json_parse_fortune_file.FortuneParse;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentBot extends TelegramLongPollingBot {
    private final AnecdoteParse parseAnecdote = new AnecdoteParse();
    private final HoroscopeParse horoscope = new HoroscopeParse();
    private final FortuneParse parseFortune = new FortuneParse();
    private final GetInspiration inspiration = new GetInspiration();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("Веселить")) {
                    perform(message(update.getMessage().getChatId().toString(), parseAnecdote.anecdote()));
                } else if (update.getMessage().getText().equals("Гороскоп")) {
                    SendMessage out = message(update.getMessage().getChatId().toString(), "Выберете ваш знак зодиака");
                    out.setReplyMarkup(horoscope.setInlineButton());
                    perform(out);
                } else if (update.getMessage().getText().equals("Погадать")) {
                    perform(message(update.getMessage().getChatId().toString(), parseFortune.fortuneParse()));
                } else if (update.getMessage().getText().equals("Вдохновить")) {
                    perform(message(update.getMessage().getChatId().toString(), inspiration.getInspiration()));
                } else {
                    SendMessage out = message(update.getMessage().getChatId().toString(), update.getMessage().getText());
                    setReplyButtons(out);
                    perform(out);
                }
            }
        } else if (update.hasCallbackQuery()) {
            HoroscopeParse parseHoroscope = new HoroscopeParse();
            perform(message(update.getCallbackQuery().getMessage().getChatId().toString(),
                    parseHoroscope.horoscope(update.getCallbackQuery().getData())));
        }
    }

    private synchronized SendMessage message(String chatId, String line) {
        SendMessage outMessage = new SendMessage();   //Создаем исходящее сообщение
        outMessage.enableMarkdown(true);
        outMessage.setChatId(chatId);  //Указываем в какой чат будем отправлять сообщение(в тот же чат, откуда пришло входящее сообщение)
        outMessage.setText(line);     //Указываем текст сообщения
        return outMessage;
    }

    private synchronized void perform(SendMessage outMessage) {
        try {
            execute(outMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private synchronized void setReplyButtons(SendMessage sendMessage) {
        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Веселить"));
        keyboardFirstRow.add(new KeyboardButton("Вдохновить"));
        //Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        //Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Погадать"));
        keyboardSecondRow.add(new KeyboardButton("Гороскоп"));
        keyboard.add(keyboardFirstRow); // Добавляем первую строку клавиатуры в список
        keyboard.add(keyboardSecondRow); //  Добавляем вторую строку клавиатуры в список
        replyKeyboardMarkup.setKeyboard(keyboard); // и устанавливаем этот список нашей клавиатуре
    }

    @Override
    public String getBotUsername() {
        return "NameBot";
    }

    @Override
    public String getBotToken() {
        return "Token";
    }
}
