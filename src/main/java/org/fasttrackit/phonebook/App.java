package org.fasttrackit.phonebook;

import org.fasttrackit.phonebook.domain.PhonebookItem;
import org.fasttrackit.phonebook.persistance.PhonebookItemRepository;
import org.fasttrackit.phonebook.transfer.CreatePhonebookItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        CreatePhonebookItemRequest request = new CreatePhonebookItemRequest();
        request.setFirstName("Learn");
        request.setLastName("JDBC");
        request.setPhoneNumber("0123456789");

        request = new CreatePhonebookItemRequest();
        request.setFirstName("andi");
        request.setLastName("babett");
        request.setPhoneNumber("0123456799");

        PhonebookItemRepository phonebookItemRepository = new PhonebookItemRepository();
        phonebookItemRepository.createPhonebookItem(request);

        phonebookItemRepository.updatePhonebookItem(1,true);
        phonebookItemRepository.deletePhonebookItem(2);

        List <PhonebookItem> phonebookItems = phonebookItemRepository.getPhonebookItems();
        System.out.println(phonebookItems);
    }
}