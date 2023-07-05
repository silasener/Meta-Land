package com.example.metaland.Service;

import com.example.metaland.Request.YoneticiRequest;
import org.springframework.stereotype.Component;

@Component
public interface YoneticiService  {
    void add(YoneticiRequest yoneticiRequest);
    boolean checkLogin(String name, String password);
    void yoneticisabitgider(Integer esyagider,Integer yemekgider,Integer paragider);
    void YoneticiArsa(Integer satir,Integer sutun);

    void yoneticiisletmeucreti(String yoneticiisletmeucreti);


}