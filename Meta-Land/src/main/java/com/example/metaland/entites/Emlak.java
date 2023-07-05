package com.example.metaland.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "emlak")

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emlak {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;


    @ManyToOne  //aynı modelden bir çok arabanın bir modeli vardır
    @JoinColumn(name = "arsa_id")
    private Arsa arsa;


    @Column(name = "emlak_komisyonu")
    private Long emlak_komisyonu;


}

