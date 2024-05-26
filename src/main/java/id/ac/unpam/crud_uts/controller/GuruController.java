package id.ac.unpam.crud_uts.controller;

import id.ac.unpam.crud_uts.model.Guru;
import id.ac.unpam.crud_uts.repository.GuruRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@Validated
@RequestMapping("api")
public class GuruController {

    @Autowired
    GuruRepository guruRepository;

    @PostMapping("guru")
    public ResponseEntity<Guru> saveGuru(@Valid @RequestBody Guru body) {
        return status(HttpStatus.CREATED).body(guruRepository.save(body));
    }

    @GetMapping("guru")
    public ResponseEntity<List<Guru>> getAllGuru() {
        return status(HttpStatus.OK).body(guruRepository.findAll());
    }

    @GetMapping("guru/{id}")
    public ResponseEntity<Guru> getGuruById(@PathVariable("id") Long id_guru) {
        Optional<Guru> guru = guruRepository.findById(id_guru);
        return guru.map(value -> status(HttpStatus.OK).body(value))
                .orElseGet(() -> status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("guru/{id}")
    public ResponseEntity<Guru> updateGuru(@PathVariable("id") Long id_guru, @Valid @RequestBody Guru body) {
        Optional<Guru> guru = guruRepository.findById(id_guru);

        if (guru.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        Guru _guru = guru.get();
        _guru.setNip(body.getNip());
        _guru.setNama_depan(body.getNama_depan());
        _guru.setNama_belakang(body.getNama_belakang());
        _guru.setTmpt_lahir(body.getTmpt_lahir());
        _guru.setTgl_lahir(body.getTgl_lahir());
        _guru.setAlamat(body.getAlamat());
        _guru.setNo_telepon(body.getNo_telepon());

        return ResponseEntity.status(HttpStatus.OK).body(guruRepository.save(_guru));

    }

    @DeleteMapping("guru/{id}")
    public ResponseEntity<HttpStatus> deleteGuru(@PathVariable("id") Long id_guru) {
        guruRepository.deleteById(id_guru);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
