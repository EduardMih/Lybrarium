package com.example.demo.model;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "books")
public class Book implements Comparable<Book>{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private boolean isAvailable = true;

    public int getId()
    {

        return id;

    }

    public String getTitle()
    {

        return title;

    }

    public String getAuthor()
    {

        return author;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable()
    {

        return isAvailable;

    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {

        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isAvailable=" + isAvailable +
                '}';

    }

    @Override
    public int compareTo(Book o)
    {

        return Comparator.comparing(Book::getTitle)
                .thenComparing(Book::getAuthor)
                .compare(this, o);

    }
}
