package com.hs.kmc;

/**
 * Created by Hardik Senghani on 31/12/2017.
 */
public class Cluster {

    int inputDataMember;
    float data[];

    protected Cluster(int inputDataMember) {
        this.inputDataMember = inputDataMember;
        data = new float[this.inputDataMember];
    }

}
