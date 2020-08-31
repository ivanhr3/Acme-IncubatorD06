
package acme.features.bookkepper.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkepper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookkepperNonRecordedInvestmentRoundListService implements AbstractListService<Bookkepper, InvestmentRound> {

	@Autowired
	private BookkepperInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		int principalId = request.getPrincipal().getActiveRoleId();
		Bookkepper b = this.repository.findOneBookkepperById(principalId);
		return b != null;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert entity != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationDate", "title", "amount");

	}

	@Override
	public Collection<InvestmentRound> findMany(final Request<InvestmentRound> request) {
		assert request != null;

		int id = request.getPrincipal().getActiveRoleId();
		Collection<InvestmentRound> result = this.repository.findManyByBookkepperNonRecorded(id);
		return result;
	}

}
