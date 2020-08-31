
package acme.features.entrepreneur.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {

	@Autowired
	private EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		int appId = request.getModel().getInteger("id");
		Application application = this.repository.findApplicationById(appId);
		Principal principal = request.getPrincipal();
		Entrepreneur entrepreneur = application.getInvestmentRound().getEntrepreneur();
		boolean result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
		return result;
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

		request.unbind(entity, model, "status", "justification");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Application result = this.repository.findApplicationById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isRejected = request.getModel().getAttribute("status").equals("REJECTED") && request.getModel().getAttribute("justification").toString().isEmpty();

		if (!errors.hasErrors("status")) {
			errors.state(request, !isRejected, "justification", "acme.validation.justification");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date updatedStatusDate = new Date(System.currentTimeMillis() - 1);
		Boolean change = request.getModel().getString("oldStatus") != request.getModel().getString("status");

		if (change) {
			entity.setUpdatedStatusDate(updatedStatusDate);
		}
		this.repository.save(entity);

	}

}
