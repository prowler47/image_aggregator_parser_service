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
        List<String> listUrls = parser.parse("https://ru.freepik.com", "");
        assertFalse(listUrls.isEmpty());
    }

    @Test
    public void countListOfUrls() {
        List<String> listUrls = parser.parse("https://ru.freepik.com", "");
        int expectedCount = 102;
        int actualCount = listUrls.size();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void keyWordTest() {
        String key = "flower";
        List<String> listUrls = parser.parse("https://ru.freepik.com", key);
        assertFalse(listUrls.isEmpty());
    }

    @Test
    public void typeOfImageTest() {
        String type = ".jpg";
        List<String> listOfUrls = parser.parse("https://ru.freepik.com", "");
        assertTrue(listOfUrls.stream().anyMatch(url -> url.endsWith(type)));
    }
}
