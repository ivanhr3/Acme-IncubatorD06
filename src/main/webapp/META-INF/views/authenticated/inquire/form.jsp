<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.inquire.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.inquire.form.label.creationDate" path="creationDate"/>
	<acme:form-moment code="authenticated.inquire.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.inquire.form.label.paragraph" path="paragraph"/>
	<acme:form-money code="authenticated.inquire.form.label.minimumMoney" path="minimumMoney"/>
	<acme:form-money code="authenticated.inquire.form.label.maximumMoney" path="maximumMoney"/>
	<acme:form-textbox code="authenticated.inquire.form.label.email" path="email"/>
	
	<acme:form-return code="authenticated.inquire.form.button.return"/>
</acme:form>