
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.spamlist.Spamlist;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		int principalId = request.getPrincipal().getActiveRoleId();

		int investmentRoundId = request.getModel().getInteger("id");
		InvestmentRound i = this.repository.findOneById(investmentRoundId);

		Boolean result = i.getEntrepreneur().getId() == principalId;
		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "kindOfRound", "title", "description", "amount", "additionalInfo");

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		InvestmentRound result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isSpamEN, isSpamES;
		String bigString;
		bigString = request.getModel().getString("title") + " " + request.getModel().getString("description") + " " + request.getModel().getString("additionalInfo");
		Collection<String> spamEN = this.repository.findAllSpamwordsEN();
		Collection<String> spamES = this.repository.findAllSpamwordsES();
		isSpamEN = this.isSpam(bigString, spamEN, entity);
		isSpamES = this.isSpam(bigString, spamES, entity);

		if (!errors.hasErrors()) {
			errors.state(request, !isSpamEN, "ticker", "acme.validation.spam");
			errors.state(request, !isSpamES, "ticker", "acme.validation.spam");
		}

		errors.state(request, this.repository.findAllApplicationsByInvestmentRoundId(entity.getId()).isEmpty(), "ticker", "acme.validation.applied");

	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		if (entity.getForum() != null) {
			this.repository.deleteAll(entity.getForum().getMessage());
			this.repository.delete(entity.getForum());
		}
		this.repository.deleteAll(entity.getApplication());
		this.repository.deleteAll(entity.getWorkProgramme());
		this.repository.deleteAll(entity.getAccountingRecord());
		this.repository.delete(entity);

	}

	//AUXILIARS

	private Boolean isSpam(final String bigString, final Collection<String> spamlist, final InvestmentRound entity) {
		Spamlist sl = this.repository.findOneSpamlist();
		Integer numWords = bigString.split(" ").length;
		Double numSpamWords = 0.;

		for (String s : spamlist) {
			numSpamWords = numSpamWords + this.numSpamwords(bigString.toLowerCase(), s, 0.);
		}
		return numSpamWords / numWords * 100 > sl.getThreshold();
	}

	private Double numSpamwords(final String fullText, final String spamword, final Double u) {
		if (!fullText.contains(spamword)) {
			return u;
		} else {
			Integer a = fullText.indexOf(spamword);
			return this.numSpamwords(fullText.substring(a + 1), spamword, u + 1);
		}
	}

}
