package com.example.demo.repositories;

import com.example.demo.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query(value = "SELECT * FROM rental r WHERE r.item_id = :itemId AND r.client_id = :clientId  AND r.returned_date IS NULL;", nativeQuery = true)
    Rental findRentedItem(@Param("itemId") int itemId, @Param("clientId") int clientId);
}
