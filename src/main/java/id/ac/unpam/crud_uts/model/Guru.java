package id.ac.unpam.crud_uts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_guru")
public class Guru {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id_guru;

  @Column(length = 20, nullable = false)
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

  @Column(length = 11, nullable = false)
  @NotNull
  @NotBlank
  private String tgl_lahir;

  @Column(length = 100, nullable = false)
  @NotNull
  @NotBlank
  private String alamat;

  @Column(length = 15, nullable = false)
  @NotNull
  @NotBlank
  private String no_telepon;
}
