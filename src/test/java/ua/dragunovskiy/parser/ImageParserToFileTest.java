package ua.dragunovskiy.parser;

import org.junit.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class ImageParserToFileTest {

    @Mock
    Parser parser = new ImageParserToFile();

    @Test
    public void nullListOfUrlsTest() {
        List<String> listUrls = parser.parse("https://www.freepik.com", "");
        assertFalse(listUrls.isEmpty());
    }

    @Test
    public void countListOfUrlsTest() {
        List<String> listUrls = parser.parse("https://www.freepik.com", "");
        int expectedCount = 106;
        int actualCount = listUrls.size();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void keyWordTest() {
        String key = "flower";
        List<String> listUrls = parser.parse("https://www.freepik.com", key);
        assertFalse(listUrls.isEmpty());
    }

    @Test
    public void typeOfJpgTest() {
        String type = ".jpg";
        List<String> listOfUrls = parser.parse("https://www.freepik.com", "");
        assertTrue(listOfUrls.stream().anyMatch(url -> url.endsWith(type)));
    }

    @Test
    public void typeOfPngTest() {
        String type = ".png";
        List<String> listOfUrls = parser.parse("https://www.freepik.com", "");
        assertTrue(listOfUrls.stream().anyMatch(url -> url.endsWith(type)));
    }

    @Test
    public void typeOfGifTest() {
        String type = ".gif";
        List<String> listOfUrls = parser.parse("https://www.freepik.com", "");
        assertFalse(listOfUrls.stream().anyMatch(url -> url.endsWith(type)));
    }
}
