package com.example.metaland.Service;

import com.example.metaland.Request.YoneticiRequest;
import com.example.metaland.dataAccsess.*;
import com.example.metaland.entites.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@AllArgsConstructor
@Service
public class YoneticiManager implements YoneticiService{
    private YoneticiRepo yoneticiRepo;  //veritabaını çağırıyorum
    private OyuncuRepo oyuncuRepo;
    private ArsaRepo arsaRepo;
    private MarketRepo marketRepo;
    private EmlakRepo emlakRepo;
    private MagazaRepo magazaRepo;
    private CalisanlarRepo calisanlarRepo;
    private İsletmeRepo i̇sletmeRepo;
    private KiralamaRepo kiralamaRepo;


    @Override
    public void add(YoneticiRequest yoneticiRequest) {
        Yonetici yonetici=new Yonetici();
        yonetici.setName(yoneticiRequest.getName());
        this.yoneticiRepo.save(yonetici);
    }


    @Override
    public boolean checkLogin(String name, String password) {

        if (yoneticiRepo.findByName(name)!=null) {
            Yonetici yonetici=yoneticiRepo.findByName(name);
            return yonetici.getSifre().equals(password);
        }
        else {
            return false; // Oyuncu bulunamadıysa false döndür
        }

    }

    @Override
    public void yoneticisabitgider( Integer esyagider,Integer yemekgider,Integer paragider) {
        List<Yonetici>yonetici= yoneticiRepo.findAll();
        for (Yonetici yon:yonetici
             ) {
           yon.setGunlukParaGideri(paragider);
           yon.setGunlukYiyecekGideri(yemekgider);
           yon.setGunlukEsyaGideri(esyagider);
            yoneticiRepo.save(yon);
        }
    }

    @Override
    public void YoneticiArsa(Integer satir, Integer sutun) {
        for (int i = 0; i <satir*sutun ; i++) {
            Arsa arsa = new Arsa();
            arsa.setName(String.valueOf(i));
            arsa.setAlanturu("Arsa");
            arsaRepo.save(arsa);
        }
        List<Arsa> arsaList=arsaRepo.findAll();

        arsaList.get(0).setAlanturu("Magaza"); //magaza yoneticinin olur
        Magaza magaza = new Magaza();
        magaza.setArsa(arsaList.get(0));
        magaza.setName("yoneticimagaza");
        magaza.setMagaza_esya_ucreti(Long.valueOf(1));
        magazaRepo.save(magaza);
        arsaRepo.save(arsaList.get(0));
        İsletme i̇sletme = new İsletme();
        i̇sletme.setIsletmetipi("Magaza");
        i̇sletme.setId(magaza.getId());
        i̇sletme.setIsletmeseviyesi(1);
        i̇sletme.setIsletmekapasitesi(Integer.MAX_VALUE);
        i̇sletme.setIsletmeadi(magaza.getName());
        i̇sletme.setCalisansayisi(0);
        i̇sletme.setIsletmesabitgelirmiktari(String.valueOf(0));
        i̇sletme.setIsletmesabitgelirorani(String.valueOf(0));
        i̇sletmeRepo.save(i̇sletme);


        arsaList.get(1).setAlanturu("Market");
        Market market=new Market();
        market.setArsa(arsaList.get(1));
        market.setName("yoneticimarket");
        market.setMarket_yiyecek_ucreti(Long.valueOf(1));
        marketRepo.save(market);
        arsaRepo.save(arsaList.get(1));
        İsletme i̇sletme1 = new İsletme();
        i̇sletme1.setIsletmetipi("Market");
        i̇sletme1.setId(market.getId());
        i̇sletme1.setIsletmeseviyesi(1);
        i̇sletme1.setIsletmekapasitesi(Integer.MAX_VALUE);
        i̇sletme1.setIsletmeadi(market.getName());
        i̇sletme1.setCalisansayisi(0);
        i̇sletme1.setIsletmesabitgelirmiktari(String.valueOf(0));
        i̇sletme1.setIsletmesabitgelirorani(String.valueOf(0));
        i̇sletmeRepo.save(i̇sletme1);

        arsaList.get(2).setAlanturu("Emlak");
        Emlak emlak=new Emlak();
        emlak.setArsa(arsaList.get(2));
        emlak.setName("yoneticiemlak");
        emlak.setEmlak_komisyonu(Long.valueOf(0));
        emlakRepo.save(emlak);
        arsaRepo.save(arsaList.get(2));
        İsletme i̇sletme2 = new İsletme();
        i̇sletme2.setIsletmetipi("Emlak");
        i̇sletme2.setId(emlak.getId());
        i̇sletme2.setIsletmeseviyesi(1);
        i̇sletme2.setIsletmekapasitesi(Integer.MAX_VALUE);
        i̇sletme2.setIsletmeadi(emlak.getName());
        i̇sletme2.setCalisansayisi(0);
        i̇sletme2.setIsletmesabitgelirmiktari(String.valueOf(0));
        i̇sletme2.setIsletmesabitgelirorani(String.valueOf(0));
        i̇sletmeRepo.save(i̇sletme2);

        }

    @Override
    public void yoneticiisletmeucreti(String yoneticiisletmeucreti) {
        List<Yonetici>yonetici= yoneticiRepo.findAll();
        for (Yonetici yon:yonetici
        ) {
           yon.setYonetici_isletme_ücreti(Long.valueOf(yoneticiisletmeucreti));
            yoneticiRepo.save(yon);
        }
    }


}