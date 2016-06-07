package com.example.jackie.orientationdiscrimination;

import java.util.ArrayList;

/**
 * Created by Jackie on 5/24/2016.
 */
public class Staircase {

    private int baseAngle;
    private double currentAngle;
    private boolean isCW;

    private int counter;

    private ArrayList<Boolean> responses;

    private ArrayList<Double> reversals;

    // A boolean that makes sure that if it's the easiest setting that

    public Staircase(int baseAngle, boolean isCW) {
        this.baseAngle = baseAngle - 90; // Changed to -90 because the grating starts at 90 degrees
        this.isCW = isCW;
        responses = new ArrayList<>();
        counter = 0;
        reversals = new ArrayList<>();

        if (isCW) {
            this.currentAngle = AppConstants.STARTING_ANGLE + this.baseAngle;
        } else {
            this.currentAngle = this.baseAngle - AppConstants.STARTING_ANGLE;
        }
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    public int getBaseAngle() {
        return baseAngle;
    }

    public boolean isCW() {
        return isCW;
    }

    public void updateStaircase(boolean isCorrect) {

        responses.add(counter,isCorrect);
        counter++;

        double prev = this.currentAngle;

        if (isCW) {
            if (isCorrect) {
                this.currentAngle = prev - prev / 2;
            } else {
                if ((prev + prev / 2) < 90) {
                    this.currentAngle = prev + prev / 2;
                }
            }
        } else {
            if (isCorrect) {
                this.currentAngle = prev + prev / 2;
            } else {
                if ((prev + prev / 2) < 90) {
                    this.currentAngle = prev - prev / 2;
                }
            }
        }

        if (responses.size() >= 2) {
            if (responses.get(counter - 2) != responses.get(counter-1)) {
                reversals.add(reversals.size() - 1, prev - this.currentAngle);
            }
        }
    }

    public double threshold() {
        double sum = 0;

        for (int i = AppConstants.NUM_REVERSALS_IGNORE - 1; i < reversals.size(); i++) {
            sum = sum + reversals.get(i);
        }

        return sum / (reversals.size() - AppConstants.NUM_REVERSALS_IGNORE);
    }

    public boolean isFinished() {
        return reversals.size() == AppConstants.NUM_REVERSALS_TOTAL;
    }

}
