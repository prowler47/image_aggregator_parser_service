package ua.dragunovskiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilterParserChain {

    @Autowired
    public ParserChain parserChain;
    public FilterParserChain(ParserChain parserChain) {
        this.parserChain = parserChain;
    }

}
