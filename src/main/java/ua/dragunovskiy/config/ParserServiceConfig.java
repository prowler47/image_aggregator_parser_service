package ua.dragunovskiy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.dragunovskiy.chain.SettingParserChain;
import ua.dragunovskiy.chain.ParserChain;
import ua.dragunovskiy.parser.Parser;


// This is configuration class for more flexible settings. It can
// set any parser and url for parsing
@Configuration
public class ParserServiceConfig {

    // It is just one parser, but in future versions can be
    // created and set any another parser
    @Autowired
    public Parser parser;

    // This method create bean of ParserChain with setting of parser
    // and url for parsing
    @Bean
    public SettingParserChain configure(ParserChain parserChain) {
        parserChain
                .setParser(parser)
                .setUrl("https://www.freepik.com");
        return parserChain.build();
    }
}
