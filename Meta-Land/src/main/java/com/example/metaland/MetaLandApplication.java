package com.example.metaland;

import com.example.metaland.guiForms.GirisEkrani;
import org.springframework.boot.SpringApplication; // Spring Boot uygulamasını başlatmak için gerekli sınıf
import org.springframework.boot.autoconfigure.SpringBootApplication; // Spring Boot uygulaması için otomatik yapılandırma sınıfı
import org.springframework.context.ConfigurableApplicationContext; // Spring uygulama bağlamını temsil eden arabirim
import com.example.metaland.guiForms.YoneticiEkrani;

import javax.swing.*; // Java Swing GUI bileşenleri



@SpringBootApplication // Spring Boot uygulaması işaretleyici anotasyon
public class MetaLandApplication extends JFrame {

    private static ConfigurableApplicationContext context; // Spring uygulama bağlamını temsil eden değişken

    public static void main(String[] args) {
        context = SpringApplication.run(MetaLandApplication.class, args); // Spring uygulamasını başlat ve bağlamı al

        // Yönetici Ekranı'nı görünür hale getir
        SwingUtilities.invokeLater(() -> {
            YoneticiEkrani yoneticiEkrani = context.getBean(YoneticiEkrani.class); // YoneticiEkrani bean'ini al
            yoneticiEkrani.setVisible(false); // YoneticiEkrani penceresini görünür yap
        });


        SwingUtilities.invokeLater(() -> {
            GirisEkrani girisEkrani = context.getBean(GirisEkrani.class); // OyuncuEkranı bean'ini al
            girisEkrani.setVisible(true); // OyuncuEkranı penceresini görünür yap
        });


    }


    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static void setContext(ConfigurableApplicationContext context) {
        MetaLandApplication.context = context;
    }
}