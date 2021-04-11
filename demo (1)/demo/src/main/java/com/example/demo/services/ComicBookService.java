package com.example.demo.services;

import com.example.demo.model.Book;
import com.example.demo.model.ComicBook;
import com.example.demo.repositories.ComicBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ComicBookService {
    @Autowired
    private final ComicBookRepository comicBookRepository;

    public ComicBookService(ComicBookRepository comicBookRepository) {
        this.comicBookRepository = comicBookRepository;
    }

    public List<ComicBook> getAllComicBooks()
    {
        List<ComicBook> comicBooks = new ArrayList<>();

        comicBooks = comicBookRepository.findAll();

        Collections.sort(comicBooks);

        return comicBooks;

    }

    public ComicBook addComicBook(ComicBook comicBook, String series, int number)
    {
        comicBook.setSeries(series);
        comicBook.setNumber(number);
        comicBook = comicBookRepository.save(comicBook);

        return comicBook;

    }

    public ComicBook updateComicBook(int id, String series, int number)
    {
        ComicBook updatedComicBook = comicBookRepository.getOne(id);
        updatedComicBook.setSeries(series);
        updatedComicBook.setNumber(number);
        updatedComicBook = comicBookRepository.save(updatedComicBook);

        return updatedComicBook;

    }

    public void deleteComicBook(int id)
    {
        comicBookRepository.deleteById(id);
    }

    public List<ComicBook> searchComicBook(String searchString, String field)
    {
        if (Objects.equals(field, "series"))
        {

            return comicBookRepository.findBySeriesContainingIgnoreCase(searchString);

        }

        if(Objects.equals(field, "number"))
        {

            return comicBookRepository.findByNumber(Integer.parseInt(searchString));
        }

        return comicBookRepository.findById(Integer.parseInt(searchString));

    }
}
