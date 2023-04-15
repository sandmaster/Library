package org.moondrop;

import org.moondrop.entity.Book;

import java.net.URI;

public class Main {
    public static void main(String[] args) {

        Book book = new Book("Playboy", URI.create("https://novelbin.com/b/playboy-cultivator-in-the-apocalypse"),
                130, new String[]{"Apocalypse"}, new String[]{"Harem", "Action", "Adventure"});

        System.out.println(book.toString());

    }

}