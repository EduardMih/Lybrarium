package com.example.demo.controllers;

import com.example.demo.model.Book;
import com.example.demo.model.ComicBook;
import com.example.demo.services.ComicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/comics")
public class ComicBookController {
    @Autowired
    private final ComicBookService comicBookService;

    public ComicBookController(ComicBookService comicBookService) {
        this.comicBookService = comicBookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ComicBook>> getComicBooks()
    {
        List<ComicBook> comicBooks = comicBookService.getAllComicBooks();

        return new ResponseEntity<>(comicBooks, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ComicBook> createComicBook(@RequestBody ComicBook comicBook)
    {
        ComicBook newComicBook = comicBookService.addComicBook(comicBook, comicBook.getSeries(), comicBook.getNumber());

        return new ResponseEntity<>(comicBook, new HttpHeaders(), HttpStatus.OK);

    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ComicBook> updateComicBook(@PathVariable int id, @RequestBody ComicBook comicBook)
    {
        ComicBook updatedComicBook = comicBookService.updateComicBook(id, comicBook.getSeries(), comicBook.getNumber());

        return new ResponseEntity<>(updatedComicBook, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable int id)
    {
        comicBookService.deleteComicBook(id);

        return new ResponseEntity<>("Deleted " + id, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/search/{searchField}", method = RequestMethod.GET)
    public ResponseEntity<List<ComicBook>> searchBooks(@PathVariable String searchField, @RequestParam(name = "s") String searchString)
    {
        if((!Objects.equals(searchField, "series")) &&
                (!Objects.equals(searchField, "number")) &&
                (!Objects.equals(searchField, "id")))

            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);

        List<ComicBook> comicBookList =comicBookService.searchComicBook(searchString, searchField);

        return new ResponseEntity<>(comicBookList, new HttpHeaders(), HttpStatus.OK);


    }
}
