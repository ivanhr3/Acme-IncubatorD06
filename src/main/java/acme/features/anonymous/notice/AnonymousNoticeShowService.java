
package acme.features.anonymous.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousNoticeShowService implements AbstractShowService<Anonymous, Notice> {

	@Autowired
	private AnonymousNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "headerPicture", "title", "creationDate", "deadline", "body", "relatedNotices");
	}

	@Override
	public Notice findOne(final Request<Notice> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Notice result = this.repository.findOneById(id);

		return result;
	}

}
