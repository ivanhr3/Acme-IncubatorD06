
package acme.features.administrator.inquire;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquires.Inquire;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquireCreateService implements AbstractCreateService<Administrator, Inquire> {

	@Autowired
	private AdministratorInquireRepository repository;


	@Override
	public boolean authorise(final Request<Inquire> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Inquire> request, final Inquire entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");

	}

	@Override
	public void unbind(final Request<Inquire> request, final Inquire entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "deadline", "paragraph", "minimumMoney", "maximumMoney", "email");
	}

	@Override
	public Inquire instantiate(final Request<Inquire> request) {
		assert request != null;

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		Inquire result = new Inquire();
		result.setCreationDate(creationDate);
		return result;
	}

	@Override
	public void validate(final Request<Inquire> request, final Inquire entity, final Errors errors) {
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
	public void create(final Request<Inquire> request, final Inquire entity) {
		assert request != null;
		assert entity != null;

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);

		this.repository.save(entity);

	}

}
