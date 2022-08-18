package com.urise.webapp.web;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer writer = response.getWriter();
        writer.write("<html>\n" +
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
                "  </tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write("  <tr>\n" +
                    "    <td>" + resume.getUuid() + "</td>\n" +
                    "    <td>" + resume.getFullName() + "</td>\n" +
                    "  </tr>\n");
        }

        writer.write("</table>\n" +
                "\n" +
                "</body>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
