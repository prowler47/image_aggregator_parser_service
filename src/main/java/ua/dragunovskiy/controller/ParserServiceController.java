package ua.dragunovskiy.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dragunovskiy.chain.SettingParserChain;
import ua.dragunovskiy.parser.Parser;
import ua.dragunovskiy.service.RabbitMQService;

import java.util.List;

@RestController
@RequestMapping("/rabbit")
public class ParserServiceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SettingParserChain settingParserChain;

    @Autowired
    private RabbitMQService rabbitMQService;

    // This method send list of urls to RabbitMQ queue
    @GetMapping("/send_list")
    public String testRabbitMqWithList() {
        Parser parser = settingParserChain.parserChain.getParserList().get(0);
        List<String> urls = parser.parse(rabbitMQService.getSiteUrl(), "");
        for (String URL : urls) {
            rabbitTemplate.convertAndSend("Test-exchange", "Test", URL);
        }
        return "Sending urls is success";
    }
}
