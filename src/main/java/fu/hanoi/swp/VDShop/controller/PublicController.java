package fu.hanoi.swp.VDShop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from public endpoint!";
    }

    @GetMapping("/status")
    public String status() {
        return "Public API is accessible";
    }
}
