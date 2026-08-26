package com.purchasing.controller;
import com.purchasing.agent.PurchasingAgent; import com.purchasing.dto.*; import com.purchasing.entity.*; import com.purchasing.repository.*; import com.purchasing.service.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api") @CrossOrigin(origins="http://localhost:5173") public class PurchasingController { final RecommendationRepository recs; final PurchasingTools tools; final PurchasingAgent agent; final PurchaseOrderService pos; public PurchasingController(RecommendationRepository r,PurchasingTools t,PurchasingAgent a,PurchaseOrderService p){recs=r;tools=t;agent=a;pos=p;}
 @GetMapping("/purchasing/recommendations") public List<RecommendationResponse> list(){return recs.findAll().stream().map(this::dto).toList();}
 @GetMapping("/purchasing/recommendations/{id}") public RecommendationResponse get(@PathVariable Long id){return dto(recs.findById(id).orElseThrow());}
 @PostMapping("/purchasing/agent/review/{id}") public ReviewResponse review(@PathVariable Long id,@RequestBody(required=false) ReviewRequest request){return agent.review(id,request);}
 @GetMapping("/purchase-orders/{id}") public PurchaseOrder order(@PathVariable Long id){return pos.get(id);}
 @PostMapping("/purchase-orders/{id}/validate") public Map<String,Object> validate(@PathVariable Long id){return pos.validate(pos.get(id));}
 private RecommendationResponse dto(PurchaseRecommendation r){Product p=r.product;Supplier s=r.supplier;return new RecommendationResponse(r.id,p.name,p.sku,p.node,s.name,r.scenario,r.quantity,tools.getProductInventory(p),tools.getExpectedDemand(p),tools.getOpenPurchaseOrders(p),s.leadTimeDays,s.minimumOrderQuantity,s.availableQuantity,p.unitCost,tools.getBudget(),tools.getStorageCapacity(p));}
}
