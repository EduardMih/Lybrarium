package com.example.demo.repositories;

import com.example.demo.model.Book;
import com.example.demo.model.ComicBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface ComicBookRepository extends JpaRepository<ComicBook, Integer> {
    List<ComicBook> findBySeriesContainingIgnoreCase(String series);
    List<ComicBook> findByNumber(int number);
    List<ComicBook> findById(int id);
}
