
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurApplicationListService implements AbstractListService<Entrepreneur, Application> {

	@Autowired
	private EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationDate", "offer", "status");

	}

	@Override
	public Collection<Application> findMany(final Request<Application> request) {
		assert request != null;

		int id = request.getPrincipal().getActiveRoleId();
		Collection<Application> result = this.repository.findAllByEntrepreneurId(id);
		return result;
	}

}
