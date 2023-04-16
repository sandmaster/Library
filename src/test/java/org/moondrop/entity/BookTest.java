package org.moondrop.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;

public class BookTest {

    @Test
    @DisplayName("Constructor with arguments")
    public void testCreation_of_book(){

        // Test with 1 argument
        Book bookZero = new Book("First Book");
        Assertions.assertEquals("First Book", bookZero.getLibraryName());

        // Test with 2 arguments
        Book bookOne = new Book("mocked_book_1", 100);

        Assertions.assertEquals("mocked_book_1", bookOne.getLibraryName());
        Assertions.assertEquals(100, bookOne.getCurrentChapter());

        // Test with 5 arguments
        String[] tags = new String[]{"Video Game"};
        String[] genre = new String[]{"Action", "Adventure"};
        URI uri;
        try {
            uri = new URI("https://www.webnovelpub.com/novel/my-legendary-class-is-husband-of-deathwill-sisters-29111137");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Book bookTwo = new Book("mocked_book_2", uri, 100, tags, genre);

        Assertions.assertEquals("mocked_book_2", bookTwo.getLibraryName());
        Assertions.assertEquals("https://www.webnovelpub.com/novel/my-legendary-class-is-husband-of-deathwill-sisters-29111137", bookTwo.getUri().toString());
        Assertions.assertEquals(100, bookTwo.getCurrentChapter());
        Assertions.assertEquals("[video game]", Arrays.toString(bookTwo.getTags()));
        Assertions.assertEquals("[action, adventure]", Arrays.toString(bookTwo.getGenres()));

        // Test with all arguments
        Date date = new Date();
        Book bookThree = new Book("mocked_book_3", "This is a mocked book, no other details are available",
                uri, date, 10, 200, tags, genre);

        Assertions.assertEquals("mocked_book_3", bookThree.getLibraryName());
        Assertions.assertEquals("This is a mocked book, no other details are available", bookThree.getLibraryDescription());
        Assertions.assertEquals(date, bookThree.getLatestDateUpdated());
        Assertions.assertEquals("https://www.webnovelpub.com/novel/my-legendary-class-is-husband-of-deathwill-sisters-29111137", bookThree.getUri().toString());
        Assertions.assertEquals(10, bookThree.getCurrentChapter());
        Assertions.assertEquals(200, bookThree.getChaptersAvailable());
        Assertions.assertEquals("[video game]", Arrays.toString(bookThree.getTags()));
        Assertions.assertEquals("[action, adventure]", Arrays.toString(bookThree.getGenres()));
    }

    @Test
    @DisplayName("Test to check whether updateTags method is working with correct input")
    public void testUpdateTagsValidInput() {
        // Setup objects
        Book temp_book = new Book("TagBook", 1);
        temp_book.setTags(new String[] {"Java", "Spring"});

        Assertions.assertEquals("[java, spring]", Arrays.toString(temp_book.getTags()));

        // Execute test
        String[] updatedTags = new String[]{"Java", "Spring"};
        String[] replacingTags = new String[]{"spring", "python"};
        temp_book.updateTags(updatedTags, replacingTags);

        // Check results
        Assertions.assertEquals("[spring, python]", Arrays.toString(temp_book.getTags()));
    }

    @Test
    @DisplayName("Test to check whether updateTags method is working with incorrect input, should keep current data")
    public void testUpdateTagsInvalidInput() {
        // Setup objects
        Book temp_book = new Book("TagBook", 1);
        temp_book.setTags(new String[] {"Java", "Spring"});

        Assertions.assertEquals("[java, spring]", Arrays.toString(temp_book.getTags()));

        // Execute test 1 - empty string array
        String[] updatedTags = new String[]{};
        String[] replacingTags = new String[]{};
        temp_book.updateTags(updatedTags, replacingTags);

        Assertions.assertEquals("[java, spring]", Arrays.toString(temp_book.getTags()));

        // Execute test 2 - null as incoming value
        temp_book.updateTags(null, null);

        Assertions.assertEquals("[java, spring]", Arrays.toString(temp_book.getTags()));

        // Execute test 3 - multiple values with null and empty strings within
        updatedTags = new String[]{"Point", null, "Website", "", null, "Stream"};
        temp_book.setTags(updatedTags);
        replacingTags = new String[]{"Pencil", null, "Backend", "", null, "Steamer"};
        temp_book.updateTags(updatedTags, replacingTags);

        Assertions.assertEquals("[pencil, backend, steamer]", Arrays.toString(temp_book.getTags()));

        // Execute test 4 - duplicated values, including case insensitive strings
        updatedTags = new String[]{"Point", "point"};
        temp_book.setTags(updatedTags);
        replacingTags = new String[]{"Pointer", "pointer"};
        temp_book.updateTags(updatedTags, replacingTags);

        Assertions.assertEquals("[pointer]", Arrays.toString(temp_book.getTags()));
    }

    @Test
    @DisplayName("Test to check that the method addToTags is working as intended")
    public void testAddTags() {
        // Setup objects
        Book book = new Book("Tags", 1);
        book.setTags(new String[]{"fantasy", "adventure"});

        Assertions.assertEquals("[fantasy, adventure]", Arrays.toString(book.getTags()));

        book.addToTags("Urban");

        Assertions.assertEquals("[fantasy, adventure, urban]", Arrays.toString(book.getTags()));

        book.addToTags("RPG", "");

        Assertions.assertEquals("[fantasy, adventure, urban, rpg]", Arrays.toString(book.getTags()));

        book.addToTags(null, "MMORPG");

        Assertions.assertEquals("[fantasy, adventure, urban, rpg, mmorpg]", Arrays.toString(book.getTags()));

    }

    @Test
    @DisplayName("test to remove existing tags")
    public void testRemoveFromTags() {
        String[] tags = new String[]{"Video Game", "MMORPG"};
        String[] genre = new String[]{"Action", "Adventure"};
        URI uri;
        try {
            uri = new URI("https://www.webnovelpub.com/novel/my-legendary-class-is-husband-of-deathwill-sisters-29111137");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Book temp_book = new Book("Java for dummies", "Learning guide for Java", uri, new Date(), 1, 20, tags, genre);

        Assertions.assertEquals("[video game, mmorpg]", Arrays.toString(temp_book.getTags()));

        temp_book.removeFromTags("video game");

        Assertions.assertEquals("[mmorpg]", Arrays.toString(temp_book.getTags()));

        // Test include null value as well as existing
        temp_book.removeFromTags(null, "mmorpg", null);

        Assertions.assertEquals("[]", Arrays.toString(temp_book.getTags()));
    }

    @Test
    @DisplayName("test to remove non existing tags from the library object")
    public void testRemoveFromTagsNonExisting() {
        String[] tags = new String[]{"Video Game", "MMORPG"};
        String[] genre = new String[]{"Action", "Adventure"};
        URI uri;
        try {
            uri = new URI("https://www.webnovelpub.com/novel/my-legendary-class-is-husband-of-deathwill-sisters-29111137");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Book temp_book = new Book("Java for dummies", "Learning guide for Java", uri, new Date(), 1, 20, tags, genre);

        Assertions.assertEquals("[video game, mmorpg]", Arrays.toString(temp_book.getTags()));

        temp_book.removeFromTags("Adventure", "video game");

        Assertions.assertEquals("[mmorpg]", Arrays.toString(temp_book.getTags()));
    }

    @Test
    @DisplayName("Test is to check the method addToGenre is working as intended")
    public void testAddGenre() {
        Book book = new Book("Genres", 1);
        book.setGenres(new String[]{"fantasy", "adventure"});

        Assertions.assertEquals("[fantasy, adventure]", Arrays.toString(book.getGenres()));

        book.addToGenre("Urban");

        Assertions.assertEquals("[fantasy, adventure, urban]", Arrays.toString(book.getGenres()));

        book.addToGenre("RPG", "");

        Assertions.assertEquals("[fantasy, adventure, urban, rpg]", Arrays.toString(book.getGenres()));

        book.addToGenre(null, "MMORPG");

        Assertions.assertEquals("[fantasy, adventure, urban, rpg, mmorpg]", Arrays.toString(book.getGenres()));

    }

    @Test
    @DisplayName("test to remove existing tags")
    public void testRemoveFromGenres() {
        String[] genre = new String[]{"Video Game", "MMORPG"};
        String[] tags = new String[]{"Action", "Adventure"};
        URI uri;
        try {
            uri = new URI("https://www.webnovelpub.com/novel/my-legendary-class-is-husband-of-deathwill-sisters-29111137");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Book temp_book = new Book("Java for dummies", "Learning guide for Java", uri, new Date(), 1, 20, tags, genre);

        Assertions.assertEquals("[video game, mmorpg]", Arrays.toString(temp_book.getGenres()));

        temp_book.removeFromGenres("video game");

        Assertions.assertEquals("[mmorpg]", Arrays.toString(temp_book.getGenres()));

        // Test include null value as well as existing
        temp_book.removeFromGenres(null, "mmorpg", null);

        Assertions.assertEquals("[]", Arrays.toString(temp_book.getGenres()));
    }

    @Test
    @DisplayName("test to remove non existing tags from the library object")
    public void testRemoveFromGenresNonExisting() {
        String[] genre = new String[]{"Video Game", "MMORPG"};
        String[] tags = new String[]{"Action", "Adventure"};
        URI uri;
        try {
            uri = new URI("https://www.webnovelpub.com/novel/my-legendary-class-is-husband-of-deathwill-sisters-29111137");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Book temp_book = new Book("Java for dummies", "Learning guide for Java", uri, new Date(), 1, 20, tags, genre);

        Assertions.assertEquals("[video game, mmorpg]", Arrays.toString(temp_book.getGenres()));

        temp_book.removeFromGenres("Adventure", "video game");

        Assertions.assertEquals("[mmorpg]", Arrays.toString(temp_book.getGenres()));
    }

}
