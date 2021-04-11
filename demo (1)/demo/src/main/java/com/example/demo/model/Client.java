package com.example.demo.model;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client implements Comparable<Client>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public int getId()
    {

        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName()
    {

        return name;

    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Client o)
    {

        return name.compareTo(o.getName());

    }
}
