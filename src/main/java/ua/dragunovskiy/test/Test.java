package ua.dragunovskiy.test;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.dragunovskiy.FilterParserChain;

@RestController()
@RequestMapping("/")
public class Test {
    @Autowired
    public FilterParserChain filterParserChain;

    @GetMapping("/test/{key}")
    public String test(@PathVariable("key") String key, HttpServletResponse response) {
        int imageCount = 0;
        int status = response.getStatus();
        System.out.println(status);
        filterParserChain.parserChain.getParserList()
                .forEach(parser -> parser.parse(filterParserChain.parserChain.getURL(), key));
        return "Done! ";
    }

    @GetMapping("/test2")
    public String test2() {
        filterParserChain.parserChain.getParserList()
                .forEach(parser -> parser.parse(filterParserChain.parserChain.getURL(), ""));
        return "Done! ";
    }
}
