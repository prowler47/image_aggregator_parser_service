package ua.dragunovskiy.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ImageParser implements Parser {

    @Override
    public void parseURLs(String url, String key) {
        List<String> imagesURLs = new ArrayList<>();
        Document document;
        try {
            document = Jsoup.connect(url).get();
            List<String> imagesUrlsForPngFromA = parseImageUrlsFromA(document, ".png", url);
            List<String> imagesUrlsForJpgFromA = parseImageUrlsFromA(document, ".jpg", url);
            List<String> imagesUrlsForGifFromA = parseImageUrlsFromA(document, ".gif", url);

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
        List<String> urls = imagesURLs.stream()
                .filter(image -> image.contains(key))
                .toList();

        downloadImagesToFile(urls);
//        return imagesURLs;
    }

    public List<byte[]> downloadImagesToList(List<String> imageURLs) {
        List<byte[]> imageDataList = new ArrayList<>();
        for (String imageURL : imageURLs) {
            try {
                URL url = new URL(imageURL);
                InputStream inputStream = url.openStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int read;
                byte[] data = new byte[1024 * 1024];
                while ((read = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, read);
                }
                inputStream.close();
                buffer.flush();

                imageDataList.add(buffer.toByteArray());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        for (byte[] data : imageDataList) {
            System.out.println(Arrays.toString(data));
        }
        return imageDataList;
    }

    private void downloadImagesToFile(List<String> imageURLs) {
        String suffix = "";
        for (String imageURL : imageURLs) {
            try {
                URL url = new URL(imageURL);
                InputStream inputStream = url.openStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int read;
                byte[] data = new byte[5 * 1024 * 1024];
                while ((read = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, read);
                }
                inputStream.close();
                buffer.flush();

                if (imageURL.endsWith(".png")) {
                    suffix = ".png";
                }
                if (imageURL.endsWith(".jpg")) {
                    suffix = ".jpg";
                }
                if (imageURL.endsWith(".gif")) {
                    suffix = ".gif";
                }
                File file = File.createTempFile(imageURL, suffix, new File("/Users/mac/Documents/tmp/"));
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(buffer.toByteArray());
                fileOutputStream.close();

            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private List<String> parseImageUrlsFromImg(Document document, String format) {
        List<String> imageURLsForImg;
        Elements imagesForImg = document.select("img");
        imageURLsForImg = imagesForImg.stream()
                .map(image -> image.attr("abs:src"))
                .filter(image -> image.endsWith(format))
                .toList();
//        for (String imageURL : imageURLsForImg) {
//            System.out.println(imageURL);
//        }
        return imageURLsForImg;
    }

     //without domain name
    private List<String> parseImageUrlsFromA(Document document, String format, String URL) {
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

    // with domain name
//        private List<String> parseImageUrlsFromA(Document document, String format, String URL) {
//        List<String> imagesUrlsForJpg;
//        String shortUrl = URL.substring(8, URL.length() - 1);
//        int indexLast = shortUrl.indexOf("/");
//        String domainName = shortUrl.substring(0, indexLast);
//        Elements imagesFomA = document.select("a");
//        imagesUrlsForJpg = imagesFomA.stream()
//                .map(image -> image.attr("href"))
//                .map(image -> "https://" + domainName + image)
//                .filter(image -> image.endsWith(format))
//                .distinct()
//                .toList();
//        return imagesUrlsForJpg;
//    }
}
