
package acme.features.authenticated.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestmentRoundShowService implements AbstractShowService<Authenticated, InvestmentRound> {

	@Autowired
	private AuthenticatedInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "kindOfRound", "title", "description", "amount", "additionalInfo", "workProgramme", "status");

		Boolean isInvestor;
		Boolean alreadyApplied = false;
		isInvestor = request.getPrincipal().hasRole(Investor.class);
		if (isInvestor) {
			Investor i = this.repository.findInvestorByUserId(request.getPrincipal().getAccountId());
			Application a = this.repository.findApplicationByInvestorId(i.getId(), entity.getId());
			if (a != null) {
				alreadyApplied = true;
			}
		}
		model.setAttribute("isInvestor", isInvestor);
		model.setAttribute("alreadyApplied", alreadyApplied);
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		InvestmentRound result = this.repository.findOneById(id);
		return result;
	}

}
