package ua.dragunovskiy.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dragunovskiy.chain.ParserChain;

// This class need to configure ParserChain instance in configuration class
@Component
public class SettingParserChain {

    @Autowired
    public ParserChain parserChain;
    public SettingParserChain(ParserChain parserChain) {
        this.parserChain = parserChain;
    }

}
