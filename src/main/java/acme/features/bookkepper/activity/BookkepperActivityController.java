
package acme.features.bookkepper.activity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.activities.Activity;
import acme.entities.roles.Bookkepper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkepper/activity/")
public class BookkepperActivityController extends AbstractController<Bookkepper, Activity> {

	@Autowired
	private BookkepperActivityShowService	showService;

	@Autowired
	private BookkepperActivityListService	listService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}
}
