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

        //double matrix[][] = new double[4][4];

        /*matrix[0][0] = 1;
        matrix[0][1] = 4;
        matrix[0][2] = 3;
        matrix[1][0] = 8;
        matrix[1][1] = 0;
        matrix[1][2] = 1;
        matrix[2][0] = 15;
        matrix[2][1] = 2;
        matrix[2][2] = 9;
        matrix[3][0] = 6;
        matrix[3][1] = 7;
        matrix[3][2] = 3;*/

        Main example = new Main();
        double[][] matr = example.reflectionMethod(matrix);

    }

    public double[][] reflectionMethod(double [][] matrix) {
        int n = matrix[0].length;

       // double vector[] = getVector(matrix, 0);

        double vectorOfDiagonalMatrixElements[] = new double[n];

        for (int k = 0; k < n - 1 ; k++) {
            double vector[] = getVector(matrix, k);

            double s = getColumnTotal(vector, k);
            double normA = getNormOfVector(vector[0], s);

            vector[0] -= normA;
            double normX = getNormOfVector(vector[0], s);

            int m = vector.length;
            for (int j = 0; j < m; j++) {
                vector[j] /= normX;
            }

            printMatrix(matrix);
            System.out.println();

            for (int j = k; j < n; j++) {

                double tempVector[] = new double[m];
                for (int p = k; p < n; p++) {

                    double [] vectorOfProduct = getVectorOfTransformationMatrix(vector, p - k);

                    for (int i = k; i < n; i++) {
                        tempVector[ p - k] += vectorOfProduct[i - k] * matrix[i][j];
                    }

                }

                for (int i = k; i < n; i++) {
                    matrix[i][j] = tempVector[i - k];
                }

                printMatrix(matrix);
                System.out.println();

            }

            vectorOfDiagonalMatrixElements[k] = matrix[k][k];

            for (int i = k; i < n; i++) {
                matrix[i][k] = vector[i - k];
            }

            printMatrix(matrix);
            System.out.println();

        }

        return matrix;

    }

    public double getColumnTotal(final double [] vector, final int k) {
        int n = vector.length;

        double result = 0;
        for (int j = 1; j < n; j++) {
            result += (vector[j] * vector[j]);
        }
        return result;
    }

    public double getNormOfVector(final double element, final double s) {
        double result = Math.sqrt( element * element + s);
        return result;
    }

    public double[] getVector(final double matrix[][], final int k) {
        int n = matrix.length;
        double vector[] = new double[n - k];

        for (int j = k; j < n; j++) {
            vector[j - k] = matrix[j][k];
        }
        return vector;
    }

    public double[] getProductOfVectors(final double vector[], final double element) {
        int n = vector.length;
        double result[] = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = element * vector[i];
        }

        return result;
    }

    public double[] getVectorOfTransformationMatrix(final double[] vector, final int iteration) {
        double[] vectorOfProduct = getProductOfVectors(vector, vector[iteration]);
        int m = vector.length;

        for (int i = 0; i < m; i++) {
            if (iteration == i) {
                vectorOfProduct[i] = 1 - 2 * vectorOfProduct[i];
            } else {
                vectorOfProduct[i] *= (-2);
            }
        }

        return vectorOfProduct;
    }

    public void printMatrix(final double[][] matrix) {
        int m = matrix.length;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < m; j++) {

               System.out.print(matrix[i][j] + " ");

            }
            System.out.println();
        }

    }

}


/*double vectorOfProduct[] = {0};
            double vectorOfColumn[] = new double[m];

            for (int iteration = m - 1; iteration >= 0; iteration--) {
                vectorOfProduct = getVectorOfTransformationMatrix(vector, iteration);

                for (int j = 0; j < m; j++) {
                    vectorOfColumn[iteration] += vectorOfProduct[j] * matrix[j + k][k];
                }
            }


            for (int j = 0; j < m; j++) {
                double element = 0;
                for (int i = k; i < n; i++) {
                    element += (vectorOfProduct[i - k] * matrix[i][j + k]);
                }
                matrix[k][j + k] = element;
            }

            for (int j = k; j < n; j++) {
                matrix[j][k] = vectorOfColumn[j - k];
            }*/
