package be.pxl.ja;

import be.pxl.ja.streamingservice.model.Content;
import be.pxl.ja.streamingservice.model.Movie;
import be.pxl.ja.streamingservice.model.Rating;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ContentRepositoryTest {
    Set<Content> contentSet = new ContentRepository().getContentSet();

    @Test
    void unableToAddDuplicatesToContentSet() {
        int size = contentSet.size();
        contentSet.add(new Movie("test", Rating.OLDER_KIDS));
        contentSet.add(new Movie("test", Rating.OLDER_KIDS));
        assertEquals(size + 1, contentSet.size());
    }
}
