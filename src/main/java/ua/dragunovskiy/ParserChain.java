package ua.dragunovskiy;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ua.dragunovskiy.parser.Parser;

import java.util.ArrayList;
import java.util.List;

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

    public FilterParserChain build() {
        return new FilterParserChain(this);
    }
}
