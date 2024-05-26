package id.ac.unpam.crud_uts.controller;

import id.ac.unpam.crud_uts.model.Guru;
import id.ac.unpam.crud_uts.model.MataPelajaran;
import id.ac.unpam.crud_uts.repository.GuruRepository;
import id.ac.unpam.crud_uts.repository.MataPelajaranRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("api")
public class MataPelajaranController {

  @Autowired
  GuruRepository guruRepository;

  @Autowired
  MataPelajaranRepository mataPelajaranRepository;

  @PostMapping("guru/{id}/mapel")
  public ResponseEntity<MataPelajaran> saveMtPelajaran(@PathVariable("id") Long id_guru, @Valid @RequestBody MataPelajaran body) {
    Optional<Guru> guru = guruRepository.findById(id_guru);

    if (guru.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    body.setGuru(guru.get());
    return ResponseEntity.status(HttpStatus.CREATED).body(mataPelajaranRepository.save(body));
  }

  @GetMapping("guru/{id}/mapel")
  public ResponseEntity<List<MataPelajaran>> getAllMtPelajaran(@PathVariable("id") Long id_guru) {
    Optional<Guru> guru = guruRepository.findById(id_guru);

      return guru.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.status(HttpStatus.OK).body(mataPelajaranRepository.findAll());
  }

  @GetMapping("mapel/{id}")
  public ResponseEntity<MataPelajaran> getMtPelajaranById(@PathVariable("id") Long id) {
    Optional<MataPelajaran> mataPelajaran = mataPelajaranRepository.findById(id);

    return mataPelajaran.map(pelajaran -> ResponseEntity.status(HttpStatus.OK).body(pelajaran)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
  }

  @PutMapping("mapel/{id}")
  public ResponseEntity<MataPelajaran> updateMtPelajaran(@PathVariable("id") Long id, @RequestParam(required = false) Long id_guru, @Valid @RequestBody MataPelajaran body) {
    Optional<MataPelajaran> mataPelajaran = mataPelajaranRepository.findById(id);

    if (mataPelajaran.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    MataPelajaran _mapel = mataPelajaran.get();
    _mapel.setMt_pelajaran(body.getMt_pelajaran());
    _mapel.setKategori_pelajaran(body.getKategori_pelajaran());

    if (id_guru == null) return ResponseEntity.status(HttpStatus.OK).body(mataPelajaranRepository.save(_mapel));
      Optional<Guru> guru = guruRepository.findById(id_guru);
      if (guru.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      _mapel.setGuru(guru.get());
      return ResponseEntity.status(HttpStatus.OK).body(mataPelajaranRepository.save(_mapel));
  }

  @DeleteMapping("mapel/{id}")
  public ResponseEntity<HttpStatus> deleteMtPelajaran(@PathVariable("id") Long id) {
    mataPelajaranRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
