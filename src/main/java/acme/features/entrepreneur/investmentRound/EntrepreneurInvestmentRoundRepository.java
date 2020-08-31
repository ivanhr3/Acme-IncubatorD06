
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.spamlist.Spamlist;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i")
	Collection<InvestmentRound> findActives();

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select i from InvestmentRound i where i.entrepreneur.id = ?1")
	Collection<InvestmentRound> findAllByEntrepreneurId(int id);

	@Query("select e from Entrepreneur e where e.id = ?1")
	Entrepreneur findOneEntrepreneurById(int id);

	@Query("select e from Entrepreneur e where e.userAccount.id = ?1")
	Entrepreneur findOneEntrepreneurByUserId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select i from InvestmentRound i where i.ticker = ?1")
	InvestmentRound findInvestmentRoundByTicker(String ticker);

	@Query("select s.englishSpamword from Spamword s")
	Collection<String> findAllSpamwordsEN();

	@Query("select s.spanishSpamword from Spamword s")
	Collection<String> findAllSpamwordsES();

	@Query("select s from Spamlist s")
	Spamlist findOneSpamlist();

	@Query("select a from Application a where a.investmentRound.id = ?1")
	Collection<Application> findAllApplicationsByInvestmentRoundId(int id);
}
