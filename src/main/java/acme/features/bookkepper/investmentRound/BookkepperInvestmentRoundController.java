
package acme.features.bookkepper.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkepper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkepper/investment-round/")
public class BookkepperInvestmentRoundController extends AbstractController<Bookkepper, InvestmentRound> {

	@Autowired
	private BookkepperInvestmentRoundShowService			showService;

	@Autowired
	private BookkepperRecordedInvestmentRoundListService	recordedListService;

	@Autowired
	private BookkepperNonRecordedInvestmentRoundListService	nonRecordedListService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_REC, BasicCommand.LIST, this.recordedListService);
		super.addCustomCommand(CustomCommand.LIST_NONREC, BasicCommand.LIST, this.nonRecordedListService);
	}
}
