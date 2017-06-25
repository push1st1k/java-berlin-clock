package com.ubs.opsit.interviews.model;

public class BerlinClock implements Clock {

    private static final char EMPTY = 'O';
    private static final char RED = 'R';
    private static final char YELLOW = 'Y';

    private static final String LINE_SEP = System.lineSeparator();

    private int hours;
    private int minutes;
    private int seconds;

    private final char[][] out = new char[][]{
            {EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY}
    };

    @Override
    public String display() {
        StringBuilder result = new StringBuilder(32);
        for (int i = 0; i < out.length; i++) {
            for (int j = 0; j < out[i].length; j++) {
                result.append(out[i][j]);
            }
            if(i != out.length - 1) {
                result.append(LINE_SEP);
            }
        }
        return result.toString();
    }

    private void updateDisplay() {
        fillRow(0, (this.seconds + 1) % 2, YELLOW);
        fillRow(1, this.hours / 5, RED);
        fillRow(2, this.hours % 5, RED);
        fillRow(3, this.minutes / 5, YELLOW);
        fillRow(4, this.minutes % 5, YELLOW);
        updateMinutesRow();
    }

    private void fillRow(int rowNum, int lightLamps, char value) {
        for (int i = 0; i < lightLamps; i++) {
            out[rowNum][i] = value;
        }
    }

    private void updateMinutesRow() {
        int countRedInRow3 = this.minutes / 15;
        for (int i = 1; i <= countRedInRow3; i++) {
            out[3][i * 3 - 1] = RED;
        }
    }

    public Clock setTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

        updateDisplay();

        return this;
    }
}
