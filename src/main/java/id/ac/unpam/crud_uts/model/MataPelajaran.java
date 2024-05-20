package id.ac.unpam.crud_uts.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guru", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Guru guru;
}
