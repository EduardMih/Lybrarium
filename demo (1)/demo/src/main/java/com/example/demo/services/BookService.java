package com.example.demo.services;

import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.BookRepository;

import java.util.*;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks()
    {
        List<Book> books = new ArrayList<>();

        books = bookRepository.findAll();

        Collections.sort(books);

        return books;

    }


    public Book addBook(Book book, String title, String author)
    {
        book.setTitle(title);
        book.setAuthor(author);
        book = bookRepository.save(book);

        return book;

    }

    public Book updateBook(int id, String title, String author)
    {
        Book updatedBook = bookRepository.getOne(id);
        updatedBook.setTitle(title);
        updatedBook.setAuthor(author);
        updatedBook = bookRepository.save(updatedBook);

        return updatedBook;

    }

    public void deleteBook(int id)
    {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBook(String searchString, String field)
    {
        if (Objects.equals(field, "title"))
        {

            return bookRepository.findByTitleContainingIgnoreCase(searchString);

        }

        if(Objects.equals(field, "author"))
        {

            return bookRepository.findByAuthorContainingIgnoreCase(searchString);
        }

        return bookRepository.findById(Integer.parseInt(searchString));

    }


}
