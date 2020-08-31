
package acme.features.anonymous.hernandezBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.HernandezBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/hernandez-bulletin/")
public class AnonymousHernandezBulletinController extends AbstractController<Anonymous, HernandezBulletin> {

	@Autowired
	private AnonymousHernandezBulletinListService	listService;

	@Autowired
	private AnonymousHernandezBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
