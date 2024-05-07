package id.ac.unpam.crud_uts.controller;

import id.ac.unpam.crud_uts.model.MataPelajaran;
import id.ac.unpam.crud_uts.service.MataPelajaranService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class MataPelajaranController {

    @Autowired
    MataPelajaranService mataPelajaranService;

    @PostMapping("/mapel")
    public ResponseEntity<?> tambahMataPelajaran(@Valid @RequestBody MataPelajaran data) {
        try {
            MataPelajaran mataPelajaran = mataPelajaranService.simpan(data);
            return new ResponseEntity<>(mataPelajaran, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/mapel")
    public ResponseEntity<?> semuaMataPelajaran() {
        try {
            List<MataPelajaran> mataPelajaran = mataPelajaranService.semua();
            return new ResponseEntity<>(mataPelajaran, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/mapel/{id}")
    public ResponseEntity<?> cariById(@PathVariable("id") Integer id) {
        try {
            MataPelajaran mataPelajaran = mataPelajaranService.satuMtPelajaran(id);
            return new ResponseEntity<>(mataPelajaran, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/mapel/{id}")
    public ResponseEntity<?> ubahMataPelajaran(@PathVariable("id") Integer id, @RequestBody MataPelajaran data) {
        try {
            MataPelajaran mataPelajaran = mataPelajaranService.ubah(id, data);
            return new ResponseEntity<>(mataPelajaran, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/mapel/{id}")
    public ResponseEntity<?> hapusMataPelajaran(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(mataPelajaranService.hapus(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
