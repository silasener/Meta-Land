package com.example.metaland.dataAccsess;

import com.example.metaland.entites.Calisanlar;
import com.example.metaland.entites.Emlak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalisanlarRepo extends JpaRepository<Calisanlar, Integer> {

    
}
