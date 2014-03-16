package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 03.03.14.
 */
public class Main {
    public static void main(String args[]) {

        double matrix[][] = new double[3][3];

        matrix[0][0] = 1;
        matrix[0][1] = 4;
        matrix[0][2] = 3;
        matrix[1][0] = 8;
        matrix[1][1] = 0;
        matrix[1][2] = 1;
        matrix[2][0] = 1;
        matrix[2][1] = 2;
        matrix[2][2] = 1;

    }

    public double[][] reflectionMethod(double [][] matrix) {
        int n = matrix[0].length;

        for (int k = 0; k < n; k++) {
            double vector[] = getVector(matrix, k);
            double s = getColumnTotal(vector, k);
            double normA = getNormOfVectorK(vector[k], s);



        }
    }

    public double getColumnTotal(final double [] vector, final int k) {
        int n = vector.length;

        double result = 0;
        for (int j = k + 1; j < n; j++) {
            result += vector[j];
        }
        return result;
    }

    public double getNormOfVectorK(final double element, final double s) {
        double result = Math.sqrt( element * element + s);
        return result;
    }

    public double[] getVector(final double matrix[][], final int k) {
        int n = matrix.length;
        double vector[] = new double[n - k];

        for (int j = k; k < n; j++) {
            vector[j - k] = matrix[j][k];
        }
        return vector;
    }
}
