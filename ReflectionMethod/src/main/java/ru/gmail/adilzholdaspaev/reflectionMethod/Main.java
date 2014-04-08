package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 03.03.14.
 */
public class Main {

    public static void main(String args[]) {

        double matrix[][] = new double[3][3];

        matrix[0][0] = 3.;
        matrix[0][1] = 2.;
        matrix[0][2] = 1.;
        matrix[1][0] = 2.;
        matrix[1][1] = 2.;
        matrix[1][2] = 1.;
        matrix[2][0] = 1.;
        matrix[2][1] = 1.;
        matrix[2][2] = 1.0;

        /*double matrix[][] = new double[4][4];

        matrix[0][0] = 1;
        matrix[0][1] = 4;
        matrix[0][2] = 3;
        matrix[1][0] = 8;
        matrix[1][1] = 5;
        matrix[1][2] = 1;
        matrix[1][3] = 9;
        matrix[2][0] = 15;
        matrix[2][1] = 2;
        matrix[2][2] = 9;
        matrix[2][3] = 1;
        matrix[3][0] = 6;
        matrix[3][1] = 7;
        matrix[3][2] = 3;
        matrix[3][3] = 7;*/

        Main example = new Main();
        double[][] matr = example.reflectionMethod(matrix);

        /*Main ex = new Main();
        ex.multiple();*/

    }

    public void multiple() {
        int n = 3;

        double u[][] = new double[n][n];
        double x[][] = new double[n][n];

        u[0][0] = -0.80177144;
        u[0][1] = -0.534518;
        u[0][2] = -0.26726;
        u[1][0] = -0.534518;
        u[1][1] = 0.8571454;
        u[1][2] = -0.077926;
        u[2][0] = -0.26726;
        u[2][1] = -0.077926;
        u[2][2] = 0.960358;

        x[0][0] = 3;
        x[0][1] = 2;
        x[0][2] = 1;
        x[1][0] = 2;
        x[1][1] = 2;
        x[1][2] = 1;
        x[2][0] = 1;
        x[2][1] = 1;
        x[2][2] = 1;



        double res[][] = new double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                for (int k = 0; k < n; k++) {

                    res[i][j] += ( u[i][k] * x[k][j] );

                }

            }

        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print( res[i][j] + " " );

            }

            System.out.println();

        }


    }


    public double[][] reflectionMethod(double [][] matrix) {
        int n = matrix[0].length;

       // double vector[] = getVector(matrix, 0);

        double vectorOfDiagonalMatrixElements[] = new double[n];

        for (int k = 0; k < n - 1; k++) {
            double vector[] = getVector(matrix, k);

            double s = getSquareOfVectorElements(vector);
            double normA = getNormOfVector(vector[0], s);

            if (vector[0] <= 0) {

                vector[0] -= normA;
            } else {
                vector[0] += normA;
            }
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
                        tempVector[p - k] += vectorOfProduct[i - k] * matrix[i][j];
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

        //обращение верхне-треугольной матрицы

        for ( int j = 0; j < n - 1; j++ ) {
            double elem = 0;

            elem = matrix[j][j];
            matrix[j][j] = vectorOfDiagonalMatrixElements[j];
            vectorOfDiagonalMatrixElements[j] = elem;

        }

        printMatrix(matrix);
        System.out.println();

        for (int k = 0; k < n; k++) {

            matrix[k][k] = 1 / matrix[k][k];

        }


        for ( int i = n - 1; i >= 0; i-- ) {

            double vector[] = new double[n - i];
            for ( int j = 0; j < n - i; j++ ) {
                vector[j] = matrix[i][j + i];
            }


            for ( int j = i + 1; j < n; j++ ) {

                double sum = 0;
                for ( int k = i + 1; k <= j; k++) {
                    sum += ( vector[k - i] * matrix[k][j] );
                }
                matrix[i][j] =  -vector[0] * sum;


            }

        }



        printMatrix(matrix);
        System.out.println();


        //необходимо сделать преобразования



        for (int j = n - 2; j >= 0; j--) {

            double vectorOfTransformation[] = new double[ n - j ];
            vectorOfTransformation[0] = vectorOfDiagonalMatrixElements[j];
            for (int i = 1; i < n - j; i++) {

                vectorOfTransformation[i] = matrix[i + j][j];
                matrix[i + j][j] = 0;
            }

            for (int i = 0; i < n; i++) {

                double elem = 0;

                for (int p = 0; p < n - j; p++) {

                    elem += ( matrix[i][j + p] * vectorOfTransformation[p] );

                }

                matrix[i][j] = elem;

            }

        }


        printMatrix(matrix);
        System.out.println();

        return matrix;
    }


    public double[][] getMatr(final double[][] matrix) {

        int n = matrix.length;

        printMatrix(matrix);
        System.out.println();

        for (int k = 0; k < n; k++) {

            matrix[k][k] = 1 / matrix[k][k];

        }


        for ( int i = n - 1; i >= 0; i-- ) {

            double vector[] = new double[n - i];
            for ( int j = 0; j < n - i; j++ ) {
                vector[j] = matrix[i][j + i];
            }


            for ( int j = i + 1; j < n; j++ ) {

                double sum = 0;
                for ( int k = i + 1; k <= j; k++) {
                    sum += ( vector[k - i] * matrix[k][j] );
                }
                matrix[i][j] =  -vector[0] * sum;


            }
        }

        printMatrix(matrix);
        System.out.println();

        return matrix;
    }

    public double getSquareOfVectorElements(final double [] vector) {
        int n = vector.length;

        double result = 0;
        for (int j = 1; j < n; j++) {
            result += (vector[j] * vector[j]);
        }
        return result;
    }

    public double getNormOfVector(final double element, final double s) {
        return Math.sqrt( element * element + s );
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

    public double[][] getResidual(final double[][] matrix, final double[][] approximateMatrix) {

        int n = matrix.length;
        double result[][] = new double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                for (int k = 0; k < n; k++) {

                    result[i][j] += ( matrix[i][k] * approximateMatrix[k][j] );

                }

            }

        }

        for (int i = 0; i < n; i++) {

            result[i][i] -= 1;

        }

        return result;

    }

    public double[][] getError(final double[][] matrix, final double[][] inverseMatrix) {

        int n = matrix.length;
        double result[][] = new double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[i][j] - inverseMatrix[i][j];
            }
        }

        return result;
    }

    public double getNormOfMatrix(final double[][] matrix) {

        int n = matrix.length;

        double result = 0;
        double temp = 0;

        for (int i = 0; i < n; i++) {
            temp = 0;

            for (int j = 0; j < n; j++) {
                temp += Math.abs(matrix[i][j]);
            }

            result = Math.max(result, temp);
        }
        return result;
    }


}
