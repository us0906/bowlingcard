package de.ulrichschulte.bowlinggame.controller;

import de.ulrichschulte.bowlinggame.Logic.BowlingGameException;
import de.ulrichschulte.bowlinggame.Logic.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @GetMapping (path = "/api/play")
    public String getBowlingCard(@RequestParam(value="pins") List<Integer> pins) throws BowlingGameException {
        Game game = new Game(pins);
        return game.getBowlingCardAsJson();
    }
}
