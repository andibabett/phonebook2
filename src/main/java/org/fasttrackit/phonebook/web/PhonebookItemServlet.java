package org.fasttrackit.phonebook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.phonebook.config.ObjectMapperConfiguration;
import org.fasttrackit.phonebook.domain.PhonebookItem;
import org.fasttrackit.phonebook.service.PhonebookItemService;
import org.fasttrackit.phonebook.transfer.CreatePhonebookItemRequest;
import org.fasttrackit.phonebook.transfer.UpdatePhonebookItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/phone_book_items")
public class PhonebookItemServlet extends HttpServlet {

    private PhonebookItemService phonebookItemService = new PhonebookItemService();

    public PhonebookItemServlet() throws SQLException, IOException, ClassNotFoundException {
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreatePhonebookItemRequest request =
                ObjectMapperConfiguration.getObjectMapper()
                        .readValue(req.getReader(), CreatePhonebookItemRequest.class);

        try {
            phonebookItemService.createPhonebookItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error:" + e.getMessage());
        }
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter("id");
//
//        try {
//            phonebookItemService.deletePhonebookItem(Long.parseLong(id));
//        } catch (SQLException | ClassNotFoundException e) {
//            resp.sendError(500, "Internal server error" + e.getMessage());
//        }
//    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] id = req.getParameterValues("id");
        long[]ids=new long[100];
        for(int i=0; i<=id.length;i++)
        {
            ids[i]=Long.parseLong(id[i]);
        }
        try {
            phonebookItemService.deleteMoreContacts(ids);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        UpdatePhonebookItemRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), UpdatePhonebookItemRequest.class);

        try {
            phonebookItemService.updatePhonebookItem(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error" + e.getMessage());
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            List<PhonebookItem> phonebookItems = phonebookItemService.getPhonebookItems();
//
//            String response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(phonebookItems);
//
//            resp.getWriter().print(response);
//
//        } catch (SQLException | ClassNotFoundException e) {
//            resp.sendError(500, "Internal server error" + e.getMessage());
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        try {
            List<PhonebookItem> phonebookItems = phonebookItemService.getPhonebookItemByName(firstName, lastName);

            String response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(phonebookItems);

            resp.getWriter().print(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error:" + e.getMessage());
        }
    }

    private void setAccessControlHeaders(HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");

    }

}
