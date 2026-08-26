package com.purchasing.entity;
import jakarta.persistence.*;
@Entity public class PurchaseRecommendation { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @ManyToOne public Product product; @ManyToOne public Supplier supplier; public int quantity; public String scenario; public PurchaseRecommendation(){} public PurchaseRecommendation(Product p,Supplier s,int q,String sc){product=p;supplier=s;quantity=q;scenario=sc;} }
