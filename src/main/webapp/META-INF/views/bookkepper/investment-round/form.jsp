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
	<acme:form-textbox code="bookkepper.investmentround.form.label.ticker" path="ticker"/>
	<acme:form-moment code="bookkepper.investmentround.form.label.creationDate" path="creationDate"/>
	<acme:form-textbox code="bookkepper.investmentround.form.label.kindOfRound" path="kindOfRound"/>
	<acme:form-textbox code="bookkepper.investmentround.form.label.title" path="title"/>
	<acme:form-textarea code="bookkepper.investmentround.form.label.description" path="description"/>
	<acme:form-money code="bookkepper.investmentround.form.label.amount" path="amount"/>
	<acme:form-url code="bookkepper.investmentround.form.label.additionalInfo" path="additionalInfo"/>
	
	<acme:form-submit method="get" code="bookkepper.investmentround.form.button.workProgramme" action="/bookkepper/activity/list?id=${id}"/>
	<acme:form-submit method="get" code="bookkepper.investmentround.form.button.accountingRecord" action="/bookkepper/accounting-record/list?id=${id}"/>
	<acme:form-return code="bookkepper.investmentround.form.button.return"/>
</acme:form>