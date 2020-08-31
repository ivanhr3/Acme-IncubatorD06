
package acme.features.administrator.sector;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.sectors.Sector;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSectorRepository extends AbstractRepository {

	@Query("select s from Sector s where s.id = ?1")
	Sector findSectorById(int id);

	@Query("select s from Sector s")
	Collection<Sector> findAllSectors();

}
