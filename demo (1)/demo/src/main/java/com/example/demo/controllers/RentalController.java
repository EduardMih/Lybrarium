package com.example.demo.controllers;
import com.example.demo.model.Book;
import com.example.demo.model.Rental;
import com.example.demo.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rental")
public class RentalController {
    @Autowired
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @RequestMapping(path = "/books", method = RequestMethod.POST)
    public ResponseEntity<String> rentBook(@RequestBody Rental rental)
    {
        Rental rented = rentalService.rentBook(rental);

        if(rented != null)

            return new ResponseEntity<>("Book rented successfully", new HttpHeaders(), HttpStatus.OK);

        return new ResponseEntity<>("Book not available", new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/books", method = RequestMethod.PUT)
    public ResponseEntity<String> returnBook(@RequestBody Rental rental)
    {
        int status = rentalService.returnBook(rental);

        if(status == -1)

            return new ResponseEntity<>("That book wasn't rented by that user or wasn't rented at all", new HttpHeaders(), HttpStatus.OK);


        return new ResponseEntity<>("Book returned successfully", new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/comicBooks", method = RequestMethod.POST)
    public ResponseEntity<String> rentComicBook(@RequestBody Rental rental)
    {
        Rental rented = rentalService.rentComicBook(rental);

        if(rented != null)

            return new ResponseEntity<>("ComicBook rented successfully", new HttpHeaders(), HttpStatus.OK);

        return new ResponseEntity<>("ComicBook not available", new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(path = "/comicBooks", method = RequestMethod.PUT)
    public ResponseEntity<String> returnComicBook(@RequestBody Rental rental)
    {
        int status = rentalService.returnComicBook(rental);

        if(status == -1)

            return new ResponseEntity<>("That ComicBook wasn't rented by that user or wasn't rented at all", new HttpHeaders(), HttpStatus.OK);


        return new ResponseEntity<>("ComicBook returned successfully", new HttpHeaders(), HttpStatus.OK);

    }
}
