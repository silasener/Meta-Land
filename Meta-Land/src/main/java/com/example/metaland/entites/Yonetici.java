package com.example.metaland.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "yonetici")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Yonetici {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //id otamatik arttırma
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @Column(name = "sifre")
    private String sifre;

    @Column(name="gunlukYiyecekGideri")
    private Integer gunlukYiyecekGideri ;

    @Column(name="gunlukEsyaGideri")
    private Integer gunlukEsyaGideri;

    @Column(name="gunlukParaGideri")
    private Integer gunlukParaGideri;



    @Column(name = "yonetici_isletme_ücreti")
    private Long yonetici_isletme_ücreti;









}


