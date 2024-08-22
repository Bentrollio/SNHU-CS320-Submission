/* Alex Baires - SNHU CS-320
 * PROJECT ONE
 * ContactTest.java
 *
 * JUNIT5 Test Suite - Designed to test the following:
 *
 * The contact object shall have a required unique contact ID string that cannot be longer than 10 characters.
 * The contact ID shall not be null and shall not be updatable.
 * The contact object shall have a required firstName String field that cannot be longer than 10 characters.
 * The firstName field shall not be null.
 * The contact object shall have a required lastName String field that cannot be longer than 10 characters.
 * The lastName field shall not be null.
 * The contact object shall have a required phone String field that must be exactly 10 digits.
 * The phone field shall not be null.
 * The contact object shall have a required address field that must be no longer than 30 characters.
 * The address field shall not be null.
 */
package org.alexbaires.test;
import org.alexbaires.main.Contact;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

public class ContactTest {
    private static Contact contact;

    @Test
    @DisplayName("Testing Contact ID Requirements - Not longer than 10 characters, not NULL or empty")
    public void testContactIDRequirements() {
        // Test Contact ID longer than 10 characters
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("12345678901",
                "Ivan", "Baires", "8008675309", "123 Sesame Street"));
        // Test a NULL Contact ID
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact(null,
                "Ivan", "Baires", "8008675309", "123 Sesame Street"));
        //Test an empty Contact ID
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("",
                "Ivan", "Baires", "8008675309", "123 Sesame Street"));
    }
    
    @Test
    @DisplayName("Testing Contact Object Setters/Getters")
    public void testContactObjectSetters() {
    	contact = new Contact("1", "Ivan", "Baires", "8008675300","123 Sesame Street");
    	String updatedFirstName = "Vanya", updatedLastName = "Dog", updatedPhone = "8008882341", updatedAddress = "Anytown, USA";
    	contact.setFirstName(updatedFirstName);
    	Assertions.assertSame(contact.getFirstName(), updatedFirstName);
    	contact.setLastName(updatedLastName);
    	Assertions.assertSame(contact.getLastName(), updatedLastName);
    	contact.setPhoneNumber(updatedPhone);
    	Assertions.assertSame(contact.getPhoneNumber(), updatedPhone);
    	contact.setMailingAddress(updatedAddress);
    	Assertions.assertSame(contact.getMailingAddress(), updatedAddress);
    }

    @Test
    @DisplayName("Contact ID shall not be updatable")
    public void testContactIDNotUpdatable() {
        contact = new Contact("1", "Ivan", "Baires", "8008675309",
                "123 Sesame Street");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> contact.setContactID("2"));
    }

    @Test
    @DisplayName("Testing First Name Requirements - Not longer than 10 characters, not NULL or empty")
    public void testFirstNameRequirements() {
        // Testing that First Name is not longer than 10 characters
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bartholomew", "Simpson", "8005557246", "742 Evergreen Terrace"));
        // Test a NULL First Name field.
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                null, "Simpson", "8005557246", "742 Evergreen Terrace"));
        // Test an empty First Name field
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "", "Simpson", "8005557246", "742 Evergreen Terrace"));
    }

    @Test
    @DisplayName("Testing Last Name Requirements - Not longer than 10 characters, not NULL or empty")
    public void testLastNameLength() {
        // Test that Last Name is not longer than 10 characters
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bart", "Simpsonsons", "8005557246", "742 Evergreen Terrace"));
        // Test a NULL Last Name field
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bart", null, "8005557246", "742 Evergreen Terrace"));
        // Test an empty Last Name field
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bart", "", "8005557246", "742 Evergreen Terrace"));
    }

    @Test
    @DisplayName("Testing Phone Number Requirements - must have exactly 10 characters, not null, not empty")
    public void testPhoneNumberRequirements() {
        contact = new Contact("1", "Bart", "Simpson", "8005557246", "742 Evergreen Terrace");

        // EDGE Case: 11-digit phone number
        Assertions.assertThrows(RuntimeException.class, () -> contact.setPhoneNumber("18005557246"));
        // EDGE Case: 1-digit phone number
        Assertions.assertThrows(RuntimeException.class, () -> contact.setPhoneNumber("1"));
        // EDGE Case: 10 characters with letters instead of numbers
        Assertions.assertThrows(RuntimeException.class, () -> contact.setPhoneNumber("800555BOOT"));
        // Edge Case: 9 characters
        Assertions.assertThrows(RuntimeException.class, () -> contact.setPhoneNumber("800555726"));
        // Edge Case: Only special characters
        Assertions.assertThrows(RuntimeException.class, () -> contact.setPhoneNumber("!@#$%^&*()"));
        // Test a NULL Phone Number field
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bart", "Simpson", null, "742 Evergreen Terrace"));
        // Test an empty Phone Number field
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bart", "Simpson", "", "742 Evergreen Terrace"));
    }

    @Test
    @DisplayName("Testing Address Requirements - Not longer than 30 characters, not NULL or empty")
    public void testAddressRequirements() {
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bart", "Simpson", "8005557246", "742 Evergreen Terrace Springfield, Unknown, USA"));
        // Test a NULL Address field
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bart", "Simpson", "8005557246", null));
        // Test an empty Address field
        Assertions.assertThrows(RuntimeException.class, () -> contact = new Contact("1",
                "Bart", "Simpson", "8005557246", ""));
    }
}