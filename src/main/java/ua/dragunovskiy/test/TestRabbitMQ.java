package ua.dragunovskiy.test;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dragunovskiy.Message;

@RestController
@RequestMapping("/rabbit")
public class TestRabbitMQ {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String testRabbit() {
        String message = "Hi from parser service";
        rabbitTemplate.convertAndSend("Test-exchange","Test", message);
        //rabbitTemplate.convertAndSend("Test", message);
        return "Success";
    }
}
