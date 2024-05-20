package id.ac.unpam.crud_uts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestBody;

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

    return new ResponseEntity<>(mataPelajaranRepository.findByGuru(guru), HttpStatus.OK);
  }

  @PostMapping("guru/{id}/mapel")
  public ResponseEntity<MataPelajaran> simpanMapelDenganIdGuru(@PathVariable("id") Integer id,
      @Valid @RequestBody MataPelajaran entity) {
    MataPelajaran _mapel = guruRepository.findById(id).map(guru -> {
      entity.setGuru(guru);
      return mataPelajaranRepository.save(entity);
    }).orElseThrow(() -> new ResourceNotFoundException("Tidak ditemukan Guru dengan ID = " + id));

    return new ResponseEntity<>(_mapel, HttpStatus.CREATED);
  }

}
