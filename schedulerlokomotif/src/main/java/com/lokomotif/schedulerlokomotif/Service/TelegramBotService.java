package com.lokomotif.schedulerlokomotif.Service;

import com.lokomotif.schedulerlokomotif.Model.SummaryLoko;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import static org.springframework.jdbc.datasource.init.DatabasePopulatorUtils.execute;

@Service
public class TelegramBotService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.chat-id}")
    private String chatId;

    public void sendTelegramMessage(String message) {
        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage?chat_id=" + chatId + "&text=" + message;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
    }

}
