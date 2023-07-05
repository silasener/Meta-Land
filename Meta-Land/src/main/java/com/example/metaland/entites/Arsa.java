package com.example.metaland.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "arsa")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Arsa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @ManyToOne     // aynı markaya ait birden çok modelin bir markası vardır
    @JoinColumn(name = "oyuncu_id", referencedColumnName = "id")
    private Oyuncu oyuncu;


    @Column(name = "alanturu")
    private String alanturu;

    @OneToMany(mappedBy = "arsa", fetch = FetchType.EAGER)
    private List<Market> markets;

    @OneToMany(mappedBy = "arsa", fetch = FetchType.EAGER)
    private List<Emlak> emlaks;

    @OneToMany(mappedBy = "arsa", fetch = FetchType.EAGER)
    private List<Magaza> magazas;


}
