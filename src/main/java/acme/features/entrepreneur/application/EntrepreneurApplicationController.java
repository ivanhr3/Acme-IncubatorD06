
package acme.features.entrepreneur.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/application/")
public class EntrepreneurApplicationController extends AbstractController<Entrepreneur, Application> {

	@Autowired
	private EntrepreneurApplicationShowService					showService;

	@Autowired
	private EntrepreneurApplicationListService					listService;

	@Autowired
	private EntrepreneurApplicationUpdateService				updateService;

	@Autowired
	private EntrepreneurApplicationListByTickerService			listByTickerService;

	@Autowired
	private EntrepreneurApplicationListByCreationDateService	listByCreationDateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addCustomCommand(CustomCommand.LIST_TICKER, BasicCommand.LIST, this.listByTickerService);
		super.addCustomCommand(CustomCommand.LIST_DATE, BasicCommand.LIST, this.listByCreationDateService);
	}
}
