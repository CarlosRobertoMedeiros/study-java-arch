package br.com.roberto.advanced.advancedtechniques.repository;


import br.com.roberto.advanced.advancedtechniques.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
