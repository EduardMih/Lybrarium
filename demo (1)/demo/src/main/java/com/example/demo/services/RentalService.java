package com.example.demo.services;

import com.example.demo.model.Book;
import com.example.demo.model.ComicBook;
import com.example.demo.model.Rental;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.ComicBookRepository;
import com.example.demo.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
public class RentalService {
    @Autowired
    private final RentalRepository rentalRepository;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final ComicBookRepository comicBookRepository;

    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository, ComicBookRepository comicBookRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.comicBookRepository = comicBookRepository;
    }

    public Rental rentBook(Rental rental)
    {


        if(bookIsAvailable(rental.getItemId()))
        {

            rental.setRentedDate(LocalDate.now());
            bookRepository.getOne(rental.getItemId()).setAvailable(false);
            bookRepository.save((bookRepository.getOne(rental.getItemId())));
            rentalRepository.save(rental);

            return rental;

        }

        return null;

    }

    private boolean bookIsAvailable(int id)
    {
        try
        {
            Book book = bookRepository.getOne(id);

            return book.isAvailable();

        }
        catch(EntityNotFoundException e)
        {

            return false;

        }



    }

    public int returnBook(Rental rental)
    {
        rental = rentalRepository.findRentedItem(rental.getItemId(), rental.getClientId());

        if(rental == null)

            return -1;

        rental.setReturnedDate(LocalDate.now());
        bookRepository.getOne(rental.getItemId()).setAvailable(true);
        bookRepository.save(bookRepository.getOne(rental.getItemId()));
        rentalRepository.save(rental);

        return 0;

    }

    public Rental rentComicBook(Rental rental)
    {
        if(comicBookIsAvailable(rental.getItemId()))
        {

            rental.setRentedDate(LocalDate.now());
            comicBookRepository.getOne(rental.getItemId()).setAvailable(false);
            comicBookRepository.save((comicBookRepository.getOne(rental.getItemId())));
            rentalRepository.save(rental);

            return rental;

        }

        return null;

    }

    private boolean comicBookIsAvailable(int id)
    {
        try
        {
            ComicBook comicBook = comicBookRepository.getOne(id);

            return comicBook.isAvailable();

        }

        catch(EntityNotFoundException e)
        {

            return false;

        }
    }

    public int returnComicBook(Rental rental)
    {
        rental = rentalRepository.findRentedItem(rental.getItemId(), rental.getClientId());

        if(rental == null)

            return -1;

        rental.setReturnedDate(LocalDate.now());
        comicBookRepository.getOne(rental.getItemId()).setAvailable(true);
        comicBookRepository.save(comicBookRepository.getOne(rental.getItemId()));
        rentalRepository.save(rental);

        return 0;

    }




}
