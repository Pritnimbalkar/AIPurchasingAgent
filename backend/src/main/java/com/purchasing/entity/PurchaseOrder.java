package com.purchasing.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity public class PurchaseOrder { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @ManyToOne public Product product; @ManyToOne public Supplier supplier; public int quantity; public String status; public BigDecimal cost; public PurchaseOrder(){} public PurchaseOrder(Product p,Supplier s,int q){product=p;supplier=s;quantity=q;cost=p.unitCost.multiply(BigDecimal.valueOf(q));status="CREATED";} }
