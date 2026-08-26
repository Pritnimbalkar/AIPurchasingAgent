package com.purchasing.dto;
import java.util.*;
public record ReviewResponse(String decision,int originalQuantity,int recommendedQuantity,double confidence,String summary,List<Factor> factors,boolean actionTaken,Long purchaseOrderId,String purchaseOrderStatus,String validationStatus,List<String> validationIssues,String recoveryResult,boolean humanApprovalRequired,String evaluation,List<String> buyerRecommendations,List<String> validationChecks){ public record Factor(String factor,String impact,String explanation){} }
