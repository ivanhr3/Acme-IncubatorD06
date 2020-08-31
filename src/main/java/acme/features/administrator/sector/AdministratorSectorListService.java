
package acme.features.administrator.sector;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.sectors.Sector;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSectorListService implements AbstractListService<Administrator, Sector> {

	@Autowired
	AdministratorSectorRepository repository;


	@Override
	public boolean authorise(final Request<Sector> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Sector> request, final Sector entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name");

	}

	@Override
	public Collection<Sector> findMany(final Request<Sector> request) {
		assert request != null;

		Collection<Sector> result = this.repository.findAllSectors();

		return result;
	}
}
