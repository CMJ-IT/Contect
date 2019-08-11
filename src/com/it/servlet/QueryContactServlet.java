package com.it.servlet;

import com.it.entity.Contact;
import com.it.service.ContactService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QueryContactServlet",urlPatterns = "/query")
public class QueryContactServlet extends HttpServlet {
    private ContactService contactService = new ContactService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.得到提交过来的id的值
        String id = request.getParameter("id");
        //2. 通过id调用业务层查询1个联系人
        Contact contact = contactService.findById(Integer.parseInt(id));
        //3. 将联系人数据放在请求域中
        request.setAttribute("contact", contact);
        //将所有的地址放在一个数组中
        String address[] = {"广东","广西","湖南","海南","福建"};
        //放在请求域中
        request.setAttribute("address", address);
        System.out.println(contact);
        //4. 转发到update.jsp，并显示出来
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }
}

