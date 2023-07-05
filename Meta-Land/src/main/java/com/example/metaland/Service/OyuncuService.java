package com.example.metaland.Service;

import com.example.metaland.Request.OyuncuRequest;
import com.example.metaland.entites.*;

import java.util.Date;
import java.util.List;

public interface OyuncuService {
    void add(OyuncuRequest oyuncuRequest); // dış dünyadan girdi request

    boolean checkLogin(String name, String password);

    void boyutGonder(String[] diziBoyut);

    void ArsaSatinal(String IdbuttonIndex, String name, String password);

    boolean ArsaSatinAlCont(String IdbuttonIndex, String name, String password);

    List<Oyuncu> oyuncuList();

    void baslangicMiktarbelirle(int yemekmiktari, int paramiktari, int esyamiktari);


    void MarketSatinal(String name, String password, String IdbuttonIndex, String Marketname);

    void EmlakSatinal(String name, String password, String IdbuttonIndex, String EmlakName);

    void MagazaSAtinAl(String name, String password, String IdbuttonIndex, String MagazaName);

    Oyuncu oyuncubul(String name, String password);

    void baslangictarihiyaz(Date tarih);

    List<Magaza> oyuncuMagazaListesi(Oyuncu oyuncu);

    List<Emlak> oyuncuEmlakListesi(Oyuncu oyuncu);

    List<Market> oyuncuMarketListesi(Oyuncu oyuncu);

    void oyuncuBilgileriGuncelle(Integer gunsayisi, Integer esyagider,Integer yemekgider,Integer paragider);

    void oyuncubilgilerisave(List<Oyuncu>oyuncuList);

    void IsletmeSeviyeAtlatma(Integer hafta);
    boolean oyuncuCalisCont(String IdbuttonIndex,String name, String password,List<Arsa> kayitliarsalar);
    List<Arsa> arsalistkontrol();
    void kiralama(Oyuncu evsahibi, Oyuncu kiraci ,Arsa a);
    List<Yonetici> yoneticibul();

    void isletmecalisansayisi();
    boolean isletmecalisansayisikontrol(Arsa arsa);
    List<Oyuncu> calismayanoyuncular();




}

