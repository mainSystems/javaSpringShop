package main.systems.shop.core.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.systems.shop.core.aop.ServiceTimer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class AopController {
    public final ServiceTimer serviceTimer;

    @GetMapping("/statistic")
    public String showStatistic() {
        return serviceTimer.timerToString();
    }
}
