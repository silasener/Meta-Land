package com.example.metaland.guiForms;

import com.example.metaland.MetaLandApplication;
import com.example.metaland.Service.OyuncuService;
import com.example.metaland.Service.YoneticiService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Controller
@Component
public class GirisEkrani extends JFrame {
    @Autowired
    private OyuncuService oyuncuService ;

    @Autowired
    private YoneticiService yoneticiService;




    private JPanel MainPanel;
    private JLabel Label1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel Label2;
    private JLabel Label3;
    private JButton girişYapButton;


    public GirisEkrani(){
        setContentPane(MainPanel);
        setTitle("Giris Ekrani");
        setSize(500,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (oyuncuService.checkLogin(textField1.getText(),passwordField1.getText())){
                    JOptionPane.showMessageDialog(null, "Oyuncu girişi başarılı");
                    OyuncuEkrani oyuncuEkrani=new OyuncuEkrani(oyuncuService,textField1.getText(),passwordField1.getText());
                    oyuncuEkrani.setVisible(true);



                }

                else if(yoneticiService.checkLogin(textField1.getText(),passwordField1.getText())){
                    JOptionPane.showMessageDialog(null, "Yönetici girişi başarılı");
                    YoneticiEkrani yoneticiEkrani=MetaLandApplication.getContext().getBean(YoneticiEkrani.class);
                    yoneticiEkrani.setVisible(true);

                }
                else if(!oyuncuService.checkLogin(textField1.getText(),passwordField1.getText())&& !yoneticiService.checkLogin(textField1.getText(),passwordField1.getText()))  {
                    JOptionPane.showMessageDialog(null,"Kullanıcı bilgileri hatalı");


                }


            }
        });




    }
}
