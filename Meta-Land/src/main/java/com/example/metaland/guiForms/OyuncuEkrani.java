package com.example.metaland.guiForms;

import com.example.metaland.Service.OyuncuService;
import com.example.metaland.entites.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class OyuncuEkrani extends JFrame {

    private final OyuncuService oyuncuService;
    private final String name;
    private final String passWord;
    JPanel tablopanel = new JPanel();
    JFrame tabloframe = new JFrame("Oyuncu Bilgileri");
    private JPanel MainPanel;
    private List<JButton> buttonList;
    private JMenuBar menuBar;

    public OyuncuEkrani(OyuncuService oyuncuService, String name, String passWord) {
        this.oyuncuService = oyuncuService;
        this.name = name;
        this.passWord = passWord;

        oyuncuEkraniGoster();

    }

    public void oyuncuEkraniGoster() {
        setContentPane(MainPanel);
        setTitle("Oyuncu-Ekrani");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        menuBar = new JMenuBar();


        JMenu tablogoster = new JMenu("Oyuncuya Ait Tablo");
        JMenuItem tablo = new JMenuItem("Tablo Göster");


        tablogoster.add(tablo);
        menuBar.add(tablogoster);

        setJMenuBar(menuBar); //Menü çubuğu JFrame nesnesine eklenir


        tablo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("çalışıyo mu");
                tabloframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                tabloframe.setSize(800, 600);

                // Panel oluştur

                tablopanel.setLayout(new BoxLayout(tablopanel, BoxLayout.Y_AXIS));
                tablopanel.setBackground(Color.WHITE);

                Oyuncu oyuncu = oyuncuService.oyuncubul(name, passWord);
                String[] columnNames = {"ID", "İsim", "Şifre", "başlangıç tarihi", "Alan boyutu", "Başlangıç Eşya Miktarı", "Başlangıç Para Miktarı", "Başlangıç Yemek Miktarı", "eşya", "para", "yemek", "arsası", "marketi", "magazası", "emlakı"};
                Object[][] data = new Object[1][15];
                data[0][0] = oyuncu.getId();
                data[0][1] = oyuncu.getName();
                data[0][2] = oyuncu.getSifre();
                data[0][3] = oyuncu.getBaslangicTarihi();
                data[0][4] = oyuncu.getAlanBoyutu();
                data[0][5] = oyuncu.getBaslangicEsyaMiktari();
                data[0][6] = oyuncu.getBaslangicParaMiktari();
                data[0][7] = oyuncu.getBaslangicYemekMiktari();
                data[0][8] = oyuncu.getEsyaMiktari();
                data[0][9] = oyuncu.getParaMiktari();
                data[0][10] = oyuncu.getYemekMiktari();
                List<String> arsaliste = new ArrayList<>();
                for (Arsa as : oyuncu.getArsalist()) {
                    arsaliste.add(as.getId().toString());
                }
                data[0][11] = arsaliste;


                List<String> marketliste = new ArrayList<>(); //oyuncunun tablosunda marketleri yazdırılır
                for (Market mar : oyuncuService.oyuncuMarketListesi(oyuncu)
                ) {
                    marketliste.add(mar.getArsa().getId().toString());
                }
                data[0][12] = marketliste;


                List<String> magazaliste = new ArrayList<>(); //oyuncunun tablosunda magazaları yazdırılır
                for (Magaza mag : oyuncuService.oyuncuMagazaListesi(oyuncu)
                ) {
                    magazaliste.add(mag.getArsa().getId().toString());
                }
                data[0][13] = magazaliste;

                List<String> emlakliste = new ArrayList<>(); //oyuncunun tablosunda emlakları yazdırılır
                for (Emlak eml : oyuncuService.oyuncuEmlakListesi(oyuncu)
                ) {
                    emlakliste.add(eml.getArsa().getId().toString());
                }
                data[0][14] = emlakliste;


                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

                JTable dataTable = new JTable(tableModel);

// JScrollPane içinde JTable'ı ekle
                JScrollPane scrollPane = new JScrollPane(dataTable);
                tablopanel.add(scrollPane);

// JFrame'in içeriğini ayarla
                tabloframe.setContentPane(tablopanel);

// JFrame'i görünür hale getir
                tabloframe.setVisible(true);

            }
        });


        MainPanel.setLayout(new GridLayout(YoneticiEkrani.getSatirSayisi(), YoneticiEkrani.getSutunSayisi()));
        MainPanel.setBackground(Color.WHITE);

        // Create button list
        buttonList = new ArrayList<>();

        // Create buttons and add to main panel and button list
        for (int i = 0; i < YoneticiEkrani.getSatirSayisi() * YoneticiEkrani.getSutunSayisi(); i++) {
            JButton button = new JButton("Alan " + (i));


            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Oyuncu oyuncu = oyuncuService.oyuncubul(name, passWord);
                    super.mouseClicked(e);
                    // Get the source of the MouseEvent
                    JButton clickedButton = (JButton) e.getSource();
                    // Get the index of clicked button
                    int index = buttonList.indexOf(clickedButton);

                    String indexString = String.valueOf(index);
                    boolean arsaVarmi = false;
                    for (Arsa a : oyuncu.getArsalist()
                    ) {
                        if (indexString.equals(a.getName())) {
                            arsaVarmi = true;
                            //eger arsaya sahipsen işletmeye cevirmek için sourlur
                            Object[] options1 = {"Magaza al", "Market-Al", "Emlak-Al"};
                            int secim1 = JOptionPane.showOptionDialog(null, "Yapmak İstediğiniz İşlemi Seçin", "İşlem Uyarısı", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options1, null);

                            if (secim1 == JOptionPane.YES_OPTION) {
                                // arsayı mağazaya çevirir

                                JTextField textField = new JTextField(10);

                                Object[] inputFields = {"Magaza Adını Giriniz:", textField};
                                int option = JOptionPane.showConfirmDialog(null, inputFields, "Magaza-Adı", JOptionPane.OK_CANCEL_OPTION);

                                if (option == JOptionPane.OK_OPTION) {
                                    String input = textField.getText();
                                    oyuncuService.MagazaSAtinAl(name, passWord, indexString, input);
                                    JOptionPane.showMessageDialog(null, "Oyuncu başarılı şekilde Magaza almıştır");
                                }


                            }
                            if (secim1 == JOptionPane.NO_OPTION) {
                                //arsayı markete çevirir
                                JTextField textField = new JTextField(10);

                                Object[] inputFields = {"Market Adını Giriniz:", textField};
                                int option = JOptionPane.showConfirmDialog(null, inputFields, "Market-Adı", JOptionPane.OK_CANCEL_OPTION);


                                if (option == JOptionPane.OK_OPTION) {
                                    String input = textField.getText();
                                    oyuncuService.MarketSatinal(name, passWord, indexString, input);
                                    JOptionPane.showMessageDialog(null, "Oyuncu başarılı şekilde Market almıştır");
                                }
                            }
                            if (secim1 == JOptionPane.CANCEL_OPTION) {
                                //arsayı emlaka çevirir
                                JTextField textField = new JTextField(10);

                                Object[] inputFields = {"Emlak Adını Giriniz:", textField};
                                int option = JOptionPane.showConfirmDialog(null, inputFields, "Emlak-Adı", JOptionPane.OK_CANCEL_OPTION);


                                if (option == JOptionPane.OK_OPTION) {
                                    String input = textField.getText();
                                    oyuncuService.EmlakSatinal(name, passWord, indexString, input);
                                    JOptionPane.showMessageDialog(null, "Oyuncu başarılı şekilde Emlak almıştır");
                                }
                            }


                        }

                    }

                    // Print the index of clicked button
                    if (!arsaVarmi) {
                        System.out.println("Clicked Button Index: " + index);

                        Object[] options = {"Kirala", "Satın Al", "Çalış"};
                        int secim = JOptionPane.showOptionDialog(null, "Yapmak İstediğiniz İşlemi Seçin", "İşlem Uyarısı", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);

                        if (indexString.equals("0") || indexString.equals("1") || indexString.equals("2") ) {
                            JOptionPane.showMessageDialog(null, "Yöneticiye Ait Alan!!!");
                        } else {
                            if (secim == JOptionPane.YES_OPTION) { //kiralaya tıklandı
                                List<Arsa> arsaList = oyuncuService.arsalistkontrol();
                                for (Arsa a : arsaList
                                ) {
                                    if ((a.getName().equals(indexString)) && a.getOyuncu() != null) {
                                        oyuncuService.kiralama(a.getOyuncu(), oyuncu, a);
                                        JOptionPane.showMessageDialog(null, "Kiralandı");
                                    } else if ((a.getName().equals(indexString)) && a.getOyuncu() == null) {
                                        JOptionPane.showMessageDialog(null, "Kiralanamaz");
                                    }
                                }

                            } else if (secim == JOptionPane.NO_OPTION) {
                                //satın al tıklandı
                                if (oyuncu.getArsalist().size() - (oyuncuService.oyuncuEmlakListesi(oyuncu).size() + oyuncuService.oyuncuMagazaListesi(oyuncu).size() + oyuncuService.oyuncuMarketListesi(oyuncu).size()) < 2) {
                                    //Oyuncu üzerine işletme kurulmamış en fazla 2 arsaya aynı anda sahip olabilir.
                                    if (oyuncuService.ArsaSatinAlCont(indexString, name, passWord)) {
                                        //arsa sahibi yoksa satın alınır
                                        oyuncuService.ArsaSatinal(indexString, name, passWord);

                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Oyuncu üzerine işletme kurulmamış 2'den fazla arsaya sahip olamaz");
                                }


                            }
                        }
                       if (secim == JOptionPane.CANCEL_OPTION) {
                            boolean calismaizni = oyuncuService.oyuncuCalisCont(indexString, name, passWord, oyuncuService.arsalistkontrol());
                            if (calismaizni) {
                                JOptionPane.showMessageDialog(null, "Oyuncu Çalışmaya Başladı");
                            } else {
                                JOptionPane.showMessageDialog(null, "Oyuncu Çalışamaz");

                            }

                        }


                    }


                }
            });

            MainPanel.add(button);
            buttonList.add(button);
        }


        pack(); //swing penceresi veya bileşeninin boyutunu, bileşenlerin boyutlarına göre ayarlar.
        setVisible(true);


    }


}





