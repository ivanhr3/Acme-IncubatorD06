
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findMessageById(int id);

	@Query("select m from Message m where m.forum.id = ?1")
	Collection<Message> findAllByForumId(int id);

	@Query("select e from Entrepreneur e where e.userAccount.id = ?1")
	Entrepreneur findOneEntrepreneurByUserId(int id);

	@Query("select i from Investor i where i.userAccount.id = ?1")
	Investor findOneInvestorByUserId(int id);

}
