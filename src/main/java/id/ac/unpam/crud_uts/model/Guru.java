package id.ac.unpam.crud_uts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_guru")
public class Guru {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_guru;

  @Column(length = 30, nullable = false)
  @NotNull
  @NotBlank
  private String nip;

  @Column(length = 30, nullable = false)
  @NotNull
  @NotBlank
  private String nama_depan;

  @Column(length = 30, nullable = false)
  @NotNull
  @NotBlank
  private String nama_belakang;

  @Column(length = 30, nullable = false)
  @NotNull
  @NotBlank
  private String tmpt_lahir;

  @Column(nullable = false)
  @NotNull
  @DateTimeFormat
  private Date tgl_lahir;

  @Column(length = 100, nullable = false)
  @NotNull
  @NotBlank
  private String alamat;

  @Column(length = 20, nullable = false)
  @NotNull
  @NotBlank
  private String no_telepon;

  @OneToMany(mappedBy = "guru", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnoreProperties("guru")
  private List<MataPelajaran> mtapelajaran = new ArrayList<>();
}
