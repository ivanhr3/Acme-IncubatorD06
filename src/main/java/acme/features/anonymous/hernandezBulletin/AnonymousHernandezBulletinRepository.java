
package acme.features.anonymous.hernandezBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.HernandezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousHernandezBulletinRepository extends AbstractRepository {

	@Query("select b from HernandezBulletin b")
	Collection<HernandezBulletin> findMany();

}
