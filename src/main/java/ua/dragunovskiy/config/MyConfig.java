package ua.dragunovskiy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.dragunovskiy.FilterParserChain;
import ua.dragunovskiy.ParserChain;
import ua.dragunovskiy.parser.Parser;

@Configuration
public class MyConfig {
    @Autowired
    public Parser parser;

    @Bean
    public FilterParserChain configure(ParserChain parserChain) {
        parserChain
                .setParser(parser)
                .setUrl("https://ru.freepik.com");

        return parserChain.build();
    }
}
