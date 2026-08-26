package com.purchasing.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity public class Product { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; public String name, sku, node; public BigDecimal unitCost; public int currentInventory, expectedDemand, storageCapacity; public Product(){} public Product(String n,String s,String node,BigDecimal c,int i,int d,int cap){name=n;sku=s;this.node=node;unitCost=c;currentInventory=i;expectedDemand=d;storageCapacity=cap;} }
