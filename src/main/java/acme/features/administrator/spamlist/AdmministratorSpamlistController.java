
package acme.features.administrator.spamlist;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spamlist.Spamlist;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spamlist/")
public class AdmministratorSpamlistController extends AbstractController<Administrator, Spamlist> {

	@Autowired
	private AdministratorSpamlistListService	listService;

	@Autowired
	private AdministratorSpamlistShowService	showService;

	@Autowired
	private AdministratorSpamlistUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
