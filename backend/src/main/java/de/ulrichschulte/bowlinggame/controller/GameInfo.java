package de.ulrichschulte.bowlinggame.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GameInfo {
    Game game;
    String owner;
    private FrameInfo frames[] = new FrameInfo[Game.MAXFRAMES];

    public GameInfo (Game game) {
        this.game = game;
        for (int i = 0; i < Game.MAXFRAMES; i++) {
            frames[i] = new FrameInfo("", "", "", "");
        }
    }

    public String toJSON() throws JsonProcessingException {
        // Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(frames);
        System.out.println(json);

        return json;
    }

}
