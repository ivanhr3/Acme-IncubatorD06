
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.spamlist.Spamlist;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundShowService implements AbstractShowService<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		int investmentRoundId = request.getModel().getInteger("id");
		int entrepreneurId = request.getPrincipal().getActiveRoleId();

		InvestmentRound investmentRound = this.repository.findOneById(investmentRoundId);
		Entrepreneur entrepreneur = this.repository.findOneEntrepreneurById(entrepreneurId);
		boolean result = investmentRound.getEntrepreneur().equals(entrepreneur);

		return result;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "kindOfRound", "title", "description", "amount", "additionalInfo", "workProgramme");

		Boolean isSpamEN, isSpamES;
		String bigString = entity.getTitle() + " " + entity.getDescription() + " " + entity.getAdditionalInfo();
		Collection<String> spamEN = this.repository.findAllSpamwordsEN();
		Collection<String> spamES = this.repository.findAllSpamwordsES();
		isSpamEN = this.isSpam(bigString, spamEN, entity);
		isSpamES = this.isSpam(bigString, spamES, entity);

		model.setAttribute("isFinalMode", entity.isBudgetCorrect() && !isSpamEN && !isSpamES);
		model.setAttribute("oldKindOfRound", entity.getKindOfRound());

		boolean isApplied;
		if (entity.getApplication().isEmpty()) {
			isApplied = false;
		} else {
			isApplied = true;
		}
		model.setAttribute("deleteable", !isApplied);
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		InvestmentRound result = this.repository.findOneById(id);
		return result;
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
