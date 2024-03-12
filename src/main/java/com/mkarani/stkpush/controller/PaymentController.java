package com.mkarani.stkpush.controller;


import com.mkarani.stkpush.dto.AccessTokenResponse;
import com.mkarani.stkpush.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @GetMapping(path = "/token", produces = "application/json")
    public ResponseEntity<AccessTokenResponse> getAccessToken() throws IOException {
        return ResponseEntity.ok(paymentService.getAccessToken());
    }


    @PostMapping("/callback-post")
    public ResponseEntity<?> handleFlexibleRequestPost(@RequestBody Map<String, Object> requestBody) {
        System.out.println("Data from request body in controller: " + requestBody);
        return ResponseEntity.ok("Request processed successfully");
    }

    @GetMapping("/callback-post")
    public ResponseEntity<?> handleFlexibleRequestGet(@RequestBody Map<String, Object> requestBody) {
        System.out.println("Data from request body in controller: " + requestBody);
        return ResponseEntity.ok("Request processed successfully");
    }
}
