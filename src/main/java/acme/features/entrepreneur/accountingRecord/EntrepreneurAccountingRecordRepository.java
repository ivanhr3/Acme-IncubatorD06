
package acme.features.entrepreneur.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecords.AccountingRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurAccountingRecordRepository extends AbstractRepository {

	@Query("select a from AccountingRecord a where a.id = ?1")
	AccountingRecord findOneById(int id);

	@Query("select i.accountingRecord from InvestmentRound i where i.id = ?1")
	Collection<AccountingRecord> findAllByInvestmentRoundId(int id);

}
