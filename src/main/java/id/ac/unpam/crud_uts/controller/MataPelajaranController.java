package id.ac.unpam.crud_uts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import id.ac.unpam.crud_uts.exception.ResourceNotFoundException;
import id.ac.unpam.crud_uts.model.Guru;
import id.ac.unpam.crud_uts.model.MataPelajaran;
import id.ac.unpam.crud_uts.repository.GuruRepository;
import id.ac.unpam.crud_uts.repository.MataPelajaranRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MataPelajaranController {

  @Autowired
  GuruRepository guruRepository;

  @Autowired
  MataPelajaranRepository mataPelajaranRepository;

  @GetMapping("guru/{id}/mapel")
  public ResponseEntity<List<MataPelajaran>> semuaMapelDenganIdGuru(@PathVariable("id") Integer id) {
    Guru guru = guruRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Tidak ditemukan Guru dengan ID = " + id));
    List<MataPelajaran> mataPelajarans = mataPelajaranRepository.findByGuru(guru);

    return new ResponseEntity<>(mataPelajarans, HttpStatus.OK);
  }

  @GetMapping("mapel/{id}")
  public ResponseEntity<MataPelajaran> mapelDenganId(@PathVariable("id") Integer id) {
    Optional<MataPelajaran> mapel = mataPelajaranRepository.findById(id);

    if (mapel.isPresent())
      return new ResponseEntity<>(mapel.get(), HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("guru/{id}/mapel")
  public ResponseEntity<MataPelajaran> simpanMapelDenganIdGuru(@PathVariable("id") Integer id,
      @Valid @RequestBody MataPelajaran entity) {
    Guru _guru = guruRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Tidak ditemukan Guru dengan ID = " + id));
    entity.setGuru(_guru);
    MataPelajaran _mapel = mataPelajaranRepository.save(entity);

    return new ResponseEntity<>(_mapel, HttpStatus.CREATED);
  }

  @PutMapping("mapel/{id}")
  public ResponseEntity<MataPelajaran> ubahData(@RequestParam(required = false) Integer id_guru,
      @PathVariable("id") Integer id,
      @Valid @RequestBody MataPelajaran entity) {
    MataPelajaran mapel = mataPelajaranRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Tidak ditemukan Mata Pelajaran dengan ID = " + id));

    if (id_guru == null) {
      mapel.setMt_pelajaran(entity.getMt_pelajaran());
      mapel.setKategori_pelajaran(entity.getKategori_pelajaran());
    } else {
      Guru guru = guruRepository.findById(id_guru)
          .orElseThrow(() -> new ResourceNotFoundException("Tidak ditemukan Guru dengan ID = " + id_guru));
      mapel.setMt_pelajaran(entity.getMt_pelajaran());
      mapel.setKategori_pelajaran(entity.getKategori_pelajaran());
      mapel.setGuru(guru);
    }

    mataPelajaranRepository.save(mapel);
    return new ResponseEntity<>(mapel, HttpStatus.OK);
  }

  @DeleteMapping("mapel/{id}")
  public ResponseEntity<HttpStatus> hapusMataPelajaran(@PathVariable("id") Integer id) {
    mataPelajaranRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
