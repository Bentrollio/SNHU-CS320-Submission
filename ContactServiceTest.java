/* Alex Baires - SNHU CS-320
 * PROJECT ONE
 * ContactServiceTest.java
 *
 * JUNIT5 Test Suite - Designed to test the following:
 *
 * The contact service shall be able to add contacts with a unique ID.
 * The contact service shall be able to delete contacts per contact ID.
 * The contact service shall be able to update contact fields per contact ID.
 * The following fields are updatable:
 * firstName, lastName, Number, Address
 */
package org.alexbaires.test;
import org.alexbaires.main.ContactService;
import org.alexbaires.main.Contact;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

public class ContactServiceTest {

    private static ContactService contactService;

    @BeforeEach
    public void setupContactService() {
        contactService = new ContactService();
    }

    @Test
    @DisplayName("Should Add a Contact and Retrieve its Individual Details")
    public void testAddAndRetrieveContact() {
        contactService.addContact("Ivan", "Baires",
                "4078736418", "308 lake hills ln");
        assertFalse(contactService.getContactList().isEmpty());
        assertEquals(1, contactService.getContactList().size());

        String uniqueID = contactService.getContactList().keySet().iterator().next();
        Contact contact = contactService.retrieveContact(uniqueID);
        Assertions.assertNotNull(contact);
        assertEquals("Ivan", contact.getFirstName());
        assertEquals("Baires", contact.getLastName());
        assertEquals("4078736418", contact.getPhoneNumber());
        assertEquals("308 lake hills ln", contact.getMailingAddress());
    }

    @Test
    @DisplayName("New Contact Should Have a Unique ID Generated")
    public void testWhetherContactHasID() {
        contactService.addContact("Ivan", "Baires",
                "4078736418", "308 lake hills ln");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();
        assertFalse(contactService.retrieveContact(uniqueIDTest).getContactID().isEmpty());
    }

    @Test
    @DisplayName("Testing whether Generated ID is Unique")
    public void testUniqueContactID() {
        contactService.addContact("Aaron", "Judge", "7188675309",
                "Yankee Stadium, NYC");
        String firstUniqueIDTest = contactService.getContactList().keySet().iterator().next();

        contactService.addContact("Luke", "Skywalker", "8009992345", "Imperial City, Coruscant");
        String secondUniqueIDTest = contactService.getContactList().keySet().iterator().next();
        assertNotEquals(firstUniqueIDTest, secondUniqueIDTest);
    }

    @Test
    @DisplayName("Should Avoid Duplicate Contacts")
    public void testAvoidDuplicateContacts() {
        contactService.addContact("Aaron", "Judge", "7188675309",
                "Yankee Stadium, NYC");
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Aaron", "Judge", "7188675309",
                    "Yankee Stadium, NYC");
        });
    }

    @Test
    @DisplayName("Testing whether Multiple Contacts are Stored")
    public void testAddMultipleContacts() {

        contactService.addContact("Aaron", "Judge", "7188675309",
                "Yankee Stadium, NYC");
        contactService.addContact("Luke", "Skywalker", "8009992345", "Imperial City, Coruscant");
        assertEquals(2, contactService.getContactList().size());

        contactService.addContact("Ivan", "Baires", "4078736418", "308 lake hills ln");
        assertEquals(3, contactService.getContactList().size());
    }

    @Test
    @DisplayName("Should Delete a Contact")
    public void testDeleteContact() {
        contactService.addContact("Ivan", "Baires",
                "8631234567", "123 Sesame Street");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();
        contactService.deleteContact(uniqueIDTest);
        assertTrue(contactService.getContactList().isEmpty());
        assertEquals(0, contactService.getContactList().size());
    }
    
    @Test
    @DisplayName("Testing - Deletion of Multiple Contacts")
    public void testDeleteMultipleContacts() {
        contactService.addContact("Aaron", "Judge", "7188675309", "Yankee Stadium, NYC");
        contactService.addContact("Luke", "Skywalker", "8009992345", "Imperial City, Coruscant");
        contactService.addContact("Ivan", "Baires", "4078736418", "308 lake hills ln");

        Iterator<String> iterator = contactService.getContactList().keySet().iterator();
        String contactOneID = iterator.next();
        String contactTwoID = iterator.next();
        String contactThreeID = iterator.next();

        assertEquals(3, contactService.getContactList().size());

        // Deleting multiple tasks out of order
        contactService.deleteContact(contactThreeID);
        assertEquals(2, contactService.getContactList().size());
        contactService.deleteContact(contactOneID);
        assertEquals(1, contactService.getContactList().size());
        contactService.deleteContact(contactTwoID);
        assertTrue(contactService.getContactList().isEmpty());

    }

    @Test
    @DisplayName("Testing Invalid Contact Data Error-Handling")
    public void testInvalidContactData() {
        // Testing NULL first Name
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact(null, "Baires", "8631234567", "123 Sesame Street");
        });
        // Testing blank first name
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("", "Baires", "8631234567", "123 Sesame Street");
        });

        // Testing NULL last name
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", null, "8631234567", "123 Sesame Street");
        });
        // Testing blank last name
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", "", "8631234567", "123 Sesame Street");
        });
        // Testing NULL phone number
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", "Baires", null, "123 Sesame Street");
        });
        // Testing blank phone number
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", "Baires", "", "123 Sesame Street");
        });
        // Testing NULL address
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", "Baires", "8631234567", null);
        });
        //Testing blank address
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactService.addContact("Ivan", "Baires", "8631234567", "");
        });
    }

    @Test
    @DisplayName("Testing Updatable Contact Information")
    public void testUpdateContactInfo() {
        contactService.addContact("Ivan", "Baires", "8631234567", "123 Sesame Street");
        String uniqueIDTest = contactService.getContactList().keySet().iterator().next();

        // Updating First Name
        contactService.updateFirstName(uniqueIDTest, "Vanya");
        assertEquals("Vanya", contactService.retrieveContact(uniqueIDTest).getFirstName());

        // Updating Last Name
        contactService.updateLastName(uniqueIDTest, "The Dog");
        assertEquals("The Dog", contactService.retrieveContact(uniqueIDTest).getLastName());

        // Updating phone number
        contactService.updatePhoneNumber(uniqueIDTest, "5888800000");
        assertEquals("5888800000", contactService.retrieveContact(uniqueIDTest).getPhoneNumber());

        // Updating mailing address
        contactService.updateMailingAddress(uniqueIDTest, "1 Jumbo Ave");
        assertEquals("1 Jumbo Ave", contactService.getContactList().get(uniqueIDTest).getMailingAddress());
    }

    @Test
    @DisplayName("Testing for edge cases")
    public void testEdgeCases() {
        char[] data = new char[1000];
        Arrays.fill(data, 'a');
        String str = new String(data);
        // Testing very long input
        assertThrows(RuntimeException.class, () -> { contactService.addContact(str, str, "1234567890", str); });
        assertNotEquals(1, contactService.getContactList().size());

        // Testing special characters
        contactService.addContact("!@#$%^&*()", "!@#$%^&*()", "1234567890", "!@#$%^&*()");
        assertEquals(1, contactService.getContactList().size());

        // Testing special character for phone number
        assertThrows(RuntimeException.class, () -> { contactService.addContact("test", "test", "!@#$%^&*()", "test"); });

    }
}