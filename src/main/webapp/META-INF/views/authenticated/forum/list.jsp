<%--
- list.jsp
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

<h5>Forums where I'm involved associated to the next investment rounds</h5>
<acme:list>
	<acme:list-column code="authenticated.forum.list.label.ticker" path="investmentRound.ticker" width="30%"/>
	<acme:list-column code="authenticated.forum.list.label.title" path="investmentRound.title" width="30%"/>			
</acme:list>