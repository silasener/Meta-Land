package com.example.metaland.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "isletmeturu")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter

public class İsletme {

    @Id
    private Long id;
    @Column(name = "isletmeTipi")
    private String isletmetipi;

    @Column(name = "isletmeAdi")
    private String isletmeadi;

    @Column(name = "calisansayisi")
    private Integer calisansayisi;


    @Column(name = "isletmeseviyesi")
    private Integer isletmeseviyesi;

    @Column(name = "isletmekapasitesi")
    private Integer isletmekapasitesi;


    @Column(name = "isletmesabitgelirorani")
    private String isletmesabitgelirorani;

    @Column(name = "isletmesabitgelirmiktari")
    private String isletmesabitgelirmiktari;




}
