package se.lexicon.springdatajpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.springdatajpa.entity.AppUser;
import se.lexicon.springdatajpa.entity.OrderItem;
import se.lexicon.springdatajpa.entity.Product;
import se.lexicon.springdatajpa.entity.ProductOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@DataJpaTest
public class ProductOrderRepositoryTest {

    @Autowired
    ProductOrderRepository testObject;

    @Autowired
    // TestEntityManager allows us to use entityManager operations in tests
    TestEntityManager em;

    AppUser appUser1;
    Product product1;
    Product product2;
    OrderItem orderItem1;
    OrderItem orderItem2;
    OrderItem orderItem3;
    ProductOrder productOrder1;
    ProductOrder productOrder2;
    ProductOrder insertedProductOrder1;
    ProductOrder insertedProductOrder2;

    @BeforeEach
    public void setup() {
        appUser1 = em.persistAndFlush(new AppUser("Test", "Testsson", "test@test.se"));

        product1 = em.persistAndFlush(new Product("TestProduct 1", BigDecimal.valueOf(10)));
        product2 = em.persistAndFlush(new Product("TestProduct 2", BigDecimal.valueOf(10)));

        orderItem1 = em.persistAndFlush(new OrderItem(5, product1));
        orderItem2 = em.persistAndFlush(new OrderItem(3, product1));
        orderItem3 = em.persistAndFlush(new OrderItem(2, product2));

        productOrder1 = new ProductOrder(LocalDate.parse("2022-02-02"), LocalTime.parse("09:30"), appUser1);
        productOrder1.addOrderItem(orderItem1);
        productOrder1.addOrderItem(orderItem3);

        productOrder2 = new ProductOrder(LocalDate.parse("2022-01-01"), LocalTime.parse("10:30"), appUser1);
        productOrder2.addOrderItem(orderItem2);

        insertedProductOrder1 = testObject.save(productOrder1);
        insertedProductOrder2 = testObject.save(productOrder2);

    }

    @Test
    public void given_2022_02_02_should_return_list_size_1() {
        List<ProductOrder> result = testObject.findByOrderDate(LocalDate.parse("2022-02-02"));
        int expectedData = 1;
        int actualData = result.size();
        assertEquals(expectedData, actualData);
    }

    @Test
    public void given_productId_return_list_size_2() {
        List<ProductOrder> result = testObject.findByOrderItemsProductId(product1.getId());
        assertEquals(2, result.size());
    }

    @Test
    public void given_string_test_product_1_should_return_list_size_2() {
        List<ProductOrder> result = testObject.findByOrderItemsProductName("TestProduct 1");
        assertEquals(2, result.size());
    }

    @Test
    public void given_app_user_id_should_return_list_size_2(){
        List<ProductOrder> result = testObject.findByCustomerId(appUser1.getId());
        assertEquals(2, result.size());
    }

    @Test
    public void remove_item2_from_order2_should_return_null(){
        insertedProductOrder2.removeOrderItem(orderItem2);
        em.flush(); // commit changes triggering orphan removal
        OrderItem result = em.find(OrderItem.class, productOrder2.getId());
        assertNull(result);
    }

}
