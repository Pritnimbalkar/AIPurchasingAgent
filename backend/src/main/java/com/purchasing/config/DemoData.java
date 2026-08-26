package com.purchasing.config;

import com.purchasing.entity.*;
import com.purchasing.repository.*;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration
public class DemoData {
  @Bean
  CommandLineRunner seed(ProductRepository products, SupplierRepository suppliers, RecommendationRepository recommendations, PurchaseOrderRepository orders) {
    return args -> {
      if (products.count() > 0) return;
      Supplier freshMart = suppliers.save(new Supplier("FreshMart Distributors", 10, 100, 1500));
      Supplier harvest = suppliers.save(new Supplier("Harvest & Co.", 21, 200, 1000));
      Product water = add(products, "Sparkling Water 500ml", "SW-500-24", "Bengaluru FC", "12.50", 250, 950, 1300);
      orders.save(new PurchaseOrder(water, freshMart, 100));
      recommend(recommendations, water, freshMart, "MODIFIED");
      recommend(recommendations, add(products, "Organic Oats 1kg", "OO-1KG", "Bengaluru FC", "8.00", 100, 900, 1200), freshMart, "ACCEPTED");
      recommend(recommendations, add(products, "Premium Orange Juice 1L", "PJ-1L", "Bengaluru FC", "60.00", 50, 850, 100), freshMart, "HUMAN REVIEW");
      recommend(recommendations, add(products, "Cold Brew Coffee", "CBC-330", "Mumbai FC", "14.00", 400, 1200, 1800), freshMart, "ACCEPTED");
      recommend(recommendations, add(products, "Basmati Rice 5kg", "BR-5KG", "Delhi FC", "10.00", 450, 1400, 2200), harvest, "MODIFIED");
      recommend(recommendations, add(products, "Sensitive Baby Wipes", "BW-72", "Bengaluru FC", "4.50", 700, 600, 1500), freshMart, "REJECTED");
      recommend(recommendations, add(products, "Unsweetened Almond Milk", "AM-1L", "Mumbai FC", "11.00", 100, 1000, 150), harvest, "HUMAN REVIEW");
      recommend(recommendations, add(products, "Peanut Protein Bars", "PB-12", "Delhi FC", "20.00", 400, 1500, 2000), freshMart, "MODIFIED");
      recommend(recommendations, add(products, "Frozen Green Peas", "FGP-500", "Bengaluru FC", "6.50", 600, 500, 1400), freshMart, "REJECTED");
      recommend(recommendations, add(products, "Herbal Shampoo", "HS-400", "Mumbai FC", "15.00", 200, 1000, 1600), freshMart, "ACCEPTED");
      recommend(recommendations, add(products, "Tri-Colour Quinoa", "TQ-500", "Delhi FC", "18.00", 250, 900, 300), harvest, "HUMAN REVIEW");
      recommend(recommendations, add(products, "Jasmine Green Tea", "JGT-25", "Bengaluru FC", "7.00", 300, 1300, 1800), harvest, "MODIFIED");
    };
  }
  private Product add(ProductRepository products, String name, String sku, String node, String cost, int inventory, int demand, int capacity) {
    return products.save(new Product(name, sku, node, new BigDecimal(cost), inventory, demand, capacity));
  }
  private void recommend(RecommendationRepository recommendations, Product product, Supplier supplier, String scenario) {
    recommendations.save(new PurchaseRecommendation(product, supplier, 800, scenario));
  }
}
