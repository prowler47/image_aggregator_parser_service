package ua.dragunovskiy.chain;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ua.dragunovskiy.parser.Parser;

import java.util.ArrayList;
import java.util.List;

// This class provide instance in configuration class for setting parser
// and uri of html page for parsing. It contains list of parsers, setter
// getter for parser and method build
@Component
@Getter
public class ParserChain {
    private final List<Parser> parserList = new ArrayList<>();
    private String URL = "";

    public ParserChain setParser(Parser parser) {
        parserList.add(parser);
        return this;
    }

    public ParserChain setUrl(String url) {
        this.URL = url;
        return this;
    }

    public SettingParserChain build() {
        return new SettingParserChain(this);
    }
}
