
package acme.features.administrator.notice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	private static final String				URL_REGEX	= "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" + "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" + "([).!';/?:,][[:blank:]])?$";
	private static final Pattern			URL_PATTERN	= Pattern.compile(AdministratorNoticeCreateService.URL_REGEX);

	@Autowired
	private AdministratorNoticeRepository	repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");

	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", false);
		} else {
			request.transfer(model, "confirm");
		}

		request.unbind(entity, model, "headerPicture", "creationDate", "deadline", "body", "relatedNotices");

	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		assert request != null;

		Notice result = new Notice();

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		result.setCreationDate(creationDate);

		return result;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isConfirmed;
		Calendar calendar;
		Date minimumDeadline;
		String relatedNotices = (String) request.getModel().getAttribute("relatedNotices");
		List<String> correctLinks = new ArrayList<>();

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			minimumDeadline = calendar.getTime();
			if (entity.getDeadline() == null) {
				errors.state(request, true, "deadline", "javax.validation.constraints.NotBlank.message");
			} else if (entity.getDeadline() != null) {
				errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "acme.validation.deadline");
			}
		}

		if (relatedNotices != "") {
			for (String n : relatedNotices.split(",")) {
				if (AdministratorNoticeCreateService.urlValidator(n)) {
					correctLinks.add(n);
				} else {
					request.getModel().setAttribute("relatedNotices", "");
					entity.setRelatedNotices(new ArrayList<>());
					errors.state(request, AdministratorNoticeCreateService.urlValidator(n), "relatedNotices", "acme.validation.relatedNotices");
				}
			}
		}

		entity.setRelatedNotices(correctLinks);
		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "acme.validation.confirm");

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {

		assert request != null;
		assert entity != null;

		Date creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);

		this.repository.save(entity);
	}

	//Auxiliares ***********************************************************
	public static boolean urlValidator(final String url) {

		if (url == null) {
			return false;
		}

		Matcher matcher = AdministratorNoticeCreateService.URL_PATTERN.matcher(url);
		return matcher.matches();
	}

}
