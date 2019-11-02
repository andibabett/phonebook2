package org.fasttrackit.phonebook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.phonebook.service.PhonebookItemService;
import org.fasttrackit.phonebook.transfer.CreatePhonebookItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/phone_book_items")
public class PhonebookItemServlet extends HttpServlet {

    private PhonebookItemService phonebookItemService = new PhonebookItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreatePhonebookItemRequest request = objectMapper.readValue(req.getReader(), CreatePhonebookItemRequest.class);

        try {
            phonebookItemService.createPhonebookItem(request);
        } catch (SQLException | ClassNotFoundException e) {
           resp.sendError(500,"Internal server error:" + e.getMessage());
        }
    }
}
