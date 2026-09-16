package lab2;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class PersonTest {
    @Test
    void testEqualsAndHashCodeContract() {
        EqualsVerifier.simple().forClass(Person.class).verify();
    }
}
