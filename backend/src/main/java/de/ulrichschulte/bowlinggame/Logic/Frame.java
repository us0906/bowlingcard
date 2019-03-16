package de.ulrichschulte.bowlinggame.Logic;

public class Frame {

    private int[] results;
    private int index = 0;
    private int maxThrows;
    private boolean isLastFrame;

    /*
      returns true, when frame is full
     */
    public boolean addResult(int resultPins) {
            results[index] = resultPins;

            if (!isLastFrame && isStrike() && index == 0) {
                return true;
            }

            index++;
            return index >= maxThrows;
    }

    public Frame (boolean isLastFrame) {
        this.isLastFrame = isLastFrame;
        if (isLastFrame)  {
            results = new int[3];
            maxThrows = 3;
        } else {
            results = new int[2];
            maxThrows = 2;
        }
    }

    public boolean isStrike() {
        return (results[0] == 10) && (results[1] == 0);
    }

    public int getScore () {
        int result = results[0] + results[1];

        if (maxThrows == 3) {
            result += results[2];
        }
        return  result;
    }

    public String getResult(int i) {
        return String.format("%d",results[i]);
    }

    public String getScores(String sep) {
        String result = results[0] + sep + results[1];
        if (maxThrows == 3) {
            result += sep + results[2];
        }
        return result;
    }
}
