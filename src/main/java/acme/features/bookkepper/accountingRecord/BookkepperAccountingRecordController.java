
package acme.features.bookkepper.accountingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkepper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkepper/accounting-record/")
public class BookkepperAccountingRecordController extends AbstractController<Bookkepper, AccountingRecord> {

	@Autowired
	private BookkepperAccountingRecordShowService	showService;

	@Autowired
	private BookkepperAccountingRecordListService	listService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}
}
