package com.example.metaland.dataAccsess;

import com.example.metaland.entites.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepo extends JpaRepository<Market,Integer> {

}
