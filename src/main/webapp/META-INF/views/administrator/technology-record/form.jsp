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
	<acme:form-textbox code="administrator.technologyrecord.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.technologyrecord.form.label.sector" path="sector.name"/>
	<acme:form-textbox code="administrator.technologyrecord.form.label.inventor" path="inventor"/>
	<acme:form-textarea code="administrator.technologyrecord.form.label.description" path="description"/>
	<acme:form-url code="administrator.technologyrecord.form.label.web" path="web"/>
	<acme:form-textbox code="administrator.technologyrecord.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.technologyrecord.form.label.openSource" path="openSource"/>
	<acme:form-integer code="administrator.technologyrecord.form.label.stars" path="stars"/>
		
	<acme:form-submit test="${ command == 'show' }" code="administrator.technologyrecord.form.button.update" action="/administrator/technology-record/update"/>
	<acme:form-submit test="${ command == 'show' }" code="administrator.technologyrecord.form.button.delete" action="/administrator/technology-record/delete"/>
	<acme:form-submit test="${ command == 'create' }" code="administrator.technologyrecord.form.button.submit" action="/administrator/technology-record/create"/>
	<acme:form-submit test="${ command == 'update' }" code="administrator.technologyrecord.form.button.update" action="/administrator/technology-record/update"/>
	<acme:form-submit test="${ command == 'delete' }" code="administrator.technologyrecord.form.button.delete" action="/administrator/technology-record/delete"/>
	<acme:form-return code="administrator.technologyrecord.form.button.return"/>
</acme:form>