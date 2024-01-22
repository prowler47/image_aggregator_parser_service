package ua.dragunovskiy.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dragunovskiy.FilterParserChain;
import ua.dragunovskiy.parser.ImageParser;
import ua.dragunovskiy.parser.Parser;

@RestController()
@RequestMapping("/")
public class Test {

    //private String URL = "https://boards.4chan.org/a/";
    //private String URL = "https://sinoptik.ua";
    //private String URL = "https://ru.freepik.com/search?format=search&last_filter=query&last_value=girls&query=girls";
    private String URL = "https://ru.freepik.com";

    @Autowired
    public FilterParserChain filterParserChain;

    @GetMapping("/test")
    public String test() {
//        imageParser.downloadImagesToFile(imageParser.parseURLs(URL, ""));
        filterParserChain.parserChain.getParserList()
                .forEach(parser -> parser.parseURLs(filterParserChain.parserChain.getURL(), ""));
        return "Done!";
    }
}
