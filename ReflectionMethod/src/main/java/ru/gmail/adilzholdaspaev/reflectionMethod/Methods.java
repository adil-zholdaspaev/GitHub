package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 11.04.14.
 */

public class Methods {

    public double[][] reflectionMethod(double[][] matrix) {
        int n = matrix.length;

        double vectorOfDiagonalMatrixElements[] = new double[n];

        double vector[];
        double s;
        double normA;
        double normX;

        for (int k = 0; k < n - 1; k++) {

            vector = getVector(matrix, k);

            s = getSquareOfVectorElements(vector);
            normA = getNormOfVector(vector[0], s);

            if (vector[0] <= 0) {
                vector[0] -= normA;
            } else {
                vector[0] += normA;
            }

            normX = getNormOfVector(vector[0], s);

            int m = vector.length;
            for (int j = 0; j < m; j++) {
                vector[j] /= normX;
            }

            double temp;
            for (int j = k; j < n; j++) {
                temp = 0;

                for (int t = k; t < n; t++) {
                    temp += (vector[t - k] * matrix[t][j]);
                }

                temp += temp;

                for (int i = k; i < n; i++) {
                    matrix[i][j] -= (temp * vector[i - k]);
                }
            }

            vectorOfDiagonalMatrixElements[k] = matrix[k][k];

            for (int i = k; i < n; i++) {
                matrix[i][k] = vector[i - k];
            }

        }

        //обращение верхне-треугольной матрицы

        double elem = 0;
        for (int j = 0; j < n - 1; j++) {

            elem = matrix[j][j];
            matrix[j][j] = vectorOfDiagonalMatrixElements[j];
            vectorOfDiagonalMatrixElements[j] = elem;
        }


        for (int k = 0; k < n; k++) {
            matrix[k][k] = 1 / matrix[k][k];
        }

        for (int i = n - 1; i >= 0; i--) {

            vector = new double[n - i];
            for (int j = 0; j < n - i; j++) {
                vector[j] = matrix[i][j + i];
            }

            for (int j = i + 1; j < n; j++) {

                double sum = 0;

                for (int k = i + 1; k <= j; k++) {
                    sum += (vector[k - i] * matrix[k][j]);
                }

                matrix[i][j] = -vector[0] * sum;
            }
        }


        //необходимо сделать преобразования

        double w[];
        for (int k = n - 2; k >= 0; k--) {

            w = new double[n - k];

            w[0] = vectorOfDiagonalMatrixElements[k];
            for (int i = k + 1; i < n; i++) {
                w[i - k] = matrix[i][k];
                matrix[i][k] = 0;
            }


            double temp = 0;
            for (int i = 0; i < n; i++) {
                temp = 0;

                for (int t = k; t < n; t++) {
                    temp += (matrix[i][t] * w[t - k]);
                }

                temp += temp;

                for (int j = k; j < n; j++) {
                    matrix[i][j] -= (w[j - k] * temp);
                }
            }

        }


        /*printMatrix(matrix);
        System.out.println();*/

        return matrix;
    }

    public double[][] reflectionMethodWithReplacement(double[][] matrix) {
        int n = matrix.length;

        double vectorOfDiagonalMatrixElements[] = new double[n];

        int vectorOfReplacement[] = new int[n - 1];
        double vectorOfLength[] = new double[n];
        int nRep = 0;

        double vector[];
        double s;
        double normA;
        double normX;

        for (int j = 0; j < n; j++) {

            for (int i = 0; i < n; i++) {

                vectorOfLength[j] += (matrix[i][j] * matrix[i][j]);

            }
        }


        int nReplacement = 0;

        for (int k = 0; k < n - 1; k++) {

            // перестановка векторов

            double lengthOfCurrentVector = vectorOfLength[k];
            nReplacement = k;

            for (int i = k + 1; i < n; i++) {

                if (lengthOfCurrentVector < vectorOfLength[i]) {

                    lengthOfCurrentVector = vectorOfLength[i];

                    nReplacement = i;

                }

            }

            if (nReplacement != k) {
                vectorOfReplacement[k] = nReplacement;

                double temp = 0;
                temp = vectorOfLength[nReplacement];
                vectorOfLength[nReplacement] = vectorOfLength[k];
                vectorOfLength[k] = temp;

                for (int i = 0; i < n; i++) {

                    temp = matrix[i][k];
                    matrix[i][k] = matrix[i][nReplacement];
                    matrix[i][nReplacement] = temp;

                }

            }

           /* printMatrix(matrix);
            System.out.println();*/

            for (int j = k + 1; j < n; j++) {
                vectorOfLength[j] -= ( matrix[k][j] * matrix[k][j] );
            }

            //

            vector = getVector(matrix, k);

            s = getSquareOfVectorElements(vector);
            normA = getNormOfVector(vector[0], s);

            if (vector[0] <= 0) {
                vector[0] -= normA;
            } else {
                vector[0] += normA;
            }

            normX = getNormOfVector(vector[0], s);

            int m = vector.length;
            for (int j = 0; j < m; j++) {
                vector[j] /= normX;
            }


            double temp;
            for (int j = k; j < n; j++) {

                temp = 0;

                for (int t = k; t < n; t++) {

                    temp += (vector[t - k] * matrix[t][j]);

                }

                temp += temp;

                for (int i = k; i < n; i++) {

                    matrix[i][j] -= (temp * vector[i - k]);

                }

            }


            vectorOfDiagonalMatrixElements[k] = matrix[k][k];

            for (int i = k; i < n; i++) {
                matrix[i][k] = vector[i - k];
            }


        }

        //обращение верхне-треугольной матрицы

        double elem = 0;
        for (int j = 0; j < n - 1; j++) {
            elem = 0;

            elem = matrix[j][j];
            matrix[j][j] = vectorOfDiagonalMatrixElements[j];
            vectorOfDiagonalMatrixElements[j] = elem;

        }



        for (int k = 0; k < n; k++) {

            matrix[k][k] = 1 / matrix[k][k];

        }


        for (int i = n - 1; i >= 0; i--) {

            vector = new double[n - i];
            for (int j = 0; j < n - i; j++) {
                vector[j] = matrix[i][j + i];
            }


            for (int j = i + 1; j < n; j++) {

                double sum = 0;
                for (int k = i + 1; k <= j; k++) {
                    sum += (vector[k - i] * matrix[k][j]);
                }
                matrix[i][j] = -vector[0] * sum;

            }

        }

        //необходимо сделать преобразования

        double w[];
        for (int k = n - 2; k >= 0; k--) {

            w = new double[n - k];

            w[0] = vectorOfDiagonalMatrixElements[k];
            for (int i = k + 1; i < n; i++) {
                w[i - k] = matrix[i][k];

                matrix[i][k] = 0;

            }



            double temp = 0;
            for (int i = 0; i < n; i++) {

                temp = 0;

                for (int t = k; t < n; t++) {

                    temp += (matrix[i][t] * w[t - k]);

                }

                temp += temp;

                for (int j = k; j < n; j++) {

                    matrix[i][j] -= (w[j - k] * temp);

                }

            }

        }


        for (int i = n - 2; i >= 0; i--) {


            if (vectorOfReplacement[i] != 0) {


                double temp = 0;
                nReplacement = vectorOfReplacement[i];
                vectorOfReplacement[i] = 0;

                for (int j = 0; j < n; j++) {

                    temp = matrix[nReplacement][j];
                    matrix[nReplacement][j] = matrix[i][j];
                    matrix[i][j] = temp;

                }


            }

            /*printMatrix(matrix);
            System.out.println();*/

        }



        printMatrix(matrix);
        System.out.println();

        return matrix;
    }

    public double getSquareOfVectorElements(final double[] vector) {
        int n = vector.length;

        double result = 0;
        for (int j = 1; j < n; j++) {
            result += (vector[j] * vector[j]);
        }
        return result;
    }

    public double getNormOfVector(final double element, final double s) {
        return Math.sqrt(element * element + s);
    }

    public double[] getVector(final double matrix[][], final int k) {
        int n = matrix.length;
        double vector[] = new double[n - k];

        for (int j = k; j < n; j++) {
            vector[j - k] = matrix[j][k];
        }
        return vector;
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
