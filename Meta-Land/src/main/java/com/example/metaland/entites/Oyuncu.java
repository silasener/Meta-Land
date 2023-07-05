package com.example.metaland.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Table(name="oyuncu")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Oyuncu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id otamatik arttırma
    @Column(name="id")
    private int id;


    @Column(name="name")
    private String name;


    @Column(name="sifre")
    private  String sifre;

    @Column(name="baslangicTarihi")
    private Date baslangicTarihi;

    @Column(name="yemekMiktari")
    private Integer yemekMiktari;


    @Column(name="esyaMiktari")
    private  Integer esyaMiktari;


    @Column(name="paraMiktari")
    private Integer paraMiktari;

    @Column(name="baslangicYemekMiktari")
    private Integer baslangicYemekMiktari;

    @Column(name="baslangicEsyaMiktari")
    private Integer baslangicEsyaMiktari;

    @Column(name="baslangicParaMiktari")
    private Integer baslangicParaMiktari;

    @OneToMany(mappedBy ="oyuncu",fetch = FetchType.EAGER)
    private List<Arsa> arsalist;

    @Column(name="alanBoyutu")
    private String alanBoyutu;


}

