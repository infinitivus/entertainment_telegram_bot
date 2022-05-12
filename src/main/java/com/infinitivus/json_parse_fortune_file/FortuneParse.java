package com.infinitivus.json_parse_fortune_file;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FortuneParse {
    private final String path = "src//main//java//com//infinitivus//json_parse_fortune_file//fortune.txt";
    Gson gson = new Gson();

    public String fortuneParse() {
        int number = 0;
        String text = null;
        try {
            number = fortuneRandomNumber(fortuneFileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),
                StandardCharsets.UTF_8))) {
            String line;
            int size = 1;
            while ((line = br.readLine()) != null) {
                if (size == number) {
                    Fortune fortune = gson.fromJson(line, Fortune.class);
                    text = fortune.getText();
                    break;
                } else {
                    size++;
                }
            }
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Ошибка, нет информации";
    }

    private int fortuneRandomNumber(int size) {
        return (int) ((Math.random() * (size - 1) + 1));
    }

    private int fortuneFileSize() throws IOException {
        return (int) Files.lines(Paths.get(path), Charset.defaultCharset()).count();
    }
}

