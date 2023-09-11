package dev.regis.rest.models.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonTest {
    
    @Test
    public void testGetId() {
        Person person = new Person();
        person.setId(1L);
        assertEquals(1L, person.getId());
    }

    @Test
    public void testGetName() {
        Person person = new Person();
        person.setName("John Doe");
        assertEquals("John Doe", person.getName());
    }
}
