package se.lexicon.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.springdatajpa.entity.ProductOrder;

import java.time.LocalDate;
import java.util.List;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Integer> {


    List<ProductOrder> findByOrderDate(LocalDate orderDate);

    List<ProductOrder> findByOrderItemsProductId(int productId);

    List<ProductOrder> findByOrderItemsProductName(String productName);

    List<ProductOrder> findByCustomerId(int customerId);


}
