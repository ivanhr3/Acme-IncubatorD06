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
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="entrepreneur.application.form.label.creationDate" path="creationDate" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-money code="entrepreneur.application.form.label.offer" path="offer" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.oldstatus" path="oldStatus" readonly="true"/>
	
	<jstl:if test="${oldStatus == 'PENDING' }">
		<acme:form-select code="entrepreneur.application.form.label.status" path="status">
			<acme:form-option code="entrepreneur.application.form.label.status.rejected" value="REJECTED"/>
			<acme:form-option code="entrepreneur.application.form.label.status.accepted" value="ACCEPTED"/>
		</acme:form-select>
	</jstl:if>
	
	<acme:form-textbox code="entrepreneur.application.form.label.justification" path="justification"/>
	
	<acme:form-submit test="${command == 'show' && oldStatus == 'PENDING' }" code="entrepreneur.application.form.button.update" action="/entrepreneur/application/update"/>
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.application.form.button.update" action="/entrepreneur/application/update"/>
	<acme:form-return code="entrepreneur.application.form.button.return"/>
</acme:form>