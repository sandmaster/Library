package org.moondrop.entity;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;

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
        this.tags = tags;
        this.genres = genres;
    }

    public Book(String name, int currentChapter) {
        super(name);
        this.currentChapter = currentChapter;
    }

    public Book(String name, String description, URI uri, Date latestDateUpdated, int currentChapter, int chaptersAvailable, String[] tags, String[] genres) {
        super(name, description);
        this.uri = uri;
        this.latestDateUpdated = latestDateUpdated;
        this.currentChapter = currentChapter;
        this.chaptersAvailable = chaptersAvailable;
        this.tags = tags;
        this.genres = genres;
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
        this.tags = tags;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "\nBook:" +
                "\nuri = " + uri +
                ", \nlatestDateUpdated = " + latestDateUpdated +
                ", \ncurrentChapter = " + currentChapter +
                ", \nchaptersAvailable = " + chaptersAvailable +
                ", \ntags = " + Arrays.toString(tags) +
                ", \ngenres = " + Arrays.toString(genres);
    }
}
