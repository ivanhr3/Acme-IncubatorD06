
package acme.features.administrator.sector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.sectors.Sector;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSectorUpdateService implements AbstractUpdateService<Administrator, Sector> {

	@Autowired
	private AdministratorSectorRepository repository;


	@Override
	public boolean authorise(final Request<Sector> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Sector> request, final Sector entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Sector> request, final Sector entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name");

	}

	@Override
	public Sector findOne(final Request<Sector> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		Sector result = this.repository.findSectorById(id);
		return result;
	}

	@Override
	public void validate(final Request<Sector> request, final Sector entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Sector> request, final Sector entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
