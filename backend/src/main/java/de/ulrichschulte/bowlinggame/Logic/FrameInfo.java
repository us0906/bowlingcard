package de.ulrichschulte.bowlinggame.Logic;

public class FrameInfo {
    String result1; // <nix>, 1-9, X
    String result2; // <nix>, 1-9, /, -
    String isStrike;
    String total;

    public FrameInfo(String result1, String result2, String isStrike, String total) {
        this.result1 = result1;
        this.result2 = result2;
        this.isStrike = isStrike;
        this.total = total;
    }

}
