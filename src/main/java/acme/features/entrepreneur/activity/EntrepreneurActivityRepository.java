
package acme.features.entrepreneur.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurActivityRepository extends AbstractRepository {

	@Query("select a from Activity a where a.id = ?1")
	Activity findOneById(int id);

	@Query("select i.workProgramme from InvestmentRound i where i.id = ?1")
	Collection<Activity> findAllByInvestmentRoundId(int id);

	@Query("select e from Entrepreneur e where e.id = ?1")
	Entrepreneur findOneEntrepreneurById(int id);
}
