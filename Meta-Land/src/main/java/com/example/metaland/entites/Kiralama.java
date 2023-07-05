package com.example.metaland.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "kiralama")

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kiralama {


    @Id
    private Long id;

    @Column(name="kiralayan_id")
    private  Long kiralayanid;

    @Column(name="kiraya_veren_id")
    private  Long kirayaverenid;


    @Column(name="kiralamasüresi")
    private Long  kiralamasüresi;

    @Column(name="kirabaslangıctarihi")
    private  Date kirabaslangıctarihi;



    @Column(name="kirabitisctarihi")
    private  Date kirabitistarihi;












}
