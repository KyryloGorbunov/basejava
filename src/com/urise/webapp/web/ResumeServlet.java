package com.urise.webapp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write("<html>\n" +
                "<head>\n" +
                "  <style>\n" +
                "    table {\n" +
                "      font-family: arial, sans-serif;\n" +
                "      border-collapse: collapse;\n" +
                "      width: 100%;\n" +
                "    }\n" +
                "\n" +
                "    td, th {\n" +
                "      border: 1px solid #dddddd;\n" +
                "      text-align: left;\n" +
                "      padding: 8px;\n" +
                "    }\n" +
                "\n" +
                "    tr:nth-child(even) {\n" +
                "      background-color: #dddddd;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<h2>RESUME</h2>\n" +
                "\n" +
                "<table>\n" +
                "  <tr>\n" +
                "    <th>uuid</th>\n" +
                "    <th>full_name</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>uuid_1</td>\n" +
                "    <td>Kyrylo</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>uuid_2</td>\n" +
                "    <td>Alan</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>uuid_3</td>\n" +
                "    <td>Artem</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
