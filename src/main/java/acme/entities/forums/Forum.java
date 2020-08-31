
package acme.entities.forums;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Forum extends DomainEntity {

	/**
	 *
	 */
	private static final long			serialVersionUID	= 1L;

	@NotNull
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "forum")
	private Collection<@Valid Message>	message;

	@OneToOne(optional = true)
	@Valid
	private Entrepreneur				entrepreneur;

	@OneToOne(optional = true)
	@Valid
	private Investor					investor;

	@NotNull
	@OneToOne(optional = false)
	@Valid
	private InvestmentRound				investmentRound;

}
