
package acme.entities.investmentRounds;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.URL;

import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.forums.Forum;
import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvestmentRound extends DomainEntity {

	/**
	 *
	 */
	private static final long				serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "[A-Z]{3}-[0-9]{2}-[0-9]{6}")
	@Column(unique = true)
	private String							ticker;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date							creationDate;

	@NotBlank
	@Pattern(regexp = "DRAFT|PUBLISHED")
	private String							status;

	@NotNull
	@Pattern(regexp = "SEED|ANGEL|SERIES-A|SERIES-B|SERIES-C|BRIDGE")
	private String							kindOfRound;

	@NotBlank
	private String							title;

	@NotBlank
	private String							description;

	@NotNull
	@Valid
	private Money							amount;

	@URL
	private String							additionalInfo;

	@OneToMany(mappedBy = "investmentRound")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<@Valid Activity>		workProgramme;

	@ManyToOne(optional = false)
	private Entrepreneur					entrepreneur;

	@OneToMany(mappedBy = "investmentRound")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<@Valid Application>	application;

	@OneToMany(mappedBy = "investmentRound")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<AccountingRecord>	accountingRecord;

	@OneToOne(optional = true)
	private Forum							forum;


	@Transient
	public boolean isBudgetCorrect() {
		Double res = 0.0;
		Boolean ok = false;

		for (Activity a : this.workProgramme) {
			res = res + a.getBudget().getAmount();

		}
		if (res.equals(this.amount.getAmount())) {
			ok = true;
		}
		return ok;
	}

}
