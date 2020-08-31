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

<h5>Forum associated to investment round#${id }</h5>
<acme:form readonly="true">
	<acme:form-textbox code="authenticated.forum.form.label.ticker" path="investmentRound.ticker"/>
	<acme:form-textbox code="authenticated.forum.form.label.title" path="investmentRound.title"/>
	
	<acme:form-submit method="get" code="authenticated.forum.form.button.getmessages" action="/authenticated/message/list?id=${id}"/>
	<acme:form-return code="authenticated.forum.form.button.return"/>
</acme:form>