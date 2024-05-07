package id.ac.unpam.crud_uts.repository;

import id.ac.unpam.crud_uts.model.MataPelajaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MataPelajaranRepository extends JpaRepository<MataPelajaran, Integer> {
}
