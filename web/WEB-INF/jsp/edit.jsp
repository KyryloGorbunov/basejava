<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/${theme}.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/edit-resume-styles.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<form method="post" action="resume" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="uuid" value="${resume.uuid}">
    <input type="hidden" name="theme" value="${theme}">
    <div class="scrollable-panel">
        <div class="form-wrapper">
            <div class="section">Full name</div>
            <input class="field" type="text" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$?" name="fullName" size=55 placeholder="Full name" value="${resume.fullName}" required>

            <div class="section">Contacts</div>

            <c:forEach var="type" items="<%=ContactType.values()%>">
                <input class="field" type="text" name="${type.name()}" size=30 placeholder="${type.title}"
                       value="${resume.getContact(type)}">
            </c:forEach>

            <div class="spacer"></div>

            <div class="section">Sections</div>

            <c:forEach var="type" items="<%=SectionType.values()%>">
                <c:set var="section" value="${resume.getSection(type)}"/>
                <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
                <div class="field-label">${type.title}</div>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                        <textarea class="field" name='${type}'><%=section%></textarea>
                    </c:when>
                    <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                        <textarea class="field" name='${type}'><%=String.join("\n", ((ListSection) section).getStrings())%></textarea>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>" varStatus="counter">
                            <c:choose>
                                <c:when test="${counter.index == 0}">
                                </c:when>
                                <c:otherwise>
                                    <div class="spacer"></div>
                                </c:otherwise>
                            </c:choose>

<%--                                                        <button class="green-button">Add</button>--%>

                            <input class="field" type="text" placeholder="Name" name='${type}' size=100 value="${org.homePage.name}">
                            <input class="field" type="text" placeholder="Link" name='${type}url' size=100 value="${org.homePage.url}">

<%--                                                        <button class="small-green-button">Add position</button>--%>

                            <c:forEach var="pos" items="${org.periods}">
                                <jsp:useBean id="pos" type="com.urise.webapp.model.Organization.Period"/>

                                <div class="date-section">
                                    <input class="field date" name="${type}${counter.index}startDate"
                                           placeholder="Start date, MM/YYYY"
                                           size=10
                                           value="<%=DateUtil.format(pos.getStartDate())%>">
                                    <input class="field date date-margin" name="${type}${counter.index}endDate"
                                           placeholder="End date, MM/YYYY"
                                           size=10
                                           value="<%=DateUtil.format(pos.getEndDate())%>">
                                </div>

                                <input class="field" type="text" placeholder="Header"
                                       name='${type}${counter.index}title' size=75
                                       value="${pos.position}">
                                <textarea class="field" placeholder="Description" name="${type}${counter.index}description">${pos.description}</textarea>

                            </c:forEach>

                            <button class="green-button">Add</button>

                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:forEach>

            <div class="spacer"></div>

            <div class="button-section">
                <button class="red-cancel-button" onclick="window.history.back()">Cancel</button>
                <button class="green-submit-button" type="submit">Save</button>
            </div>

        </div>
    </div>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
