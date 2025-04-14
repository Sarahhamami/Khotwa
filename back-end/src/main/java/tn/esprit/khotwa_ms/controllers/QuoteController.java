package tn.esprit.khotwa_ms.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteController {

    @GetMapping("/api/quote")
    public ResponseEntity<String> getQuote() {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("https://zenquotes.io/api/today", String.class);
        return ResponseEntity.ok(quote);
    }

}
