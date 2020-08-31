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

<acme:form readonly="false">
	<acme:form-textbox code="authenticated.entrepreneur.form.label.startupName" path="startupName"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.sector" path="sector"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.qualificationRecord" path="qualificationRecord"/>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.skillsRecord" path="skillsRecord"/>
	
	<acme:form-submit code="authenticated.entrepreneur.form.button.submit" test="${command == 'create' }" action="/authenticated/entrepreneur/create"/>
	<acme:form-submit code="authenticated.entrepreneur.form.button.update" test="${command == 'update' }" action="/authenticated/entrepreneur/update"/>
	<acme:form-return code="authenticated.entrepreneur.form.button.return"/>
</acme:form>