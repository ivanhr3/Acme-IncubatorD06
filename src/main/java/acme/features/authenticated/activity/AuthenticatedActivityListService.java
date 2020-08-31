
package acme.features.authenticated.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedActivityListService implements AbstractListService<Authenticated, Activity> {

	@Autowired
	private AuthenticatedActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "start", "end");

	}

	@Override
	public Collection<Activity> findMany(final Request<Activity> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Collection<Activity> result = this.repository.findAllByInvestmentRoundId(id);

		return result;
	}

}
