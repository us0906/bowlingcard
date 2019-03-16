package de.ulrichschulte.bowlinggame.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {
    @GetMapping (path = "/play")
    public String getBowlingCard(@RequestParam(value="pins") List<Integer> pins) {
        String result = pins.toString();
        return result;
    }
}
