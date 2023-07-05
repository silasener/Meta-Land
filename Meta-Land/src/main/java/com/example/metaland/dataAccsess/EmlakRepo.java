package com.example.metaland.dataAccsess;

import com.example.metaland.entites.Arsa;
import com.example.metaland.entites.Emlak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmlakRepo extends JpaRepository<Emlak,Integer> {

}
