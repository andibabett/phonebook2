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

    public void createPhonebookItem(CreatePhonebookItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating phonebook-item:" + request);
        phonebookItemRepository.createPhonebookItem(request);
    }

    public void updatePhonebookItem(long id, UpdatePhonebookItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating phonebook-item:" + request);
        phonebookItemRepository.updatePhonebookItem(id, request.isDone());
    }

    public void deletePhonebookItem(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting item:" + id);
        phonebookItemRepository.deletePhonebookItem(id);
    }

    public List<PhonebookItem> getPhonebookItems() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retriving phonebook-items...");
        return phonebookItemRepository.getPhonebookItems();
    }

}
