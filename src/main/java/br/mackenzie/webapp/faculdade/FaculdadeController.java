package br.mackenzie.webapp.faculdade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FaculdadeController {

	@Autowired
	private FaculdadeRepo faculdadeRepo;

	@GetMapping("/api/faculdades")
	Iterable<Faculdade> getFaculdades() {
		return faculdadeRepo.findAll();
	}
	
	@GetMapping("/api/faculdades/{id}")
	Optional<Faculdade> getFaculdade(@PathVariable long id) {
		return faculdadeRepo.findById(id);
	}
	
	@PostMapping("/api/faculdades")
	Faculdade createFaculdade(@RequestBody Faculdade f) {
		Faculdade createdFac = faculdadeRepo.save(f);
		return createdFac;
	}
	
	@PutMapping("/api/faculdades/{faculdadeId}")
	Optional<Faculdade> updateFaculdade(@RequestBody Faculdade faculdadeReq, @PathVariable long faculdadeId) {
		Optional<Faculdade> opt = faculdadeRepo.findById(faculdadeId);
		if (opt.isPresent()) {
			if (faculdadeReq.getId() == faculdadeId) {
				faculdadeRepo.save(faculdadeReq);
				return opt;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da faculdade com id " + faculdadeId);
	}	
	
	@DeleteMapping("/api/faculdades/{id}")
	void deleteFaculdade(@PathVariable long id) {
		faculdadeRepo.deleteById(id);
	}	
	
}
