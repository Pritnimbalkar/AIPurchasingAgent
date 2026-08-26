package com.purchasing.dto;
import java.math.BigDecimal; import java.util.*;
public record RecommendationResponse(Long id,String product,String sku,String node,String supplier,String scenario,int systemQuantity,int currentInventory,int expectedDemand,int openPurchaseOrders,int leadTimeDays,int minimumOrderQuantity,int supplierAvailability,BigDecimal unitCost,BigDecimal availableBudget,int storageCapacity){ }
