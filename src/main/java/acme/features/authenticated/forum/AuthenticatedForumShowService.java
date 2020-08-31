
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedForumShowService implements AbstractShowService<Authenticated, Forum> {

	@Autowired
	private AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		Boolean result = false;
		Integer idPrincipal = request.getPrincipal().getAccountId();
		Integer idForum = request.getModel().getInteger("id");
		Forum forum = this.repository.findForumById(idForum);

		if (request.getPrincipal().hasRole(Entrepreneur.class)) {
			Entrepreneur entrepreneur = this.repository.findOneEntrepreneurByUserId(idPrincipal);
			result = forum.getInvestmentRound().getEntrepreneur().equals(entrepreneur);
		} else {
			Investor investor = this.repository.findOneInvestorByUserId(idPrincipal);
			result = forum.getInvestor().equals(investor);
		}
		return result;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int id = request.getPrincipal().getActiveRoleId();
		model.setAttribute("id", id);

		request.unbind(entity, model, "message", "investmentRound.ticker", "investmentRound.title");

		Collection<Message> messages = this.repository.findAllMessagesById(entity.getId());
		model.setAttribute("message", messages);

	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Forum result = this.repository.findForumById(id);

		return result;
	}

}
