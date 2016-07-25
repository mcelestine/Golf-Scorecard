package com.makinacelestine.golfscorecard.model;

public class Hole {
    private String mHoleNumber;
    private  int mNumOfStrokes;

    public Hole(String holeNumber, int numOfStrokes) {
        mHoleNumber = holeNumber;
        mNumOfStrokes = numOfStrokes;
    }

    public String getHoleNumber() {
        return mHoleNumber;
    }

    public void setHoleNumber(String holeNumber) {
        mHoleNumber = holeNumber;
    }

    public int getNumOfStrokes() {
        return mNumOfStrokes;
    }

    public void setNumOfStrokes(int numOfStrokes) {
        mNumOfStrokes = numOfStrokes;
    }

    public void subtractStroke() {
        if (mNumOfStrokes == 0) {
            // do nothing
        } else {
            setNumOfStrokes(mNumOfStrokes - 1);
        }
    }

    public void addStroke() {
        setNumOfStrokes(mNumOfStrokes + 1);
    }
}
