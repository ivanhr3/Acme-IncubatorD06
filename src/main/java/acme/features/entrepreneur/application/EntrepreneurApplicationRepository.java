
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findApplicationById(int id);

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1")
	Collection<Application> findAllByEntrepreneurId(int id);

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1 order by a.ticker asc")
	Collection<Application> findAllByEntrepreneurIdGroupedByTicker(int id);

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1 order by a.creationDate asc")
	Collection<Application> findAllByEntrepreneurIdGroupedByCreationDate(int id);
}
