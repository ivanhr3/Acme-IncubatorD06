
package acme.entities.spamlist;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spamlist extends DomainEntity {

	/**
	 *
	 */
	private static final long		serialVersionUID	= 1L;

	@NotNull
	@Valid
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "spamlist")
	private Collection<Spamword>	spamwordslist;

	@NotNull
	private Double					threshold;

}
