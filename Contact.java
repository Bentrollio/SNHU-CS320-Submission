/*
 * PROJECT ONE
 * Contact.java
 *
 * Contains Contact Object information.
 *
 * Alex Baires
 * SNHU CS-320
 * 8-11-24
 */

package org.alexbaires.main;

public class Contact {

    final String contactID; // contactID not updatable
    String firstName;
    String lastName;
    String phoneNumber;
    String mailingAddress;

    public Contact(String contactID, String firstName, String lastName, String phoneNumber, String mailingAddress) {
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mailingAddress = mailingAddress;

        verifyContactID();
        verifyFirstName();
        verifyLastName();
        verifyPhoneNumber();
        verifyMailingAddress();
    }

    public void setContactID(String contactID) {
        throw new UnsupportedOperationException("Contact ID Not updatable");
    }

    // Retrieves contactID
    public String getContactID() {
        return contactID;
    }

    // Verifies that the contact ID is neither null, blank nor longer than 10 characters
    public void verifyContactID() {
        if (this.contactID == null || this.contactID.isEmpty()) {
            throw new RuntimeException("Contact ID string is null or empty");
        }

        if (this.contactID.length() > 10) {
            throw new RuntimeException("Contact ID string cannot be longer than 10 characters");
        }
    }

    // Updates and retrieves the customer's first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        verifyFirstName();
    }

    public String getFirstName() {
        return firstName;
    }

    // Verifies that the first name is neither null, blank nor longer than 10 characters
    public void verifyFirstName() {
        if(this.firstName == null || this.firstName.isEmpty()) {
            throw new RuntimeException("First name field is NULL or empty");
        }
        if (this.firstName.length() > 10) {
            throw new RuntimeException("First name field must be 10 characters or less");
        }
    }

    // Updates and retrieves the customer's last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
        verifyLastName();
    }

    public String getLastName() {
        return lastName;
    }

    // Verifies that the last name is neither null, blank nor longer than 10 characters
    public void verifyLastName() {
        if(this.lastName == null || this.lastName.isEmpty()) {
            throw new RuntimeException("Last name field is NULL or empty");
        }
        if (this.lastName.length() > 10) {
            throw new RuntimeException("Last name field must be 10 characters or less");
        }
    }

    // Updates and retrieves the customer's phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        verifyPhoneNumber();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Verifies that the phone number is neither null nor blank, and ensures it is 10 digits long
    public void verifyPhoneNumber() {
        if (this.phoneNumber == null || this.phoneNumber.isEmpty()) {
            throw new RuntimeException("Phone number is null or empty");
        }

        if (this.phoneNumber.length() != 10) {
            throw new RuntimeException("Phone number length must be 10 digits");
        }

        try {
            Double.parseDouble(this.phoneNumber);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Phone number has invalid characters");
        }
    }

    // Updates and retrieves the customer's mailing address
    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
        verifyMailingAddress();
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    // Verifies that the mailing address is neither null nor empty
    public void verifyMailingAddress() {
        if(this.mailingAddress == null || this.mailingAddress.isEmpty()) {
            throw new RuntimeException("Mailing address is null or empty");
        }

        if (this.mailingAddress.length() > 30) {
            throw new RuntimeException("Mailing address length must be no longer than 30 characters");
        }
    }
}
