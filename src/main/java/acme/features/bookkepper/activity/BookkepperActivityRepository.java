
package acme.features.bookkepper.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkepperActivityRepository extends AbstractRepository {

	@Query("select a from Activity a where a.id = ?1")
	Activity findOneById(int id);

	@Query("select i.workProgramme from InvestmentRound i where i.id = ?1")
	Collection<Activity> findAllByInvestmentRoundId(int id);
}
