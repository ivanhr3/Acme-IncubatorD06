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

<acme:form>
	<acme:form-textbox code="administrator.inquire.form.label.title" path="title"/>
	<jstl:if test="${ command != 'create' }">
		<acme:form-moment readonly="true" code="administrator.inquire.form.label.creationDate" path="creationDate"/>
	</jstl:if>
	<acme:form-moment code="administrator.inquire.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.inquire.form.label.paragraph" path="paragraph"/>
	<acme:form-money code="administrator.inquire.form.label.minimumMoney" path="minimumMoney"/>
	<acme:form-money code="administrator.inquire.form.label.maximumMoney" path="maximumMoney"/>
	<acme:form-textbox code="administrator.inquire.form.label.email" path="email"/>
	
	<acme:form-submit test="${ command == 'show' }" code="administrator.inquire.form.button.update" action="/administrator/inquire/update"/>
	<acme:form-submit test="${ command == 'show' }" code="administrator.inquire.form.button.delete" action="/administrator/inquire/delete"/>
	<acme:form-submit test="${ command == 'create' }" code="administrator.inquire.form.button.submit" action="/administrator/inquire/create"/>
	<acme:form-submit test="${ command == 'update' }" code="administrator.inquire.form.button.update" action="/administrator/inquire/update"/>
	<acme:form-submit test="${ command == 'delete' }" code="administrator.inquire.form.button.delete" action="/administrator/inquire/delete"/>
	
	<acme:form-return code="administrator.inquire.form.button.return"/>
</acme:form>