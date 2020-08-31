
package acme.features.bookkepper.accountingRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkepper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookkepperAccountingRecordListService implements AbstractListService<Bookkepper, AccountingRecord> {

	@Autowired
	private BookkepperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "status");

	}

	@Override
	public Collection<AccountingRecord> findMany(final Request<AccountingRecord> request) {
		assert request != null;

		int id = Integer.parseInt(request.getServletRequest().getParameter("id"));
		Collection<AccountingRecord> result = this.repository.findAllByInvestmentRoundId(id);
		return result;
	}

}
