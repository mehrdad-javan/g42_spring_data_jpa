package se.lexicon.springdatajpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.springdatajpa.entity.AppUser;

import java.util.Optional;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository testObject;

    @BeforeEach
    public void setup() {
        testObject.save(new AppUser("Test", "Testsson", "test@test.se"));
    }

    @Test
    public void given_email_findByEmail_return_optional_present() {
        AppUser expectedContent = new AppUser(1, "Test", "Testsson", "test@test.se");

        Optional<AppUser> result = testObject.findByEmailIgnoreCase("TEST@TEST.SE");
        assertTrue(result.isPresent());
        assertEquals(expectedContent, result.get());
    }

    //...


}
