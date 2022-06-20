package se.lexicon.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.springdatajpa.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

}
