
package acme.features.investor.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	private InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean res = false;
		int investmentRoundId = Integer.parseInt(request.getServletRequest().getParameter("id"));
		Application a = this.repository.findApplicationByInvestorId(request.getPrincipal().getActiveRoleId(), investmentRoundId);
		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(investmentRoundId);

		if (investmentRound.getStatus().equals("PUBLISHED") && a == null) {
			res = true;
		}
		return res;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "statement", "offer", "status");

		model.setAttribute("id", request.getServletRequest().getParameter("id"));
		model.setAttribute("ticker", entity.getInvestmentRound().getTicker());

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result = new Application();
		int investorId = request.getPrincipal().getActiveRoleId();
		Investor investor = this.repository.findOneInvestorById(investorId);

		result.setInvestor(investor);

		InvestmentRound i = this.repository.findInvestmentRoundById(Integer.parseInt(request.getServletRequest().getParameter("id")));
		result.setInvestmentRound(i);
		result.setStatus("PENDING");

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		Date updatedStatusDate = new Date(System.currentTimeMillis() - 1);
		result.setCreationDate(creationDate);
		result.setUpdatedStatusDate(updatedStatusDate);
		result.setJustification("");

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isUnique = this.repository.findOneApplicationByTicker(request.getModel().getString("ticker")) == null;
		errors.state(request, isUnique, "ticker", "acme.error.ticker");

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);

		int id = Integer.parseInt(request.getServletRequest().getParameter("id"));
		InvestmentRound i = this.repository.findInvestmentRoundById(id);
		entity.setInvestmentRound(i);

		this.repository.save(entity);
	}

}
