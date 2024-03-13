package ua.dragunovskiy.service;

import lombok.Getter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Getter
public class RabbitMQService {

    private String siteUrl;

    private String key;

    @RabbitListener(queues = "Site_url")
    public void getSiteUrl(String message) {
        siteUrl = message;
        System.out.println(message);
    }

    @RabbitListener(queues = "key_parser")
    public void getKey(String message) {
        key = message;
    }
}
