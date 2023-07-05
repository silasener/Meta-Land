package com.example.metaland.entites;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "calisanlar")

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calisanlar {


    @Id
    private Long id;


    @Column(name = "arsa_id")
    private Long arsaiİd;

    @Column(name = "alanturu")
    private String alanturu;


    @Column(name = "calismabaslangici")
    private Date calismaBaslangici;


    @Column(name = "calismabitisi")
    private Date calismaBitisi;


    @Column(name = "calistigigünsayisi")
    private Integer calistigiGünSayisi;


    @Column(name = "oyuncu_isletmeucreti")
    private  Long oyuncu_calisangeliri;


    @Column(name = "yonetici_isletmeucreti")
    private  Long  yonetici_calisangeliri;


































}
