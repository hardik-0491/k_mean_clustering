package com.hs.kmc;

/**
 * Created by Hardik Senghani on 31/12/2017.
 */
public class Observation {

    float data[];

    protected Observation(int n) {
        data = new float[n];
    }

    public void setDataAtIndex(int index, float value) {
        if (index < 0 || index >= data.length) {
            return;
        }
        data[index] = value;
    }

    public float getDataAtIndex(int index) {
        return data[index];
    }

    public float[] getData() {
        return data;
    }

}
