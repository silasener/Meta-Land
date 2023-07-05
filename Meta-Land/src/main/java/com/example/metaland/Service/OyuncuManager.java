package com.example.metaland.Service;

import com.example.metaland.Request.OyuncuRequest;
import com.example.metaland.dataAccsess.*;
import com.example.metaland.entites.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;

@AllArgsConstructor
@Service
public class OyuncuManager implements OyuncuService {
    private OyuncuRepo oyuncuRepo; //veritabanı bağlantısı repoyu çağırdım .
    private ArsaRepo arsaRepo;
    private MarketRepo marketRepo;
    private EmlakRepo emlakRepo;
    private MagazaRepo magazaRepo;
    private CalisanlarRepo calisanlarRepo;
    private İsletmeRepo i̇sletmeRepo;
    private KiralamaRepo kiralamaRepo;
    private YoneticiRepo yoneticiRepo;


    @Override
    public void add(OyuncuRequest oyuncuRequest) {
        Oyuncu oyuncu = new Oyuncu();
        oyuncu.setName(oyuncuRequest.getName());
        this.oyuncuRepo.save(oyuncu);


    }


    @Override
    public boolean checkLogin(String name, String password) {
        // İsimle oyuncuyu bul
        if (oyuncuRepo.findByName(name) != null) {
            Oyuncu oyuncu = oyuncuRepo.findByName(name);
            return oyuncu.getSifre().equals(password);
            // Oyuncu bulunamadıysa false döndür
        } else {
            return false;
        }

        // Şifre kontrolü yaparak sonucu döndür
    }


    public void boyutGonder(String[] diziboyut) {


        List<Oyuncu> Oyuncular = oyuncuRepo.findAll();
        for (Oyuncu a : Oyuncular
        ) {
            a.setAlanBoyutu(Arrays.toString(diziboyut));
            oyuncuRepo.save(a);
        }


    }

    public void ArsaSatinal(String IdbuttonIndex, String name, String password) {

        Oyuncu oyuncu = oyuncuRepo.findOyuncuByNameAndSifre(name, password);
        Arsa arsa = new Arsa();
        arsa.setName(IdbuttonIndex);
        arsa.setAlanturu("Arsa");
        arsa.setOyuncu(oyuncu);
        arsaRepo.save(arsa);
        JOptionPane.showMessageDialog(null, "Arsa Başarıyla Satın Alınmıştır");
    }

    @Override
    public void kiralama(Oyuncu evsahibi, Oyuncu kiraci, Arsa a) {
        Kiralama kiralama = new Kiralama();
        Calendar tarihial = Calendar.getInstance();

        kiralama.setId(a.getId());
        kiralama.setKiralayanid(Long.valueOf(kiraci.getId()));
        kiralama.setKirayaverenid(Long.valueOf(evsahibi.getId()));
        kiralama.setKirabaslangıctarihi(tarihial.getTime());
        kiralamaRepo.save(kiralama);
    }


    public boolean ArsaSatinAlCont(String IdbuttonIndex, String name, String password) {


        List<Arsa> arsaList = arsaRepo.findAll();
        Oyuncu oyuncu = oyuncuRepo.findOyuncuByNameAndSifre(name, password);

        for (Arsa a : arsaList
        ) {
            if ((a.getName().equals(IdbuttonIndex)) && a.getOyuncu() != oyuncu) { //arsanın sahibi varsa oyuncuya sorulur
                Object[] secenek = {"Kirala", "Satın Al", "Vazgeç"}; //sahibinden kiralamak mı satın almak mı istersin
                int secim = JOptionPane.showOptionDialog(null, "Sahibinden Kiralamak mı Satın Almak mı İstersiniz?", "Sahibi Var", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, secenek, null);
                if (secim == JOptionPane.YES_OPTION) { //kiralanır
                    kiralama(a.getOyuncu(), oyuncu, a);
                    JOptionPane.showMessageDialog(null, "Kiralandı");
                    return false;
                } else if (secim == JOptionPane.NO_OPTION) {
                    a.setOyuncu(oyuncu); //arsa diğer oyuncuya satılır
                    oyuncuRepo.save(oyuncu);
                    arsaRepo.save(a);
                    JOptionPane.showMessageDialog(null, "Arsa Başarıyla Satın Alınmıştır");
                    return false;
                } else if (secim == JOptionPane.CANCEL_OPTION) {
                    // hiçbir şey yapılmaz
                    return false;
                }

            }

        }

        return true;

    }

    @Override
    public List<Yonetici> yoneticibul() {
        List<Yonetici> yonetici = yoneticiRepo.findAll();
        return yonetici;
    }

    @Override
    public void isletmecalisansayisi() {
        System.out.println("isletme calisan sayisi fonksiyonu ");
        List<Market> market_isletmeleri = marketRepo.findAll();
        List<Magaza> magaza_isletmeleri = magazaRepo.findAll();
        List<Emlak> emlak_isletmeleri = emlakRepo.findAll();
        List<Calisanlar> calisanlar = calisanlarRepo.findAll();
        List<İsletme> isletmeler = i̇sletmeRepo.findAll();
        for (Market m : market_isletmeleri) {
            int calisansayisi = 0;
            for (Calisanlar c : calisanlar) {
                if (m.getArsa().getId().equals(c.getArsaiİd())) {
                    calisansayisi++;
                    for (İsletme i : isletmeler) {
                        if (i.getId().equals(m.getId())) {
                            i.setCalisansayisi(calisansayisi);
                            i̇sletmeRepo.save(i);
                        }
                    }
                }
            }
        }

        for (Magaza m : magaza_isletmeleri) {
            int calisansayisi2 = 0;
            for (Calisanlar c : calisanlar) {
                if (m.getArsa().getId().equals(c.getArsaiİd())) {
                    calisansayisi2++;
                    for (İsletme i : isletmeler) {
                        if (i.getId().equals(m.getId())) {
                            i.setCalisansayisi(calisansayisi2);
                            i̇sletmeRepo.save(i);
                        }
                    }
                }
            }
        }

        for (Emlak m : emlak_isletmeleri) {
            int calisansayisi3 = 0;
            for (Calisanlar c : calisanlar) {
                if (m.getArsa().getId().equals(c.getArsaiİd())) {
                    calisansayisi3++;
                    for (İsletme i : isletmeler) {
                        if (i.getId().equals(m.getId())) {
                            i.setCalisansayisi(calisansayisi3);
                            i̇sletmeRepo.save(i);
                        }
                    }
                }
            }
        }

    }

    @Override
    public boolean isletmecalisansayisikontrol(Arsa arsa) {
        List<Market> market_isletmeleri = marketRepo.findAll();
        List<Magaza> magaza_isletmeleri = magazaRepo.findAll();
        List<Emlak> emlak_isletmeleri = emlakRepo.findAll();
        List<İsletme> isletmeler = i̇sletmeRepo.findAll();
        for (Market m : market_isletmeleri) {
            if (m.getArsa().getId().equals(arsa.getId())) {
                for (İsletme i : isletmeler) {
                    if (i.getId().equals(m.getId())) {
                        if ((i.getIsletmeseviyesi() == 1 && i.getCalisansayisi() < 3) ||
                                (i.getIsletmeseviyesi() == 2 && i.getCalisansayisi() < 6) ||
                                (i.getIsletmeseviyesi() == 3 && i.getCalisansayisi() < 12)) {
                            return true;
                        }
                    }
                }
            }
        }

        for (Magaza mag : magaza_isletmeleri) {
            if (mag.getArsa().getId().equals(arsa.getId())) {
                for (İsletme is : isletmeler) {
                    if (is.getId().equals(mag.getId())) {
                        if ((is.getIsletmeseviyesi() == 1 && is.getCalisansayisi() < 3) ||
                                (is.getIsletmeseviyesi() == 2 && is.getCalisansayisi() < 6) ||
                                (is.getIsletmeseviyesi() == 3 && is.getCalisansayisi() < 12)) {
                            return true;
                        }
                    }
                }
            }
        }


        for (Emlak em : emlak_isletmeleri) {
            if (em.getArsa().getId().equals(arsa.getId())) {
                for (İsletme isl : isletmeler) {
                    if (isl.getId().equals(em.getId())) {
                        if ((isl.getIsletmeseviyesi() == 1 && isl.getCalisansayisi() < 3) ||
                                (isl.getIsletmeseviyesi() == 2 && isl.getCalisansayisi() < 6) ||
                                (isl.getIsletmeseviyesi() == 3 && isl.getCalisansayisi() < 12)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public List<Oyuncu> calismayanoyuncular() {
        List<Calisanlar> calisanlarList = calisanlarRepo.findAll();
        List<Oyuncu> oyuncuList = oyuncuRepo.findAll();

        Iterator<Calisanlar> calisanlarIterator = calisanlarList.iterator();
        while (calisanlarIterator.hasNext()) {
            Calisanlar c = calisanlarIterator.next();
            Iterator<Oyuncu> oyuncuIterator = oyuncuList.iterator();
            while (oyuncuIterator.hasNext()) {
                Oyuncu o = oyuncuIterator.next();
                if (c.getId() == o.getId()) {
                    oyuncuIterator.remove();
                }
            }
        }
        return oyuncuList;
    }


    @Override
    public List<Oyuncu> oyuncuList() {
        List<Oyuncu> oyuncuList = oyuncuRepo.findAll();
        return oyuncuList;
    }

    @Override
    public void baslangicMiktarbelirle(int yemekmiktari, int paramiktari, int esyamiktari) {
        List<Oyuncu> Oyuncular = oyuncuRepo.findAll();
        for (Oyuncu a : Oyuncular
        ) {
            a.setBaslangicYemekMiktari(yemekmiktari);
            a.setBaslangicParaMiktari(paramiktari);
            a.setBaslangicEsyaMiktari(esyamiktari);
            oyuncuRepo.save(a);
        }
    }

    @Override
    public void EmlakSatinal(String name, String password, String IdbuttonIndex, String EmlakName) {
        Oyuncu oyuncu = oyuncuRepo.findOyuncuByNameAndSifre(name, password);
        List<Arsa> arsaList = oyuncu.getArsalist();
        for (Arsa a : arsaList
        ) {
            if (a.getName().equals(IdbuttonIndex)) {
                a.setAlanturu("Emlak");
                Emlak emlak = new Emlak();
                emlak.setEmlak_komisyonu(Long.valueOf(10));
                emlak.setArsa(a);
                emlak.setName(EmlakName);
                emlakRepo.save(emlak);
                arsaRepo.save(a);
                İsletme i̇sletme = new İsletme();
                i̇sletme.setIsletmetipi("Emlak");
                i̇sletme.setId(emlak.getId());
                i̇sletme.setIsletmeseviyesi(1);
                i̇sletme.setIsletmekapasitesi(3);
                i̇sletme.setCalisansayisi(0);
                i̇sletme.setIsletmeadi(emlak.getName());
                i̇sletme.setIsletmesabitgelirmiktari("20");
                i̇sletme.setIsletmesabitgelirorani("50");
                i̇sletmeRepo.save(i̇sletme);
            }
        }
    }

    @Override
    public void MagazaSAtinAl(String name, String password, String IdbuttonIndex, String MagazaName) {
        Oyuncu oyuncu = oyuncuRepo.findOyuncuByNameAndSifre(name, password);
        List<Arsa> arsaList = oyuncu.getArsalist();
        for (Arsa a : arsaList
        ) {
            if (a.getName().equals(IdbuttonIndex)) {
                a.setAlanturu("Magaza");
                Magaza magaza = new Magaza();
                magaza.setMagaza_esya_ucreti(Long.valueOf(1));
                magaza.setArsa(a);
                magaza.setName(MagazaName);
                magazaRepo.save(magaza);
                arsaRepo.save(a);
                İsletme i̇sletme = new İsletme();
                i̇sletme.setIsletmetipi("Magaza");
                i̇sletme.setId(magaza.getId());
                i̇sletme.setIsletmeseviyesi(1);
                i̇sletme.setIsletmekapasitesi(3);
                i̇sletme.setCalisansayisi(0);
                i̇sletme.setIsletmeadi(magaza.getName());
                i̇sletme.setIsletmesabitgelirmiktari("30");
                i̇sletme.setIsletmesabitgelirorani("50");
                i̇sletmeRepo.save(i̇sletme);


            }


        }

    }

    public void MarketSatinal(String name, String password, String IdbuttonIndex, String MarketName) {
        Oyuncu oyuncu = oyuncuRepo.findOyuncuByNameAndSifre(name, password);
        List<Arsa> arsaList = oyuncu.getArsalist();
        for (Arsa a : arsaList
        ) {
            if (a.getName().equals(IdbuttonIndex)) {
                a.setAlanturu("Market");
                Market market = new Market();
                market.setMarket_yiyecek_ucreti(Long.valueOf(1));
                market.setArsa(a);
                market.setName(MarketName);
                marketRepo.save(market);
                arsaRepo.save(a);
                İsletme i̇sletme = new İsletme();
                i̇sletme.setIsletmetipi("Market");
                i̇sletme.setId(market.getId());
                i̇sletme.setIsletmeseviyesi(1);
                i̇sletme.setIsletmekapasitesi(3);
                i̇sletme.setCalisansayisi(0);
                i̇sletme.setIsletmeadi(market.getName());
                i̇sletme.setIsletmesabitgelirmiktari("10");
                i̇sletme.setIsletmesabitgelirorani("50");
                i̇sletmeRepo.save(i̇sletme);


            }


        }


    }

    @Override
    public List<Magaza> oyuncuMagazaListesi(Oyuncu oyuncu) {
        List<Magaza> oyuncumagazalistesi = new ArrayList<>();
        for (Arsa arsa1 : oyuncu.getArsalist()
        ) {
            for (Magaza magaza : arsa1.getMagazas()
            ) {
                if (magaza != null) {
                    oyuncumagazalistesi.add(magaza);
                }
            }
        }
        return oyuncumagazalistesi;
    }


    @Override
    public List<Emlak> oyuncuEmlakListesi(Oyuncu oyuncu) {
        List<Emlak> oyuncuemlaklistesi = new ArrayList<>();

        for (Arsa arsa1 : oyuncu.getArsalist()
        ) {
            for (Emlak emlak : arsa1.getEmlaks()
            ) {
                if (emlak != null) {
                    oyuncuemlaklistesi.add(emlak);
                }
            }
        }
        return oyuncuemlaklistesi;

    }

    @Override
    public List<Market> oyuncuMarketListesi(Oyuncu oyuncu) {
        List<Market> oyuncumarketlistesi = new ArrayList<>();
        for (Arsa arsa1 : oyuncu.getArsalist()
        ) {
            for (Market market : arsa1.getMarkets()
            ) {
                if (market != null) {
                    oyuncumarketlistesi.add(market);
                }

            }

        }
        return oyuncumarketlistesi;

    }


    @Override
    public Oyuncu oyuncubul(String name, String password) {
        Oyuncu oyuncu = oyuncuRepo.findOyuncuByNameAndSifre(name, password);
        return oyuncu;
    }

    @Override
    public void baslangictarihiyaz(Date tarih) {
        List<Oyuncu> Oyuncular = oyuncuRepo.findAll();
        for (Oyuncu a : Oyuncular
        ) {
            a.setBaslangicTarihi(tarih);
            oyuncuRepo.save(a);
        }
    }

    @Override
    public void oyuncuBilgileriGuncelle(Integer gunsayisi, Integer esyagider, Integer yemekgider, Integer paragider) {
        List<Oyuncu> kaybedenlerlistesi = new ArrayList<>();
        List<Oyuncu> Oyuncular = oyuncuRepo.findAll();
        List<Calisanlar> Calisanlar = calisanlarRepo.findAll();
        List<Kiralama> kiralamaList=kiralamaRepo.findAll();
        Calendar bugun = Calendar.getInstance();
        Calendar ilerialinantarih = Calendar.getInstance();
        ilerialinantarih.add(Calendar.DATE, gunsayisi); //ileri alındıktan sonraki tarih
        long farktarih = ilerialinantarih.getTimeInMillis() - bugun.getTimeInMillis();
        long fark = farktarih / (24 * 60 * 60 * 1000);

        for (Kiralama kira:kiralamaList) {
            long kiralamasuresi = ilerialinantarih.getTimeInMillis() - kira.getKirabaslangıctarihi().getTime();
            kira.setKiralamasüresi((kiralamasuresi/ (24 * 60 * 60 * 1000))); //kaç gün olduğunu hesaplar
            kira.setKirabitistarihi(ilerialinantarih.getTime());
            kiralamaRepo.save(kira);
        }


        for (Calisanlar c : Calisanlar
        ) {

            for (Oyuncu a : Oyuncular
            ) {
                for (int i = 1; i <= gunsayisi; i++) {
                    if ((c.getId() == a.getId() && !(c.getAlanturu().equals("Emlak")))) {//oyuncu emlakta çalışmıyorsa parası azalır
                        if (a.getParaMiktari() > 0) {
                            a.setParaMiktari(a.getBaslangicParaMiktari() - (i * paragider));
                            c.setCalismaBitisi(ilerialinantarih.getTime());
                            c.setCalistigiGünSayisi((int) fark);
                            calisanlarRepo.save(c);
                            oyuncuRepo.save(a);
                        } else { //parası sıfırın altındaysa
                            oyuncuRepo.save(a);
                        }
                    }

                    if ((c.getId() == a.getId() && !(c.getAlanturu().equals("Magaza")))) { //oyuncu magazada çalışmıyorsa eşyası azalır
                        if (a.getEsyaMiktari() > 0) {
                            a.setEsyaMiktari(a.getBaslangicEsyaMiktari() - (i * esyagider));
                            c.setCalismaBitisi(ilerialinantarih.getTime());
                            c.setCalistigiGünSayisi((int) fark);
                            calisanlarRepo.save(c);
                            oyuncuRepo.save(a);
                        }
                        if (a.getEsyaMiktari() == 0 || a.getEsyaMiktari() < 0) {
                            kaybedenlerlistesi.add(a);
                            oyuncuRepo.save(a);
                        }
                    }

                    if ((c.getId() == a.getId() && !(c.getAlanturu().equals("Market")))) { //oyuncu markette çalışmıyorsa yemeği azalır
                        if (a.getYemekMiktari() > 0) {
                            a.setYemekMiktari(a.getBaslangicYemekMiktari() - (i * yemekgider));
                            c.setCalismaBitisi(ilerialinantarih.getTime());
                            c.setCalistigiGünSayisi((int) fark);
                            calisanlarRepo.save(c);
                            oyuncuRepo.save(a);
                        }
                        if (a.getYemekMiktari() == 0 || a.getYemekMiktari() < 0) {
                            kaybedenlerlistesi.add(a);
                            oyuncuRepo.save(a);
                        }
                    }


                }

                a.setBaslangicParaMiktari(a.getParaMiktari());
                a.setBaslangicEsyaMiktari(a.getEsyaMiktari());
                a.setBaslangicYemekMiktari(a.getYemekMiktari());
                oyuncuRepo.save(a);
            }


        }


        List<Oyuncu> calismayanoyuncular = calismayanoyuncular();
        System.out.println("calismayan oyuncular" + calismayanoyuncular.size());
        for (Oyuncu co : calismayanoyuncular) {
            for (int i = 1; i <= gunsayisi; i++) {
                if (co.getEsyaMiktari() > 0) {
                    co.setEsyaMiktari(co.getBaslangicEsyaMiktari() - (i * esyagider));
                    oyuncuRepo.save(co);
                }
                if (co.getEsyaMiktari() == 0 || co.getEsyaMiktari() < 0) {
                    kaybedenlerlistesi.add(co);
                    oyuncuRepo.save(co);
                }
                if (co.getParaMiktari() > 0) {
                    co.setParaMiktari(co.getBaslangicParaMiktari() - (i * paragider));
                    oyuncuRepo.save(co);
                } else {
                    oyuncuRepo.save(co);
                }

                if (co.getYemekMiktari() > 0) {
                    co.setYemekMiktari(co.getBaslangicYemekMiktari() - (i * yemekgider));
                    oyuncuRepo.save(co);
                }
                if (co.getYemekMiktari() == 0 || co.getYemekMiktari() < 0) {
                    kaybedenlerlistesi.add(co);
                    oyuncuRepo.save(co);
                }

            }
            co.setBaslangicEsyaMiktari(co.getEsyaMiktari());
            co.setBaslangicYemekMiktari(co.getYemekMiktari());
            co.setBaslangicParaMiktari(co.getParaMiktari());
            oyuncuRepo.save(co);
        }

        Set<Oyuncu> set = new HashSet<>(kaybedenlerlistesi);
        List<Oyuncu> kaybedenlerlistesi1 = new ArrayList<>(set);
        for (Oyuncu oyuncu : kaybedenlerlistesi1) {
            JOptionPane.showMessageDialog(null, oyuncu.getName() + "Adlı oyuncu oyunu kaybetmiştir");
            //oyuncuRepo.delete(oyuncu);

        }

        calismayanoyuncular.removeAll(calismayanoyuncular);

    }

    @Override
    public void oyuncubilgilerisave(List<Oyuncu> oyuncuList) {
        for (Oyuncu a : oyuncuList
        ) {
            a.setYemekMiktari(a.getBaslangicYemekMiktari());
            a.setParaMiktari(a.getBaslangicParaMiktari());
            a.setEsyaMiktari(a.getBaslangicEsyaMiktari());
            oyuncuRepo.save(a);
        }

    }


    @Override
    public void IsletmeSeviyeAtlatma(Integer hafta) {

        List<İsletme> i̇sletmes = i̇sletmeRepo.findAll();
        for (İsletme a : i̇sletmes
        ) {
            if ((a.getIsletmeseviyesi().equals(1) && a.getCalisansayisi().equals(3)) ||
                    (a.getIsletmeseviyesi().equals(2) && a.getCalisansayisi().equals(6)) ||
                    (a.getIsletmeseviyesi().equals(3) && a.getCalisansayisi().equals(12))
            ) {
                a.setIsletmeseviyesi(a.getIsletmeseviyesi() + hafta);
                a.setIsletmekapasitesi(a.getIsletmekapasitesi() * (2 * hafta));
                Double guncellenmissabitgelir=Integer.parseInt(a.getIsletmesabitgelirorani())*Integer.parseInt(a.getIsletmesabitgelirmiktari())/100.0;
                Double sonuc=guncellenmissabitgelir+Integer.parseInt(a.getIsletmesabitgelirmiktari());
                a.setIsletmesabitgelirmiktari(String.valueOf(sonuc));
                i̇sletmeRepo.save(a);
            }

        }
    }

    @Override
    public boolean oyuncuCalisCont(String IdbuttonIndex, String name, String password, List<Arsa> kayitliarsalar) {
        boolean calisabilirmi = true;
        Oyuncu oyuncu = oyuncuRepo.findOyuncuByNameAndSifre(name, password);
        List<Calisanlar> calisanlarList = calisanlarRepo.findAll();
        for (Arsa arsagez : kayitliarsalar
        ) {
            if (arsagez.getName().equals(IdbuttonIndex) && (arsagez.getAlanturu().equals("Market") || arsagez.getAlanturu().equals("Emlak") || arsagez.getAlanturu().equals("Magaza"))) {
                for (Calisanlar c : calisanlarList) {
                    if (c.getId() == oyuncu.getId()) {
                        calisabilirmi = false;
                        return false;
                    }
                }
                // işletmenin kapasitesini kontrol et
                boolean kontrol = isletmecalisansayisikontrol(arsagez);
                if (kontrol == false) {
                    return false;
                }

                if (calisabilirmi == true) {

                    if (arsagez.getName().equals("0") || arsagez.getName().equals("1") || arsagez.getName().equals("2")) {
                        Calendar tarihial = Calendar.getInstance();
                        Calisanlar calisan = new Calisanlar();
                        List<Yonetici> yoneticiList = yoneticiRepo.findAll();
                        for (Yonetici a : yoneticiList
                        ) {
                            calisan.setYonetici_calisangeliri(a.getYonetici_isletme_ücreti());
                            calisan.setOyuncu_calisangeliri(Long.valueOf(0));
                        }
                        calisan.setArsaiİd(arsagez.getId());
                        calisan.setAlanturu(arsagez.getAlanturu());
                        calisan.setId(Long.valueOf(oyuncu.getId()));
                        calisan.setCalismaBaslangici(tarihial.getTime());
                        calisanlarRepo.save(calisan);
                        isletmecalisansayisi();
                        return true;

                    } else {
                        if (arsagez.getAlanturu().equals("Magaza")) {
                            Calendar tarihial = Calendar.getInstance();
                            Calisanlar calisan = new Calisanlar();
                            calisan.setYonetici_calisangeliri(Long.valueOf(0));
                            calisan.setOyuncu_calisangeliri(Long.valueOf(10));
                            calisan.setArsaiİd(arsagez.getId());
                            calisan.setAlanturu(arsagez.getAlanturu());
                            calisan.setId(Long.valueOf(oyuncu.getId()));
                            calisan.setCalismaBaslangici(tarihial.getTime());
                            calisanlarRepo.save(calisan);
                            isletmecalisansayisi();
                            return true;
                        }
                        if (arsagez.getAlanturu().equals("Emlak")) {
                            Calendar tarihial = Calendar.getInstance();
                            Calisanlar calisan = new Calisanlar();
                            calisan.setYonetici_calisangeliri(Long.valueOf(0));
                            calisan.setOyuncu_calisangeliri(Long.valueOf(20));
                            calisan.setArsaiİd(arsagez.getId());
                            calisan.setAlanturu(arsagez.getAlanturu());
                            calisan.setId(Long.valueOf(oyuncu.getId()));
                            calisan.setCalismaBaslangici(tarihial.getTime());
                            calisanlarRepo.save(calisan);
                            isletmecalisansayisi();
                            return true;
                        }
                        if (arsagez.getAlanturu().equals("Market")) {
                            Calendar tarihial = Calendar.getInstance();
                            Calisanlar calisan = new Calisanlar();
                            calisan.setYonetici_calisangeliri(Long.valueOf(0));
                            calisan.setOyuncu_calisangeliri(Long.valueOf(30));
                            calisan.setArsaiİd(arsagez.getId());
                            calisan.setAlanturu(arsagez.getAlanturu());
                            calisan.setId(Long.valueOf(oyuncu.getId()));
                            calisan.setCalismaBaslangici(tarihial.getTime());
                            calisanlarRepo.save(calisan);
                            isletmecalisansayisi();
                            return true;
                        }


                    }


                }

            }
        }
        return false;
    }

    @Override
    public List<Arsa> arsalistkontrol() {
        List<Arsa> arsaList = arsaRepo.findAll();
        return arsaList;
    }


}







