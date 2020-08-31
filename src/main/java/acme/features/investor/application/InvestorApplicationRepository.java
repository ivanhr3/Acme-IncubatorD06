
package acme.features.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findApplicationById(int id);

	@Query("select a from Application a where a.investor.id = ?1")
	Collection<Application> findAllByInvestorId(int id);

	@Query("select a from Application a where a.investor.id = ?1 and a.investmentRound.id = ?2")
	Application findApplicationByInvestorId(int id, int investmentRoundId);

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select i from Investor i where i.id = ?1")
	Investor findOneInvestorById(int id);

	@Query("select a from Application a where a.ticker = ?1")
	Application findOneApplicationByTicker(String ticker);
}
