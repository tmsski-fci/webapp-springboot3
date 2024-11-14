package br.mackenzie.webapp.professor;

import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepo extends CrudRepository<Professor, Long> {
}