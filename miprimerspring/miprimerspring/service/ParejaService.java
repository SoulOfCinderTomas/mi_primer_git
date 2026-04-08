package co.edu.unbosque.miprimerspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.miprimerspring.dto.ParejaDTO;
import co.edu.unbosque.miprimerspring.entity.Pareja;
import co.edu.unbosque.miprimerspring.repository.ParejaRepository;

@Service
public class ParejaService implements CRUDOPERATION<ParejaDTO> {

	@Autowired // inicializa el objeto
	private ParejaRepository parejaRep;
	@Autowired
	private ModelMapper mapper;

	public ParejaService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(ParejaDTO data) { // Si necesito validar, lo hago acá
		Pareja entity = mapper.map(data, Pareja.class);
		parejaRep.save(entity);
		return 0;
	}

	@Override
	public List<ParejaDTO> getAll() {
		List<Pareja> entityList = (List<Pareja>) parejaRep.findAll();
		List<ParejaDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> {
			ParejaDTO dto = mapper.map(entity, ParejaDTO.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public int deleteById(Long id) {
		Optional<Pareja> encontrado = parejaRep.findById(id);
		if (encontrado.isPresent()) {
			parejaRep.delete(encontrado.get());
			return 0;
		}
		return 1;
	}

	@Override
	public int updateById(Long id, ParejaDTO data) {
		Optional<Pareja> encontrado = parejaRep.findById(id);
		if (encontrado.isPresent()) {
			Pareja temp = encontrado.get();
			temp.setNombre(data.getNombre());
			temp.setEdad(data.getEdad());
			temp.setCarrera(data.getCarrera());
			temp.setFechaAniversario(data.getFechaAniversario());
			parejaRep.save(temp);
			return 0;
		}
		return 1;
	}

	@Override
	public long count() {
		return parejaRep.count();
	}

	@Override
	public boolean exist(Long id) {
		return parejaRep.existsById(id) ? true : false;
	}

	public List<ParejaDTO> findByNombre(String nombre) {

		Optional<List<Pareja>> encontrados = parejaRep.findByNombre(nombre);
		List<Pareja> entityList = encontrados.get();
		List<ParejaDTO> dtoList = new ArrayList<>();
		if (encontrados.isPresent() && !encontrados.get().isEmpty()) {
			entityList.forEach((entity) -> {
				ParejaDTO dto = mapper.map(entity, ParejaDTO.class);
				dtoList.add(dto);
			});
			return dtoList;
		} else {
			return new ArrayList<ParejaDTO>();
		}

	}

}
