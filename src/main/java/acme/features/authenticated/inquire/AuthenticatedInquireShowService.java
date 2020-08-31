
package acme.features.authenticated.inquire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquires.Inquire;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInquireShowService implements AbstractShowService<Authenticated, Inquire> {

	@Autowired
	private AuthenticatedInquireRepository repository;


	@Override
	public boolean authorise(final Request<Inquire> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Inquire> request, final Inquire entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "deadline", "paragraph", "minimumMoney", "maximumMoney", "email");
	}

	@Override
	public Inquire findOne(final Request<Inquire> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Inquire result = this.repository.findOneById(id);

		return result;
	}

}
