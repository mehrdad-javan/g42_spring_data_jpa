package se.lexicon.springdatajpa.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.springdatajpa.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository testObject;

    private List<Product> data(){
        return new ArrayList<>(Arrays.asList(
                new Product("JPA Book1", BigDecimal.valueOf(399)),
                new Product("JPA Book2", BigDecimal.valueOf(799)),
                new Product("OCA", BigDecimal.valueOf(500))
        ));
    }

    @BeforeEach
    public void setup(){
        testObject.saveAll(data());
    }

    @Test
    public void test_given_JPA_findByNameStartingWithIgnoreCase_should_return_list_size_2(){
        String productName = "jpa";

        List<Product> result = testObject.findByNameStartingWithIgnoreCase(productName);
        assertEquals(2, result.size());
    }
}
