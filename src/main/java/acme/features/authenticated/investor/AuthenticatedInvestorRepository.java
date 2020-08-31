
package acme.features.authenticated.investor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Investor;
import acme.entities.sectors.Sector;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestorRepository extends AbstractRepository {

	@Query("select i from Investor i where i.userAccount.id = ?1")
	Investor findOneInvestorByUserId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select s from Sector s")
	Collection<Sector> findAllSectors();
}
