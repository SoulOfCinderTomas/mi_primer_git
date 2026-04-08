package co.edu.unbosque.miprimerspring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.miprimerspring.entity.Pareja;
import java.util.List;

public interface ParejaRepository extends CrudRepository<Pareja, Long> {

	public Optional<List<Pareja>> findByNombre(String nombre);

}
