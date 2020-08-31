
package acme.features.administrator.inquire;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.inquires.Inquire;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/inquire/")
public class AdministratorInquireController extends AbstractController<Administrator, Inquire> {

	@Autowired
	private AdministratorInquireListService		listService;

	@Autowired
	private AdministratorInquireShowService		showService;

	@Autowired
	private AdministratorInquireCreateService	createService;

	@Autowired
	private AdministratorInquireUpdateService	updateService;

	@Autowired
	private AdministratorInquireDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
