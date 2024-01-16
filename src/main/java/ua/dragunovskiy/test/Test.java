package ua.dragunovskiy.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dragunovskiy.parser.Parser;

@RestController()
@RequestMapping("/")
public class Test {

    private String URL = "https://boards.4chan.org/a/";
//    private String URL = "https://sinoptik.ua";

    public Parser parser = new Parser();

    @GetMapping("/test")
    public String test() {
        parser.downloadImagesToFile(parser.parseURLs(URL));
        return "test";
    }
}
