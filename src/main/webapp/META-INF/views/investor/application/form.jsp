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
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" placeholder="SSS-00-000000"/>
	<jstl:if test="${command != 'create' }">
		<acme:form-moment code="investor.application.form.label.creationDate" path="creationDate"/>
		<acme:form-select code="investor.application.form.label.status" path="status">
			<acme:form-option code="investor.application.form.label.status.accepted" value="ACCEPTED"/>
			<acme:form-option code="investor.application.form.label.status.rejected" value="REJECTED"/>
		</acme:form-select>
		<jstl:if test="${status != 'PENDING' }">
		<acme:form-textbox code="investor.application.form.label.justification" path="justification"/>
	</jstl:if>
	</jstl:if>
	<acme:form-textbox code="investor.application.form.label.statement" path="statement"/>
	<acme:form-money code="investor.application.form.label.offer" path="offer"/>
	
	<acme:form-submit test="${command == 'create' }" code="investor.application.form.button.create" action="/investor/application/create"/>
	<acme:form-return code="investor.application.form.button.return"/>
</acme:form>