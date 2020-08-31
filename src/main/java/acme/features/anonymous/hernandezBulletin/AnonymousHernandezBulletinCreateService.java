
package acme.features.anonymous.hernandezBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.HernandezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousHernandezBulletinCreateService implements AbstractCreateService<Anonymous, HernandezBulletin> {

	@Autowired
	AnonymousHernandezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<HernandezBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<HernandezBulletin> request, final HernandezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<HernandezBulletin> request, final HernandezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "surname", "country", "favouriteSinger");

	}

	@Override
	public HernandezBulletin instantiate(final Request<HernandezBulletin> request) {
		assert request != null;
		HernandezBulletin result = new HernandezBulletin();

		return result;
	}

	@Override
	public void validate(final Request<HernandezBulletin> request, final HernandezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<HernandezBulletin> request, final HernandezBulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
