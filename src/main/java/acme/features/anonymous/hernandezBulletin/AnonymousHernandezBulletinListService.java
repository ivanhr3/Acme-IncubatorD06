
package acme.features.anonymous.hernandezBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.HernandezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousHernandezBulletinListService implements AbstractListService<Anonymous, HernandezBulletin> {

	@Autowired
	AnonymousHernandezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<HernandezBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<HernandezBulletin> request, final HernandezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "surname", "country", "favouriteSinger");

	}

	@Override
	public Collection<HernandezBulletin> findMany(final Request<HernandezBulletin> request) {
		assert request != null;

		Collection<HernandezBulletin> result = this.repository.findMany();
		return result;
	}

}
