
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select f from Forum f where f.entrepreneur.userAccount.id = ?1")
	Collection<Forum> findAllByEntrepreneurId(int id);

	@Query("select f from Forum f where f.investor.userAccount.id = ?1")
	Collection<Forum> findAllByInvestorId(int id);

	@Query("select f from Forum f where f.id = ?1")
	Forum findForumById(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findAllMessagesById(int id);

	@Query("select e from Entrepreneur e where e.userAccount.id = ?1")
	Entrepreneur findOneEntrepreneurByUserId(int id);

	@Query("select i from Investor i where i.userAccount.id = ?1")
	Investor findOneInvestorByUserId(int id);

}
