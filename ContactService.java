/*
 * PROJECT ONE
 * ContactService.java
 *
 * Creates and stores individual contacts with their details.
 *
 * Alex Baires
 * SNHU CS-320
 * 8-11-24
 */

package org.alexbaires.main;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ContactService {

    HashMap<String, Contact> contactList = new HashMap<>();

    public void addContact(String firstName, String lastName, String phone, String address) {
        String uniqueID = getHash(firstName, lastName);
        Contact contact = new Contact(uniqueID, firstName, lastName, phone, address);
        checkExistingContacts(contact.getContactID());
        contactList.put(uniqueID, contact);
    }

    public void deleteContact(String uniqueID) {
        contactList.remove(uniqueID);
    }

    public void checkExistingContacts(String key) {
        if(contactList.containsKey(key)) {
            throw new IllegalArgumentException("Contact already exists");
        }
    }

    // Hash map holding each contact list
    public HashMap<String, Contact> getContactList() {
        return contactList;
    }

    public Contact retrieveContact(String uniqueID) {
        return contactList.get(uniqueID);
    }

    public void updateFirstName(String uniqueID, String firstName) {
        Contact temp = contactList.get(uniqueID);
        temp.setFirstName(firstName);
    }

    public void updateLastName(String uniqueID, String lastName) {
        Contact temp = contactList.get(uniqueID);
        temp.setLastName(lastName);
    }

    public void updateMailingAddress(String uniqueID, String mailingAddress) {
        Contact temp = contactList.get(uniqueID);
        temp.setMailingAddress(mailingAddress);
    }

    public void updatePhoneNumber(String uniqueID, String phoneNumber) {
        Contact temp = contactList.get(uniqueID);
        temp.setPhoneNumber(phoneNumber);
    }

    /**
     * bytesToHex
     *
     * @param bytes - A byte array that will be converted into a HexString
     * @return hex - A string containing the HexString converted message as output.
     */
    protected String bytesToHex(byte[] bytes) {
        String hex;

        // Converts byte array into HexString object
        StringBuilder hexString = new StringBuilder();

        for (byte aByte : bytes) {
            hexString.append(String.format("%02x", aByte));
        }
        hex = hexString.toString();
        return hex;
    }

    /**
     * generateCheckSum
     *
     * Adapted from:
     * https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm#:~:text=You%20can%20generate%20the%20message,digest%20using%20the%20digest%20method.
     *
     * @param firstName, lastName - 2 strings to be concatenated
     * and converted into a SHA-256 checksum. Function only retrieves the first 10 digits.
     * @return checkSum - A string containing the HexString converted message as output.
     */
    protected String getHash(String firstName, String lastName) {
        String combinedString = firstName + lastName;
        String checkSum = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(combinedString.getBytes());
            byte[] digest = md.digest();

            checkSum = bytesToHex(digest);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return checkSum.substring(0, 10);
    }
}
