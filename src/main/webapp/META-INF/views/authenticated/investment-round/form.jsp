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
	<acme:form-textbox code="authenticated.investmentround.form.label.ticker" path="ticker"/>
	<acme:form-moment code="authenticated.investmentround.form.label.creationDate" path="creationDate"/>
	<acme:form-textbox code="authenticated.investmentround.form.label.kindOfRound" path="kindOfRound"/>
	<acme:form-textbox code="authenticated.investmentround.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.investmentround.form.label.description" path="description"/>
	<acme:form-money code="authenticated.investmentround.form.label.amount" path="amount"/>
	<acme:form-url code="authenticated.investmentround.form.label.additionalInfo" path="additionalInfo"/>
	
	
	<acme:form-submit method="get" code="authenticated.investmentround.form.button.workProgramme" action="/authenticated/activity/list?id=${id}"/>
	<acme:form-submit method="get" code="authenticated.investmentround.form.button.accountingRecord" action="/authenticated/accounting-record/list?id=${id}"/>
	<acme:form-submit method="get" test="${status == 'PUBLISHED' && isInvestor && !alreadyApplied }" code="authenticated.investentround.form.button.application" action="/investor/application/create?id=${id}&ticker=${ticker}"/>
	<acme:form-return code="authenticated.investmentround.form.button.return"/>
</acme:form>