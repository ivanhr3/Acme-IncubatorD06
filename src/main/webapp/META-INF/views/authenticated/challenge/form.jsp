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
	<acme:form-textbox code="administrator.challenge.form.label.title" path="title"/>
	<acme:form-moment code="administrator.challenge.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.challenge.form.label.description" path="description"/>
	<acme:form-textbox code="administrator.challenge.form.label.rookieGoal" path="rookieGoal"/>
	<acme:form-textbox code="administrator.challenge.form.label.averageGoal" path="averageGoal"/>
	<acme:form-textbox code="administrator.challenge.form.label.expertGoal" path="expertGoal"/>
	<acme:form-money code="administrator.challenge.form.label.rookieReward" path="rookieReward"/>
	<acme:form-money code="administrator.challenge.form.label.averageReward" path="averageReward"/>
	<acme:form-money code="administrator.challenge.form.label.expertReward" path="expertReward"/>
	
	<acme:form-return code="authenticated.challenge.form.button.return"/>
</acme:form>