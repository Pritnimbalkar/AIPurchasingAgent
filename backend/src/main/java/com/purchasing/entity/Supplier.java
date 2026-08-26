package com.purchasing.entity;
import jakarta.persistence.*;
@Entity public class Supplier { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; public String name; public int leadTimeDays, minimumOrderQuantity, availableQuantity; public Supplier(){} public Supplier(String n,int l,int m,int a){name=n;leadTimeDays=l;minimumOrderQuantity=m;availableQuantity=a;} }
