package com.example.metaland.guiForms;

import com.example.metaland.Service.OyuncuService;
import com.example.metaland.Service.YoneticiService;
import com.example.metaland.dataAccsess.ArsaRepo;
import com.example.metaland.dataAccsess.YoneticiRepo;
import com.example.metaland.entites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Controller
public class YoneticiEkrani extends JFrame {


    private JPanel mainPanel;
    private JTable dataTable;

    @Autowired
    private YoneticiService yoneticiService;

    @Autowired
    private OyuncuService oyuncuService;
    private ArsaRepo arsaRepo;



    private JPanel MainPanel;
    private JButton verigoster;
    private JButton BilgileriKaydetbutton;
    private JButton alanboyutButton;
    private JTextField yoneticiisletmeucretitextField;
    private JTextField alanboyuttextfield;
    private JTextField baslangicyemektext;
    private JTextField baslangicesyatext;
    private JTextField baslangicparatext;
    private JTextField yemekgideritextfield;
    private JTextField esyagideritextfield;
    private JTextField paragideritextfield;


    private String boyutalma;

   private static int satirSayisi;
    private static int sutunSayisi;


    private int artıkGun;
    private int toplamGun;

    private YoneticiRepo yoneticiRepo;
   private JMenuBar menuBar;

    public YoneticiEkrani() {


        setContentPane(MainPanel);
        setTitle("Yonetici-Ekrani");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        menuBar = new JMenuBar();


        JMenu menu=new JMenu("Oyuncu Tablolarını Oluştur");
        JMenuItem menuitem = new JMenuItem("Oyuncu Tablosunu Göster");
        JMenu tarih=new JMenu("Tarih Değiştirme");
        JMenuItem tarihidegistirme=new JMenuItem("Tarihi İleri Al");


           menu.add(menuitem);
           tarih.add(tarihidegistirme);

           menuBar.add(tarih);
           menuBar.add(menu);

        setJMenuBar(menuBar); //Menü çubuğu JFrame nesnesine eklenir


        tarihidegistirme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar tarihial = Calendar.getInstance();
                String gunsayisi = JOptionPane.showInputDialog("İleri alınması için gün sayısı giriniz");
                int gun=Integer.parseInt(gunsayisi);
                oyuncuService.oyuncuBilgileriGuncelle(gun,Integer.parseInt(esyagideritextfield.getText()),Integer.parseInt(yemekgideritextfield.getText()),Integer.parseInt(paragideritextfield.getText()));

                int hafta=gun/7;  //5 gün     hafta=0      3gün  hafta=0
                artıkGun=gun%7;    //artık gun 5                artık gün 3

                toplamGun+=artıkGun;  //toplam gun =5            toplamgun =8

                if (toplamGun>=7){
                    hafta=hafta+(toplamGun/7);
                }

                if(hafta!=0){
                    oyuncuService.IsletmeSeviyeAtlatma(hafta);
                }



            }
        });



                menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setSize(800, 600);

                MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
                MainPanel.setBackground(Color.WHITE);

                List<Oyuncu> oyuncuList =oyuncuService.oyuncuList();
                System.out.println(oyuncuList.size());

// Verileri JTable'a ekle
                String[] columnNames = {"ID", "İsim", "Şifre","başlangıç tarihi","Alan boyutu", "Başlangıç Eşya Miktarı", "Başlangıç Para Miktarı","Başlangıç Yemek Miktarı",
                       "eşya","para","yemek", "arsası","marketi","magazası","emlakı"};
                Object[][] data = new Object[oyuncuList.size()][15];
                for (int i = 0; i < oyuncuList.size(); i++) {
                    data[i][0] = oyuncuList.get(i).getId();
                    data[i][1] = oyuncuList.get(i).getName();
                    data[i][2] = oyuncuList.get(i).getSifre();
                    data[i][3] = oyuncuList.get(i).getBaslangicTarihi();
                    data[i][4] = oyuncuList.get(i).getAlanBoyutu();
                    data[i][5] = oyuncuList.get(i).getBaslangicEsyaMiktari();
                    data[i][6] = oyuncuList.get(i).getBaslangicParaMiktari();
                    data[i][7] = oyuncuList.get(i).getBaslangicYemekMiktari();
                    data[i][8] = oyuncuList.get(i).getEsyaMiktari();
                    data[i][9] = oyuncuList.get(i).getParaMiktari();
                    data[i][10] = oyuncuList.get(i).getYemekMiktari();
                    List<String> arsaliste=new ArrayList<>();
                    for (Arsa as:oyuncuList.get(i).getArsalist()
                    ) { arsaliste.add(as.getId().toString());
                    }

                    data[i][11]= arsaliste;

                    List<String> marketliste=new ArrayList<>(); //oyuncunun tablosunda marketleri yazdırılır
                    for (Market mar:oyuncuService.oyuncuMarketListesi(oyuncuList.get(i))
                    ) {
                        marketliste.add(mar.getArsa().getId().toString());
                    }
                    data[i][12]=marketliste;


                    List<String> magazaliste=new ArrayList<>(); //oyuncunun tablosunda magazaları yazdırılır
                    for (Magaza mag:oyuncuService.oyuncuMagazaListesi(oyuncuList.get(i))
                    ) {
                        magazaliste.add(mag.getArsa().getId().toString());
                    }
                    data[i][13]=magazaliste;

                    List<String> emlakliste=new ArrayList<>(); //oyuncunun tablosunda emlakları yazdırılır
                    for (Emlak eml:oyuncuService.oyuncuEmlakListesi(oyuncuList.get(i))
                    ) {
                        emlakliste.add(eml.getArsa().getId().toString());
                    }
                    data[i][14]=emlakliste;
                }




                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

// JTable oluştur
                JTable dataTable = new JTable(tableModel);

// JScrollPane içinde JTable'ı ekle
                JScrollPane scrollPane = new JScrollPane(dataTable);
                MainPanel.add(scrollPane);
                setVisible(true);

            }
        });


        BilgileriKaydetbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boyutalma= alanboyuttextfield.getText();
                String[] boyutlar = boyutalma.split("x"); //3x4 değerini 3 ve 4 olarak tutmak için
                satirSayisi= Integer.parseInt(boyutlar[0]);
                sutunSayisi= Integer.parseInt(boyutlar[1]);
                yoneticiService.YoneticiArsa(satirSayisi,sutunSayisi);
                oyuncuService.boyutGonder(boyutlar);
                yoneticiService.yoneticiisletmeucreti(yoneticiisletmeucretitextField.getText());
                yoneticiService.yoneticisabitgider(Integer.parseInt(esyagideritextfield.getText()),Integer.parseInt(yemekgideritextfield.getText()),Integer.parseInt(paragideritextfield.getText()));
                oyuncuService.baslangicMiktarbelirle(Integer.parseInt(baslangicyemektext.getText()),Integer.parseInt(baslangicparatext.getText()),Integer.parseInt(baslangicesyatext.getText()));
                esyagideritextfield.setEnabled(false);
                yemekgideritextfield.setEnabled(false);
                paragideritextfield.setEnabled(false);
                baslangicparatext.setEnabled(false);
                alanboyuttextfield.setEnabled(false);
                baslangicyemektext.setEnabled(false);
                baslangicesyatext.setEnabled(false);
                yoneticiisletmeucretitextField.setEnabled(false);
                Calendar baslangictarihi= Calendar.getInstance();
                oyuncuService.baslangictarihiyaz(baslangictarihi.getTime());
                List<Oyuncu> oyuncuList=oyuncuService.oyuncuList();
                oyuncuService.oyuncubilgilerisave(oyuncuList);
                BilgileriKaydetbutton.setEnabled(false);
            }
        });



    }

    public YoneticiRepo getYoneticiRepo() {
        return yoneticiRepo;
    }

    public void setYoneticiRepo(YoneticiRepo yoneticiRepo) {
        this.yoneticiRepo = yoneticiRepo;
    }

    public YoneticiService getYoneticiService() {
        return yoneticiService;
    }

    public void setYoneticiService(YoneticiService yoneticiService) {
        this.yoneticiService = yoneticiService;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        MainPanel = mainPanel;
    }

    public JButton getButton1() {
        return verigoster;
    }

    public void setButton1(JButton button1) {
        this.verigoster = button1;
    }

    public JButton getAlanboyutButton() {
        return alanboyutButton;
    }

    public void setAlanboyutButton(JButton alanboyutButton) {
        this.alanboyutButton = alanboyutButton;
    }

    public JTextField getAlanboyuttextfield() {
        return alanboyuttextfield;
    }

    public void setAlanboyuttextfield(JTextField alanboyuttextfield) {
        this.alanboyuttextfield = alanboyuttextfield;
    }

    public String getBoyutalma() {
        return boyutalma;
    }

    public void setBoyutalma(String boyutalma) {
        this.boyutalma = boyutalma;
    }

    public static int getSatirSayisi() {
        return satirSayisi;
    }

    public static void setSatirSayisi(int satirSayisi) {
        YoneticiEkrani.satirSayisi = satirSayisi;
    }

    public static int getSutunSayisi() {
        return sutunSayisi;
    }

    public static void setSutunSayisi(int sutunSayisi) {
        YoneticiEkrani.sutunSayisi = sutunSayisi;
    }

    public JTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(JTable dataTable) {
        this.dataTable = dataTable;
    }

    public OyuncuService getOyuncuService() {
        return oyuncuService;
    }

    public void setOyuncuService(OyuncuService oyuncuService) {
        this.oyuncuService = oyuncuService;
    }

    public ArsaRepo getArsaRepo() {
        return arsaRepo;
    }

    public void setArsaRepo(ArsaRepo arsaRepo) {
        this.arsaRepo = arsaRepo;
    }

    public JButton getVerigoster() {
        return verigoster;
    }

    public void setVerigoster(JButton verigoster) {
        this.verigoster = verigoster;
    }

    public JButton getBilgileriKaydetbutton() {
        return BilgileriKaydetbutton;
    }

    public void setBilgileriKaydetbutton(JButton bilgileriKaydetbutton) {
        BilgileriKaydetbutton = bilgileriKaydetbutton;
    }

    public JTextField getBaslangicyemektext() {
        return baslangicyemektext;
    }

    public void setBaslangicyemektext(JTextField baslangicyemektext) {
        this.baslangicyemektext = baslangicyemektext;
    }

    public JTextField getBaslangicesyatext() {
        return baslangicesyatext;
    }

    public void setBaslangicesyatext(JTextField baslangicesyatext) {
        this.baslangicesyatext = baslangicesyatext;
    }

    public JTextField getBaslangicparatext() {
        return baslangicparatext;
    }

    public void setBaslangicparatext(JTextField baslangicparatext) {
        this.baslangicparatext = baslangicparatext;
    }


    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public int getArtıkGun() {
        return artıkGun;
    }

    public void setArtıkGun(int artıkGun) {
        this.artıkGun = artıkGun;
    }

    public int getToplamGun() {
        return toplamGun;
    }

    public void setToplamGun(int toplamGun) {
        this.toplamGun = toplamGun;
    }
}