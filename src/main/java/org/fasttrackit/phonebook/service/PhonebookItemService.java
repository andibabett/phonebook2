package org.fasttrackit.phonebook.service;


import org.fasttrackit.phonebook.domain.PhonebookItem;
import org.fasttrackit.phonebook.persistance.PhonebookItemRepository;
import org.fasttrackit.phonebook.transfer.CreatePhonebookItemRequest;
import org.fasttrackit.phonebook.transfer.UpdatePhonebookItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhonebookItemService {

    private PhonebookItemRepository phonebookItemRepository = new PhonebookItemRepository();

    public PhonebookItemService() throws SQLException, IOException, ClassNotFoundException {
    }

    public void createPhonebookItem(CreatePhonebookItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating phone_book_item:" + request);
        phonebookItemRepository.createPhonebookItem(request);
    }

    public void updatePhonebookItem(long id, UpdatePhonebookItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating phone_book_item:" + request);
        phonebookItemRepository.updatePhonebookItem(id, request.isDone());
    }

    public void deletePhonebookItem(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting item:" + id);
        phonebookItemRepository.deletePhonebookItem(id);
    }

    public void deleteMoreContacts (long[] id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting contacts...");
        phonebookItemRepository.deleteMoreContacts(id);
    }

    public List<PhonebookItem> getPhonebookItems() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retriving phonebook-items...");
        return phonebookItemRepository.getPhonebookItems();
    }

    public List <PhonebookItem> getPhonebookItemByName (String firstName, String lastName) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrivering phonebook...");
        return phonebookItemRepository.getPhonebookItemsByName(firstName,lastName);
    }
}
