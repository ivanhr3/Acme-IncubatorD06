
package acme.features.administrator.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorChallengeShowService implements AbstractShowService<Administrator, Challenge> {

	@Autowired
	private AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "rookieGoal", "averageGoal", "expertGoal", "rookieReward", "averageReward", "expertReward");
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Challenge result = this.repository.findOneById(id);

		return result;
	}

}
