package org.moondrop.entity;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

public class Book extends LibraryObject {

    private URI uri;
    private Date latestDateUpdated;
    private int currentChapter;
    private int chaptersAvailable;
    private String[] tags;
    private String[] genres;

    public Book(String name, URI uri, int currentChapter, String[] tags, String[] genres) {
        super(name);
        this.uri = uri;
        this.currentChapter = currentChapter;
        this.tags = process_content(tags);
        this.genres = process_content(genres);
    }

    public Book(String name, int currentChapter) {
        super(name);
        this.currentChapter = currentChapter;
    }

    public Book(String name) {
        super(name);
    }

    public Book(String name, String description, URI uri, Date latestDateUpdated, int currentChapter, int chaptersAvailable, String[] tags, String[] genres) {
        super(name, description);
        this.uri = uri;
        this.latestDateUpdated = latestDateUpdated;
        this.currentChapter = currentChapter;
        this.chaptersAvailable = chaptersAvailable;
        this.tags = process_content(tags);
        this.genres = process_content(genres);
    }

    /**
     * Process incoming tags, by removing empty values and
     * make them all lower-case. Make sure duplicates are
     * removed
     * @param tags_to_be_processed the Strings to be processed
     * @return the processed content, or null if process failed.
     */
    private String[] process_content(String[] tags_to_be_processed) {
        String[] processed_tags = null;
        if(tags_to_be_processed != null && tags_to_be_processed.length > 0) {
            processed_tags = Arrays.stream(tags_to_be_processed)
                    .filter(s -> (s != null && s.length() > 0))
                    .map(String::toLowerCase)
                    .distinct()
                    .toArray(String[]::new);
        }
        return processed_tags;
    }

    /**
     * Method to update tags for the book instance
     * <br>
     * Validates that the existing and replacing tags are
     * the same size.
     * Updates each existing index with the replacing array's index.
     * <br>
     * @param existing_tags the array with existing tags to be updated
     * @param replacing_tags the array with values to replace with
     */
    public void updateTags(String[] existing_tags, String[] replacing_tags) {

        String[] processed_existing = process_content(existing_tags);
        String[] processed_replacing = process_content(replacing_tags);

        if (existing_tags == null) {
            return;
        }

        if (replacing_tags == null) {
            return;
        }

        if (processed_existing == null) {
            return;
        }

        if (processed_replacing == null) {
            return;
        }

        if (existing_tags.length != replacing_tags.length) {
            return;
        }

        int processed_existing_length = processed_existing.length;

        for (int i = 0; i < tags.length; i++) {
            for (int j = 0; j < processed_existing_length; j++) {
                if (tags[i].equals(processed_existing[j])) {
                    tags[i] = processed_replacing[j];
                    break;
                }
            }
        }

    }

    /**
     * method to remove n number of tags from the object.
     * Only removes existing tags.
     * @param tagsToRemove the tags to remove
     */
    public void removeFromTags(String... tagsToRemove) {

        LinkedList<String> tag_list = new LinkedList<>(Arrays.asList(tags));
        String[] process_incoming = process_content(tagsToRemove);
        for (String tag : process_incoming) {
            tag_list.remove(tag);
        }

        tags = tag_list.toArray(new String[0]);

    }
    /**
     * method to remove n number of genres from the object.
     * Only removes existing genres.
     * @param genresToRemove the genres to remove
     */
    public void removeFromGenres(String... genresToRemove) {

        LinkedList<String> genre_list = new LinkedList<>(Arrays.asList(genres));
        String[] process_incoming = process_content(genresToRemove);
        for (String tag : process_incoming) {
            genre_list.remove(tag);
        }

        genres = genre_list.toArray(new String[0]);

    }

    /**
     * Method to add n number of tags to the library object
     * @param tagsToAdd the tags to be added
     */
    public void addToTags(String... tagsToAdd) {

        int totalSize = tags.length + tagsToAdd.length;
        String[] tagsArray = new String[totalSize];

        System.arraycopy(tags, 0, tagsArray, 0, tags.length);

        int argumentIterator = 0;

        for(int j = tags.length; j < totalSize; j++) {
            tagsArray[j] = tagsToAdd[argumentIterator];
            argumentIterator++;
        }

        tags = tagsArray;

        this.tags = process_content(tags);

    }
    /**
     * Method to add n number of genres to the library object
     * @param genreToAdd the genres to be added
     */
    public void addToGenre(String... genreToAdd) {

        int totalSize = genres.length + genreToAdd.length;
        String[] genresArray = new String[totalSize];

        System.arraycopy(genres, 0, genresArray, 0, genres.length);

        int argumentIterator = 0;

        for(int j = genres.length; j < totalSize; j++) {
            genresArray[j] = genreToAdd[argumentIterator];
            argumentIterator++;
        }

        genres = genresArray;

        this.genres = process_content(genres);
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Date getLatestDateUpdated() {
        return latestDateUpdated;
    }

    public void setLatestDateUpdated(Date latestDateUpdated) {
        this.latestDateUpdated = latestDateUpdated;
    }

    public int getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }

    public int getChaptersAvailable() {
        return chaptersAvailable;
    }

    public void setChaptersAvailable(int chaptersAvailable) {
        this.chaptersAvailable = chaptersAvailable;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = process_content(tags);
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = process_content(genres);
    }

    @Override
    public String toString() {
        return "\nBook:" +
                "\nuri = " + uri +
                ", \nlatestDateUpdated = " + latestDateUpdated +
                ", \ncurrentChapter = " + currentChapter +
                ", \nchaptersAvailable = " + chaptersAvailable +
                ", \ntags = " + Arrays.toString(tags).replace("[", "").replace("]", "") +
                ", \ngenres = " + Arrays.toString(genres).replace("[", "").replace("]", "");
    }
}
