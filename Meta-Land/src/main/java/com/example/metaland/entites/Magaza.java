package com.example.metaland.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "magaza")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Magaza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "magaza_esya_ucreti")
    private Long magaza_esya_ucreti;

    @ManyToOne  //aynı modelden bir çok arabanın bir modeli vardır
    @JoinColumn(name = "arsa_id")
    private Arsa arsa;


}

