package com.example.metaland.dataAccsess;

import com.example.metaland.entites.Yonetici;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YoneticiRepo extends JpaRepository<Yonetici,Integer> {
    Yonetici findByName(String name);

}
