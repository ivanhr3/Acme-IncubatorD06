
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Boolean result = false;
		Integer idPrincipal = request.getPrincipal().getAccountId();
		Integer idMessage = request.getModel().getInteger("id");
		Message message = this.repository.findMessageById(idMessage);

		if (request.getPrincipal().hasRole(Entrepreneur.class)) {
			Entrepreneur entrepreneur = this.repository.findOneEntrepreneurByUserId(idPrincipal);
			result = message.getForum().getInvestmentRound().getEntrepreneur().equals(entrepreneur);
		} else {
			Investor investor = this.repository.findOneInvestorByUserId(idPrincipal);
			result = message.getForum().getInvestor().equals(investor);
		}
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "tags", "body");

	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Message result = this.repository.findMessageById(id);

		return result;
	}

}
