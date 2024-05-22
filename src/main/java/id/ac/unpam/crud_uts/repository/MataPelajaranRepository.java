package id.ac.unpam.crud_uts.repository;

import id.ac.unpam.crud_uts.model.Guru;
import id.ac.unpam.crud_uts.model.MataPelajaran;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MataPelajaranRepository extends JpaRepository<MataPelajaran, Integer> {
  List<MataPelajaran> findByGuru(Guru guru);

  @Transactional
  void deleteById(int id);
}
