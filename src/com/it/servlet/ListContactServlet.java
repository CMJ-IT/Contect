package com.it.servlet;

import com.it.entity.Contact;
import com.it.service.ContactService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListContactServlet", urlPatterns = "/list")
public class ListContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用业务层
        ContactService contactService = new ContactService();
        //得到所有联系人
        List<Contact> contacts = contactService.findAllContacts();
        //将数据放在请求域中
        request.setAttribute("contacts", contacts);
        //转发给JSP
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

