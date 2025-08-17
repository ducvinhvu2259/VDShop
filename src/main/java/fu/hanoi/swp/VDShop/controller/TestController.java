package fu.hanoi.swp.VDShop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/health")
    public String health() {
        return "VDShop API is running!";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }

    @PostMapping("/echo")
    public String echo(@RequestBody String message) {
        return "Echo: " + message;
    }

    @GetMapping("/debug")
    public String debug() {
        return "Debug endpoint - if you see this, security is working correctly";
    }
}
