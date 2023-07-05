package com.example.metaland.dataAccsess;

import com.example.metaland.entites.Oyuncu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OyuncuRepo extends JpaRepository<Oyuncu,Integer> {
    Oyuncu findByName(String name);
    Oyuncu findOyuncuByNameAndSifre(String name,String sifre);




    // Oyuncu findByName(String name);






}
