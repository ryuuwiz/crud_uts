package id.ac.unpam.crud_uts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import id.ac.unpam.crud_uts.exception.ResourceNotFoundException;
import id.ac.unpam.crud_uts.model.Guru;
import id.ac.unpam.crud_uts.repository.GuruRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class GuruController {

  @Autowired
  GuruRepository guruRepository;

  @GetMapping("guru")
  public ResponseEntity<List<Guru>> semuaDataGuru(@RequestParam(required = false) Integer id) {
    List<Guru> semuaGuru = new ArrayList<Guru>();

    if (id == null)
      guruRepository.findAll().forEach(semuaGuru::add);
    else
      semuaGuru.add(guruRepository.findById(id).get());

    if (semuaGuru.isEmpty())
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    return new ResponseEntity<>(semuaGuru, HttpStatus.OK);
  }

  @PostMapping("guru")
  public ResponseEntity<Guru> simpanDataGuru(@Valid @RequestBody Guru guru) {
    Guru _guru = new Guru();

    _guru.setNip(guru.getNip());
    _guru.setNama_depan(guru.getNama_depan());
    _guru.setNama_belakang(guru.getNama_belakang());
    _guru.setTmpt_lahir(guru.getTmpt_lahir());
    _guru.setTgl_lahir(guru.getTgl_lahir());
    _guru.setAlamat(guru.getAlamat());
    _guru.setNo_telepon(guru.getNo_telepon());

    guruRepository.save(_guru);

    return new ResponseEntity<>(_guru, HttpStatus.CREATED);
  }

  @PutMapping("guru/{id}")
  public ResponseEntity<Guru> ubahDataGuru(@PathVariable("id") Integer id, @Valid @RequestBody Guru entity) {
    Guru guru = guruRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Tidak ditemukan Guru dengan ID = " + id));

    Guru _guru = guru;
    _guru.setNip(entity.getNip());
    _guru.setNama_depan(entity.getNama_depan());
    _guru.setNama_belakang(entity.getNama_belakang());
    _guru.setTmpt_lahir(entity.getTmpt_lahir());
    _guru.setTgl_lahir(entity.getTgl_lahir());
    _guru.setAlamat(entity.getAlamat());
    _guru.setNo_telepon(entity.getNo_telepon());
    guruRepository.save(_guru);

    return new ResponseEntity<>(_guru, HttpStatus.OK);
  }

  @DeleteMapping("guru/{id}")
  public ResponseEntity<HttpStatus> hapusDataGuru(@PathVariable("id") Integer id) {
    Optional<Guru> guru = guruRepository.findById(id);

    if (guru.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    guruRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
