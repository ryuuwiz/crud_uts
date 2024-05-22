package id.ac.unpam.crud_uts.model;

import java.util.*;

import jakarta.persistence.*;
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
