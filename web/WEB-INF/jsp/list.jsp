<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/${theme}.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/resume-list-styles.css">
    <title>List of all resumes</title>
</head>
<body>
<div class="themes">
    <div class="theme-title">Theme</div>
    <div class="theme-selector">
        <form action="" method="GET">
            <select name="theme" onchange="this.form.submit()">
                <option value="light" ${theme == null || theme == 'light' ? 'selected' : ''}>Light</option>
                <option value="dark" ${theme == 'dark' ? 'selected' : ''}>Dark</option>
                <option value="purple" ${theme == 'purple' ? 'selected' : ''}>Purple</option>
            </select>
        </form>
    </div>
</div>
<jsp:include page="fragments/header.jsp"/>
<div class="scrollable-panel">
    <div class="table-wrapper">
        <div class="add-resume">
            <a class="no-underline-anchor" href="resume?action=add&theme=${theme}">
                <img src="img/${theme}/add-person.svg" alt="">
            </a>
            <a class="text-anchor" href="resume?action=add&theme=${theme}">
                <p class="add-resume-title">Add resume</p>
            </a>
        </div>
        <div class="resumes-list">
            <table>
                <tr class="t-header">
                    <th class="name-column">Name</th>
                    <th class="info-column">Contacts</th>
                    <th class="img-column">Edit</th>
                    <th class="img-column">Delete</th>
                </tr>
                <c:forEach items="${resumes}" var="resume">
                    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
                    <tr class="t-body">
                        <td class="name-column">
                            <a class="contact-link"
                               href="resume?uuid=${resume.uuid}&action=view&theme=${theme}">${resume.fullName}</a>
                        </td>
                        <td class="info-column">
                            <%=ContactType.EMAIL.toLink(resume.getContact(ContactType.EMAIL))%>
                        </td>
                        <td class="img-column">
                            <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit&theme=${theme}">
                                <img src="img/${theme}/edit.svg" alt="">
                            </a>
                        </td>
                        <td class="img-column">
                            <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=delete&theme=${theme}">
                                <img src="img/${theme}/remove.svg" alt="">
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>