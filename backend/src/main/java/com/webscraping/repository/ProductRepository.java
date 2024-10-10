package com.webscraping.repository;

import com.webscraping.data.Product;
import com.webscraping.data.TrackedProductSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select DISTINCT p.searchText from Product p")
    List<String> findDistinctSearchText();

    @Query("select DISTINCT new com.webscraping.data.TrackedProductSource(p.searchText,p.source) from Product p")
    List<TrackedProductSource> findDistinctTrackProduct();


    List<Product> findByTrackedTrue();

    List<Product> findBySearchTextLike(String searchText);

}
