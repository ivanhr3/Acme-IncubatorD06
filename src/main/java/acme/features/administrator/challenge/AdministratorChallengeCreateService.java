
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "rookieGoal", "averageGoal", "expertGoal", "rookieReward", "averageReward", "expertReward");

	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		assert request != null;

		Challenge result = new Challenge();
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		//Deadline validation

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimumDeadline = calendar.getTime();
			if (entity.getDeadline() == null) {
				errors.state(request, true, "deadline", "javax.validation.constraints.NotBlank.message");
			} else if (entity.getDeadline() != null) {
				errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "acme.validation.deadline");
			}
		}

		//Money validation
		boolean money1 = entity.getAverageReward().getAmount() >= 0;
		boolean money2 = entity.getExpertReward().getAmount() >= 0;
		boolean money3 = entity.getRookieReward().getAmount() >= 0;
		boolean currency2 = entity.getAverageReward().getCurrency().equals("€") || entity.getAverageReward().getCurrency().equals("EUR");
		boolean currency1 = entity.getExpertReward().getCurrency().equals("€") || entity.getExpertReward().getCurrency().equals("EUR");
		boolean currency3 = entity.getRookieReward().getCurrency().equals("€") || entity.getRookieReward().getCurrency().equals("EUR");
		if (!errors.hasErrors("expertReward")) {
			errors.state(request, money1, "expertReward", "acme.validation.money");
		}
		if (!errors.hasErrors("averageReward")) {
			errors.state(request, money2, "averageReward", "acme.validation.money");
		}
		if (!errors.hasErrors("rookieReward")) {
			errors.state(request, money3, "rookieReward", "acme.validation.money");
		}

		if (!errors.hasErrors("expertReward")) {
			errors.state(request, currency1, "expertReward", "acme.validation.money");
		}
		if (!errors.hasErrors("averageReward")) {
			errors.state(request, currency2, "averageReward", "acme.validation.money");
		}
		if (!errors.hasErrors("rookieReward")) {
			errors.state(request, currency3, "rookieReward", "acme.validation.money");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
