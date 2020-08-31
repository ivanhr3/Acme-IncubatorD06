
package acme.entities.toolRecords;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.entities.sectors.Sector;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolRecord extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@ManyToOne(cascade = CascadeType.ALL)
	private Sector				sector;

	@NotBlank
	private String				inventor;

	@NotBlank
	private String				description;

	@URL
	@NotBlank
	private String				web;

	@NotBlank
	@Email
	private String				email;

	private Boolean				openSource; //TRUE=OPEN-SOURCE     FALSE=CLOSED-SOURCE

	@Range(min = -5, max = 5)
	private Integer				stars;

}
