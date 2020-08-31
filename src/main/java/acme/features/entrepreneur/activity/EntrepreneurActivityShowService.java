
package acme.features.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurActivityShowService implements AbstractShowService<Entrepreneur, Activity> {

	@Autowired
	private EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		int principalId = request.getPrincipal().getActiveRoleId();
		int activityId = request.getModel().getInteger("id");
		Activity activity = this.repository.findOneById(activityId);
		Entrepreneur entrepreneur = this.repository.findOneEntrepreneurById(principalId);

		boolean result = activity.getInvestmentRound().getEntrepreneur().equals(entrepreneur);
		return result;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "start", "end", "budget");

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Activity result = this.repository.findOneById(id);

		return result;
	}

}
