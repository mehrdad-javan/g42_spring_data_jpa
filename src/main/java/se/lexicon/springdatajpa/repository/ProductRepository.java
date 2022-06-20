package se.lexicon.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.springdatajpa.entity.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {


    List<Product> findByNameStartingWithIgnoreCase(String productName);
}
