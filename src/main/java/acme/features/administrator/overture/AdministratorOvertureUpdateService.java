
package acme.features.administrator.overture;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorOvertureUpdateService implements AbstractUpdateService<Administrator, Overture> {

	@Autowired
	private AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");

	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "deadline", "paragraph", "minimumMoney", "maximumMoney", "email");

	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Overture result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		//Deadline validation

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			minimumDeadline = calendar.getTime();
			if (entity.getDeadline() == null) {
				errors.state(request, true, "deadline", "javax.validation.constraints.NotBlank.message");
			} else if (entity.getDeadline() != null) {
				errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "acme.validation.deadline");
			}
		}

		//Money validation
		boolean money1 = entity.getMaximumMoney().getAmount() >= 0;
		boolean money2 = entity.getMinimumMoney().getAmount() >= 0;
		boolean currency2 = entity.getMaximumMoney().getCurrency().equals("€") || entity.getMaximumMoney().getCurrency().equals("EUR");
		boolean currency1 = entity.getMinimumMoney().getCurrency().equals("€") || entity.getMinimumMoney().getCurrency().equals("EUR");
		if (!errors.hasErrors("maximumMoney")) {
			errors.state(request, money1, "maximumMoney", "acme.validation.money");
		}
		if (!errors.hasErrors("minimumMoney")) {
			errors.state(request, money2, "averageReward", "acme.validation.money");
		}

		if (!errors.hasErrors("maximumMoney")) {
			errors.state(request, currency1, "maximumMoney", "acme.validation.money");
		}
		if (!errors.hasErrors("minimumMoney")) {
			errors.state(request, currency2, "minimumMoney", "acme.validation.money");
		}

	}

	@Override
	public void update(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);

		this.repository.save(entity);

	}

}
