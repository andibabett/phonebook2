package org.fasttrackit.phonebook.persistance;

import org.fasttrackit.phonebook.domain.PhonebookItem;
import org.fasttrackit.phonebook.transfer.CreatePhonebookItemRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhonebookItemRepository {

    public void createPhonebookItem(CreatePhonebookItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO phone_book_item (firstName, lastName, phoneNumber) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }
    }

    public void updatePhonebookItem(long id, boolean done) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE phone_book_item SET done=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.setBoolean(2, done);

            preparedStatement.executeUpdate();
        }
    }

    public void deletePhonebookItem(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phone_book_item WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public List<PhonebookItem> getPhonebookItems() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id, firstName, lastName, phoneNumber, done FROM phone_book_item";

        List<PhonebookItem> phonebookItems = new ArrayList<>();

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                PhonebookItem phonebookItem = new PhonebookItem();

                phonebookItem.setId(resultSet.getLong("id"));
                phonebookItem.setFirstName(resultSet.getString("firstName"));
                phonebookItem.setLastName(resultSet.getString("lastName"));
                phonebookItem.setPhoneNumber(resultSet.getString("phoneNumber"));
                phonebookItem.setDone(resultSet.getBoolean("done"));

                phonebookItems.add(phonebookItem);
            }
        }
        return phonebookItems;
    }
}
