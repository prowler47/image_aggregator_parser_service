package ua.dragunovskiy.parser;

import java.util.List;

public interface Parser {
    List<String> parse(String url, String key);
}
