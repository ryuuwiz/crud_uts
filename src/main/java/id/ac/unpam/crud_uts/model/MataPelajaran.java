package id.ac.unpam.crud_uts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_mtpelajaran")
public class MataPelajaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_mtpelajaran;

    @Column(length = 30, nullable = false)
    @NotNull
    @NotBlank
    private String mt_pelajaran;

    @Column(length = 30, nullable = false)
    @NotNull
    @NotBlank
    private String kategori_pelajaran;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guru")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Guru guru;
}
