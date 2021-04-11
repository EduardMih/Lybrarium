package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int itemId;
    private int clientId;
    private LocalDate rentedDate;
    private LocalDate returnedDate;

    public int getId()
    {

        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId()
    {

        return itemId;

    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getClientId()
    {

        return clientId;

    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getRentedDate()
    {

        return rentedDate;

    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public LocalDate getReturnedDate()
    {

        return returnedDate;

    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}
