package id.ac.unpam.crud_uts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.ac.unpam.crud_uts.model.Guru;

@Repository
public interface GuruRepository extends JpaRepository<Guru, Integer> {
}
