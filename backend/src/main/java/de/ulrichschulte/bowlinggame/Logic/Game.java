package de.ulrichschulte.bowlinggame.Logic;

import java.util.List;

public class Game {

    private static int MAXFRAMES = 10;
    private static int MAXROLLS = 21;

    private Frame[] frames = new Frame[MAXFRAMES];
    private int[] rolls = new int[MAXROLLS];
    private int frameIndex = 0;
    private int rollsIndex = 0;

    public Game(List<Integer> pins) {
        for (int i = 0; i < MAXFRAMES; i++) {
            frames[i] = new Frame(i == (MAXFRAMES-1)) ;
        }

        if (pins != null && pins.size() > 0) {
            for (int i = 0; i < pins.size(); i++) {
                this.bowl(pins.get(i));
            }
        }
    }

    public void bowl(int resultingPins) {
        if (frames[frameIndex].addResult(resultingPins)) {
            frameIndex++;
        };
        rolls[rollsIndex] = resultingPins;
        rollsIndex++;
    }

    public int getFrameScore (int zielIndex) {
        int result = 0;
        int frameIndex = 0;
        int frameSum = 0;

        for (int i = 0; i <= zielIndex; i++) {
            frameSum = rolls[frameIndex];
            if (frameSum == 10) {  // strike
                frameSum += rolls[frameIndex+1] + rolls[frameIndex+2];
                frameIndex += 1;
            } else {
                frameSum += rolls[frameIndex+1];

                if (frameSum == 10) { // spare
                    frameSum += rolls[frameIndex + 2];
                }
                frameIndex+=2;
            }
            result += frameSum;
        }
        return result;

    }

    public int getTotalScore(){
        return getFrameScore(MAXFRAMES-1);
    }

    public String getBowlingCard() {
        String result = "";
        for (int i = 0; i < MAXFRAMES; i++) {
            result += frames[i].getScores(" ") + "| " + getFrameScore(i) + "| ";
        }
        result += getTotalScore();
        return result;
    }

    public String getBowlingCardAsJson() {
        String result = "[";
        for (int i = 0; i < MAXFRAMES; i++) {
            result += "{ scores: [" + frames[i].getScores(", ") + "] , " + "score: " + getFrameScore(i) + "} ";
            if (i < MAXFRAMES-1) {
                result += ", ";
            }
        }
        result += ", total:" + getTotalScore();
        result += "]";
        return result;
    }
}
