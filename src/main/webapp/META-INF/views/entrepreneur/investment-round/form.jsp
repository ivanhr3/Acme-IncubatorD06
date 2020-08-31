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
	<jstl:if test="${isFinalMode }">
	<acme:form-textbox code="entrepreneur.investmentround.form.label.ticker" path="ticker" placeholder="SSS-00-000000" readonly="true"/>
	<acme:form-moment code="entrepreneur.investmentround.form.label.creationDate" path="creationDate" readonly="true"/>
	<acme:form-textbox code="entrepreneur.investmentround.form.label.oldKindOfRound" path="oldKindOfRound" readonly="true"/>
	<acme:form-textbox code="entrepreneur.investmentround.form.label.title" path="title" readonly="true"/>
	<acme:form-textarea code="entrepreneur.investmentround.form.label.description" path="description" readonly="true"/>
	<acme:form-money code="entrepreneur.investmentround.form.label.amount" path="amount" readonly="true"/>
	<acme:form-url code="entrepreneur.investmentround.form.label.additionalInfo" path="additionalInfo" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${not isFinalMode }">
	<acme:form-textbox code="entrepreneur.investmentround.form.label.ticker" path="ticker" placeholder="SSS-00-000000"/>
	<acme:check-access test="${command != 'create' }">
	<acme:form-textbox code="entrepreneur.investmentround.form.label.oldKindOfRound" path="oldKindOfRound" readonly="true"/>
	</acme:check-access>
	<acme:form-select code="entrepreneur.investmentround.form.label.kindOfRound" path="kindOfRound">
		<acme:form-option code="entrepreneur.investmentround.form.label.kindOfRound.seed" value="SEED"/>
		<acme:form-option code="entrepreneur.investmentround.form.label.kindOfRound.angel" value="ANGEL"/>
		<acme:form-option code="entrepreneur.investmentround.form.label.kindOfRound.seriesa" value="SERIES-A"/>
		<acme:form-option code="entrepreneur.investmentround.form.label.kindOfRound.seriesb" value="SERIES-B"/>
		<acme:form-option code="entrepreneur.investmentround.form.label.kindOfRound.seriesc" value="SERIES-C"/>	
		<acme:form-option code="entrepreneur.investmentround.form.label.kindOfRound.bridge" value="BRIDGE"/>
	</acme:form-select>
	<acme:form-textbox code="entrepreneur.investmentround.form.label.title" path="title"/>
	<acme:form-textarea code="entrepreneur.investmentround.form.label.description" path="description"/>
	<acme:form-money code="entrepreneur.investmentround.form.label.amount" path="amount"/>
	<acme:form-url code="entrepreneur.investmentround.form.label.additionalInfo" path="additionalInfo"/>
	</jstl:if>
	
	
	<acme:check-access test="${!isFinalMode }">
		<acme:form-submit test="${command == 'show' }" code="entrepreneur.investmentround.form.button.update" action="/entrepreneur/investment-round/update"/>
		<acme:form-submit test="${command == 'update' }" code="entrepreneur.investmentround.form.button.update" action="/entrepreneur/investment-round/update"/>
	</acme:check-access>
	
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investmentround.form.button.create" action ="/entrepreneur/investment-round/create"/>
	
	<!-- Delete -->
	<acme:form-submit test="${(command == 'show' || command == 'update') && deleteable}" code="entrepreneur.investmentround.form.button.delete" action ="/entrepreneur/investment-round/delete"/>
	<acme:form-submit test="${command == 'delete' && deleteable}" code="entrepreneur.investmentround.form.button.delete" action ="/entrepreneur/investment-round/delete"/>
	
	<acme:form-submit method="get" code="entrepreneur.investmentround.form.button.workProgramme" action="/entrepreneur/activity/list?id=${id}"/>
	<acme:form-submit method="get" code="entrepreneur.investmentround.form.button.accountingRecord" action="/entrepreneur/accounting-record/list?id=${id}"/>
	<acme:form-return code="entrepreneur.investmentround.form.button.return"/>
</acme:form>