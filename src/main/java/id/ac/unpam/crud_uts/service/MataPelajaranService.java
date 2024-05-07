package id.ac.unpam.crud_uts.service;

import id.ac.unpam.crud_uts.model.MataPelajaran;
import id.ac.unpam.crud_uts.repository.MataPelajaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MataPelajaranService {

    @Autowired
    MataPelajaranRepository mataPelajaranRepository;

    public MataPelajaran simpan(MataPelajaran data) {
        MataPelajaran mataPelajaran = new MataPelajaran();

        mataPelajaran.setMt_pelajaran(data.getMt_pelajaran());
        mataPelajaran.setKategori_pelajaran(data.getKategori_pelajaran());
        mataPelajaran.setId_kurikulum(data.getId_kurikulum());

        return mataPelajaranRepository.save(mataPelajaran);
    }

    public List<MataPelajaran> semua() {
        List<MataPelajaran> semuaMtPelajaran = mataPelajaranRepository.findAll();

        return semuaMtPelajaran;
    }

    public MataPelajaran satuMtPelajaran(Integer id) {
        MataPelajaran mataPelajaran = mataPelajaranRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("Mata Pelajaran tidak ditemukan");
        });

        return mataPelajaran;
    }

    public MataPelajaran ubah(Integer id, MataPelajaran data) {
        MataPelajaran mataPelajaran = mataPelajaranRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("Mata Pelajaran tidak ditemukan");
        });

        mataPelajaran.setMt_pelajaran(data.getMt_pelajaran());
        mataPelajaran.setKategori_pelajaran(data.getKategori_pelajaran());
        mataPelajaran.setId_kurikulum(data.getId_kurikulum());

        mataPelajaran = mataPelajaranRepository.save(mataPelajaran);

        return mataPelajaran;
    }

    public Object hapus(Integer id) {
        mataPelajaranRepository.deleteById(id);
        return null;
    }

}
