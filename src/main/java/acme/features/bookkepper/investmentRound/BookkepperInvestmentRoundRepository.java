
package acme.features.bookkepper.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkepper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookkepperInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i")
	Collection<InvestmentRound> findActives();

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select i from InvestmentRound i where i.id in (select a.investmentRound.id from AccountingRecord a where a.bookkepper.id = ?1)")
	Collection<InvestmentRound> findManyByBookkepperRecorded(int id);

	@Query("select i from InvestmentRound i where i.id not in (select a.investmentRound.id from AccountingRecord a where a.bookkepper.id = ?1)")
	Collection<InvestmentRound> findManyByBookkepperNonRecorded(int id);

	@Query("select b from Bookkepper b where b.id = ?1")
	Bookkepper findOneBookkepperById(int id);
}
