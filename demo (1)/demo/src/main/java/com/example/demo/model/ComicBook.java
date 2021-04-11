package com.example.demo.model;
import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "comicbooks")
public class ComicBook implements Comparable<ComicBook>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String series;
    private int number;
    private boolean isAvailable = true;

    public int getId()
    {

        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeries()
    {

        return series;

    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getNumber()
    {

        return number;

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAvailable()
    {

        return isAvailable;

    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public int compareTo(ComicBook o)
    {

        return Comparator.comparing(ComicBook::getNumber)
                .thenComparing((o1, o2) -> o2.getNumber() - o1.getNumber())
                .compare(this, o);


    }
}
