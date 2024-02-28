package ua.dragunovskiy.parser;

import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
public class ImageParserToFile implements Parser {

    // This method parse urls for images by target site url it
    // can do it with some key, which can include in name of
    // image urls and with empty key. Parser can parse urls in
    // format png, jpg and gif
    @Override
    public List<String> parse(String url, String key) {
        List<String> imagesURLs = new ArrayList<>();
        Document document;
        try {
            document = Jsoup.connect(url).get();
            List<String> imagesUrlsForPngFromA = parseImageUrlsFromA(document, ".png");
            List<String> imagesUrlsForJpgFromA = parseImageUrlsFromA(document, ".jpg");
            List<String> imagesUrlsForGifFromA = parseImageUrlsFromA(document, ".gif");

            List<String> imagesUrlsForPngFromImg = parseImageUrlsFromImg(document, ".png");
            List<String> imagesUrlsForGifFromImg = parseImageUrlsFromImg(document, ".gif");
            List<String> imagesUrlsForJpgFromImg = parseImageUrlsFromImg(document, ".jpg");

            imagesURLs.addAll(imagesUrlsForPngFromA);
            imagesURLs.addAll(imagesUrlsForJpgFromA);
            imagesURLs.addAll(imagesUrlsForGifFromA);

            imagesURLs.addAll(imagesUrlsForPngFromImg);
            imagesURLs.addAll(imagesUrlsForGifFromImg);
            imagesURLs.addAll(imagesUrlsForJpgFromImg);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String imgURL : imagesURLs) {
            System.out.println(imgURL);
        }
        return imagesURLs.stream()
                .filter(image -> image.contains(key))
                .toList();
    }

    // This is auxiliary method for parsing urls from <img> attribute
    private List<String> parseImageUrlsFromImg(Document document, String format) {
        List<String> imageURLsForImg;
        Elements imagesForImg = document.select("img");
        imageURLsForImg = imagesForImg.stream()
                .map(image -> image.attr("abs:src"))
                .filter(image -> image.endsWith(format))
                .toList();
        return imageURLsForImg;
    }

    // This is auxiliary method for parsing urls from <a> attribute
    private List<String> parseImageUrlsFromA(Document document, String format) {
        List<String> imagesUrlsForJpg;
        Elements imagesFomA = document.select("a");
        imagesUrlsForJpg = imagesFomA.stream()
                .map(image -> image.attr("href"))
                .map(image -> "https:" + image)
                .filter(image -> image.endsWith(format))
                .distinct()
                .toList();
        return imagesUrlsForJpg;
    }
}
