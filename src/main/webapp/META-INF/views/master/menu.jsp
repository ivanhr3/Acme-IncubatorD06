<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Investor,acme.entities.roles.Entrepreneur"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.unity.com/es/learn"/>
			<acme:menu-suboption code="master.menu.anonymous.listbulletins" action="/anonymous/hernandez-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.createbulletins" action="/anonymous/hernandez-bulletin/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.listnotices" action="/anonymous/notice/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.listtechnologyrecords" action="/anonymous/technology-record/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.listtoolrecords" action="/anonymous/tool-record/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.listnotices" action="/administrator/notice/list"/>
			<acme:menu-suboption code="master.menu.administrator.notices.create" action="/administrator/notice/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.inquires.list" action="/administrator/inquire/list"/>
			<acme:menu-suboption code="master.menu.administrator.inquires.create" action="/administrator/inquire/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.overtures.list" action="/administrator/overture/list"/>
			<acme:menu-suboption code="master.menu.administrator.overtures.create" action="/administrator/overture/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.technologyrecords.list" action="/administrator/technology-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.technologyrecords.create" action="/administrator/technology-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.toolrecords.list" action="/administrator/tool-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.toolrecords.create" action="/administrator/tool-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.challenges.list" action="/administrator/challenge/list"/>
			<acme:menu-suboption code="master.menu.administrator.challenges.create" action="/administrator/challenge/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator.customization" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.listsectors" action="/administrator/sector/list"/>
			<acme:menu-suboption code="master.menu.administrator.listspamlist" action="/administrator/spamlist/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.user-accounts" action="/authenticated/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listnotices" action="/authenticated/notice/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listtechnologyrecords" action="/authenticated/technology-record/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listtoolrecords" action="/authenticated/tool-record/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listinquires" action="/authenticated/inquire/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listovertures" action="/authenticated/overture/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listchallenges" action="/authenticated/challenge/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listinvestmentrounds" action="/authenticated/investment-round/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listforums" action="/authenticated/forum/list"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.bookkepper" access="hasRole('Bookkepper')">
			<acme:menu-suboption code="master.menu.bookkepper.favourite-link" action="http://www.unity.com/es/learn"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.bookkepper.investmentround.listrec" action="/bookkepper/investment-round/list_rec"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.bookkepper.investmentround.listnonrec" action="/bookkepper/investment-round/list_nonrec"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.favourite-link" action="http://www.unity.com/es/learn"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.entrepreneur.listmyinvestmentrounds" action="/entrepreneur/investment-round/list"/>
			<acme:menu-suboption code="master.menu.entrepreneur.investmentround.create" action="/entrepreneur/investment-round/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.entrepreneur.listmyapplications" action="/entrepreneur/application/list"/>
			<acme:menu-suboption code="master.menu.entrepreneur.listmyapplicationsbyticker" action="/entrepreneur/application/list_ticker"/>
			<acme:menu-suboption code="master.menu.entrepreneur.listmyapplicationsbydate" action="/entrepreneur/application/list_date"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listforums" action="/authenticated/forum/list"/>

		</acme:menu-option>
		
		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.favourite-link" action="http://www.unity.com/es/learn"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.investor.listmyapplications" action="/investor/application/list"/>
			<acme:menu-suboption code="master.menu.investor.createapplications" action="/investor/application/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.listforums" action="/authenticated/forum/list"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create" access="!hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update" access="hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create" access="!hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update" access="hasRole('Investor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

