package org.fasttrackit.phonebook.persistance;

import org.fasttrackit.phonebook.transfer.CreatePhonebookItemRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }


}
