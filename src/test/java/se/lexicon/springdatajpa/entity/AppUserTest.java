package se.lexicon.springdatajpa.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppUserTest {

    private AppUser testObject;

    @BeforeEach
    private void setup() {
        testObject = new AppUser(1, "Mehrdad", "Javan", "mehrdad.javan@lexicon.se");
    }

    @Test
    public void testObject_successfully_created() {
        assertEquals(1, testObject.getId());
        assertEquals("Mehrdad", testObject.getFirstName());
        assertEquals("Javan", testObject.getLastName());
        assertEquals("mehrdad.javan@lexicon.se", testObject.getEmail());
    }

    @Test
    public void test_equal_and_hashcode_true() {
        AppUser copy = new AppUser(1, "Mehrdad", "Javan", "mehrdad.javan@lexicon.se");
        assertEquals(copy, testObject);
        assertEquals(copy.hashCode(), testObject.hashCode());
    }

    @Test
    public void test_toString_contains(){
        String toString = testObject.toString();

        assertTrue(
                toString.contains("1") &&
                        toString.contains("Mehrdad") &&
                        toString.contains("Javan") &&
                        toString.contains("mehrdad.javan@lexicon.se")

                );
    }

}
