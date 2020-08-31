
package acme.features.administrator.technologyRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorTechnologyRecordListService implements AbstractListService<Administrator, TechnologyRecord> {

	@Autowired
	private AdministratorTechnologyRecordRepository repository;


	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "sector.name", "stars");
	}

	@Override
	public Collection<TechnologyRecord> findMany(final Request<TechnologyRecord> request) {
		Collection<TechnologyRecord> result = this.repository.findMany();

		return result;
	}

}
