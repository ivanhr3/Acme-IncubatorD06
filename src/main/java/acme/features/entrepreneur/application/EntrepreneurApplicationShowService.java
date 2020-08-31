
package acme.features.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurApplicationShowService implements AbstractShowService<Entrepreneur, Application> {

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
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "statement", "offer", "status", "justification");
		model.setAttribute("oldStatus", entity.getStatus());
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Application result = this.repository.findApplicationById(id);

		return result;
	}

}
