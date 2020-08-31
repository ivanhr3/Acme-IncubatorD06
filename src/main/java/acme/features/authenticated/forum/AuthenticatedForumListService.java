
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedForumListService implements AbstractListService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investmentRound.ticker", "investmentRound.title");
	}

	@Override
	public Collection<Forum> findMany(final Request<Forum> request) {
		assert request != null;

		int id = request.getPrincipal().getAccountId();
		Collection<Forum> result = null;

		// Si es Entrepreneur usa su query, en caso contrario usa el query de Investor.
		//Para cualquier otro rol la lista estará vacía (por ej. si un bookkepper busca sus forums).
		if (request.getPrincipal().hasRole(Entrepreneur.class)) {
			result = this.repository.findAllByEntrepreneurId(id);
		} else {
			result = this.repository.findAllByInvestorId(id);
		}

		return result;
	}

}
