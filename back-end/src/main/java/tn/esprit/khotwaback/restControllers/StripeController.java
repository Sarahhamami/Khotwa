package tn.esprit.khotwaback.restControllers;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.services.StripeService;

import java.util.Map;

@RestController
@RequestMapping("/stripe")
@CrossOrigin(origins = "http://localhost:4200")
public class StripeController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody Map<String, Object> data) throws StripeException {
        int amount = (int) data.get("amount");
        String currency = (String) data.get("currency");
        String sessionId = stripeService.createCheckoutSession(amount, currency);
        return Map.of("sessionId", sessionId);
    }
}
