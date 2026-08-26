package com.purchasing.dto;

import java.math.BigDecimal;

/** Buyer-supplied snapshot of the live purchasing situation. */
public record ReviewRequest(
    Integer currentInventory,
    Integer expectedDemand,
    Integer openPurchaseOrders,
    Integer supplierLeadTimeDays,
    Integer supplierMinimumOrderQuantity,
    BigDecimal availableBudget,
    Integer availableStorageCapacity,
    Integer forecastHorizonDays,
    BigDecimal averageDailyConsumption) { }
