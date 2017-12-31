package com.hs.kmc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hardik Senghani on 31/12/2017.
 */
public class KMeanCluster {

    int k;
    int inputDataMembers;
    Cluster[] clusters;

    public KMeanCluster(int noOfCluster, int inputDataMembers) {
        k = noOfCluster;
        this.inputDataMembers = inputDataMembers;
        clusters = new Cluster[k];
        for (int i = 0; i < k; i++) {
            clusters[i] = new Cluster(inputDataMembers);
        }
    }

    public Observation generateInputDataInstance() {
        Observation input = new Observation(inputDataMembers);
        return input;
    }

    public float distance(float data1[], float data2[]) {
        float distance = 0;
        float val;
        for (int i = 0; i < data1.length; i++) {
            val = data1[i] - data2[i];
            distance += val * val;
        }
        return distance;
    }

    public int getNearestCluster(Observation observation) {

        int nearestClusterIndex = 0;

        float[] distance = new float[k];
        float a;
        for (int i = 0; i < k; i++) {
            distance[i] = distance(observation.getData(), clusters[i].data);
            if (distance[nearestClusterIndex] > distance[i]) {
                nearestClusterIndex = i;
            }
        }

        return nearestClusterIndex;
    }

    private boolean updateClusterCentroid(ArrayList<Observation> inputBucket[]) {

        Cluster[] oldClusterData = clusters.clone();

        for (int j = 0; j < inputBucket.length; j++) {
            float data[] = new float[inputDataMembers];
            for (Observation observation : inputBucket[j]) {
                for (int i = 0; i < inputDataMembers; i++) {
                    data[i] += observation.getDataAtIndex(i);
                }
            }
            for (int i = 0; i < inputDataMembers; i++) {
                clusters[j].data[i] = data[i] / inputBucket[j].size();
            }
        }

        boolean dataChanged = false;

        for (int i = 0; i < oldClusterData.length; i++) {

            boolean clusterChanged = true;

            if (distance(clusters[i].data, oldClusterData[i].data) < 0.0001f) {
                clusterChanged = false;
            }

            dataChanged = dataChanged | clusterChanged;
            if (dataChanged) {
                break;
            }
        }

        return dataChanged;

    }

    public void fit(List<Observation> input, int maxNoOfIteration) {

        boolean dataChanged = false;

        for (int i = 0; i < maxNoOfIteration; i++) {

            ArrayList<Observation> inputBucket[] = new ArrayList[k];
            for (int j = 0; j < k; j++) {
                inputBucket[j] = new ArrayList<Observation>();
            }

            for (Observation observation : input) {
                inputBucket[getNearestCluster(observation)].add(observation);
            }

            dataChanged = updateClusterCentroid(inputBucket);

            if (!dataChanged) {
                break;
            }

        }

    }

    public Cluster classify(Observation observation) {
        return clusters[getNearestCluster(observation)];
    }

}
