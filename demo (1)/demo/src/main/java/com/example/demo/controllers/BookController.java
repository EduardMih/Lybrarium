package com.example.demo.controllers;

import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.BookService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks()
    {
        List<Book> books = bookService.getAllBooks();

        return new ResponseEntity<>(books, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/books", method = RequestMethod.POST)
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        Book newBook = bookService.addBook(book, book.getTitle(), book.getAuthor());

        return new ResponseEntity<>(newBook, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(path = "/books/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book)
    {
        Book updatedBook = bookService.updateBook(id, book.getTitle(), book.getAuthor());

        return new ResponseEntity<>(updatedBook, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable int id)
    {
        bookService.deleteBook(id);

        return new ResponseEntity("Deleted" + id, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/books/search/{searchField}", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> searchBooks(@PathVariable String searchField, @RequestParam(name = "s") String searchString)
    {
        if((!Objects.equals(searchField, "title")) &&
                (!Objects.equals(searchField, "author")) &&
                (!Objects.equals(searchField, "id")))

            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);

        List<Book> booksList = bookService.searchBook(searchString, searchField);

        return new ResponseEntity<>(booksList, new HttpHeaders(), HttpStatus.OK);

    }
}
