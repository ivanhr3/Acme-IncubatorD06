
package acme.entities.sectors;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.entities.toolRecords.ToolRecord;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sector extends DomainEntity {

	/**
	 *
	 */
	private static final long				serialVersionUID	= 1L;

	@NotBlank
	private String							name;

	@OneToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<TechnologyRecord>	technologyRecord;

	@OneToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ToolRecord>			toolRecord;

}
