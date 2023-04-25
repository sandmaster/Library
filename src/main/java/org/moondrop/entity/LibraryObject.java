package org.moondrop.entity;

/**
 * Super class extended to subclasses for each type of object
 * represented as a LibraryObject. Could be a book, movie or something else.
 */
public class LibraryObject {

    private final String libraryName;
    private final String libraryDescription;

    protected LibraryObject(String name) {
        this.libraryName = name;
        this.libraryDescription = "";
    }

    protected LibraryObject(String name, String description) {
        this.libraryName = name;
        this.libraryDescription = description;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public String getLibraryDescription() {
        return libraryDescription;
    }
}
