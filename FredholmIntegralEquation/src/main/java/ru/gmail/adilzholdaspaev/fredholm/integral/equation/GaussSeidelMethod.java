package ru.gmail.adilzholdaspaev.fredholm.integral.equation;

/**
 * Created by Адиль on 06.05.14.
 */
public class GaussSeidelMethod {

    double[] getSolution(double[][] A, double[] B, double epsilon) {
        int n = B.length;

        double[] xSolution = new double[n];

        double[][] alphaMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                alphaMatrix[i][j] = (i != j) ? -A[i][j] / A[i][i] : 0;

            }

        }

        double normAlpha = getNormMatrix(alphaMatrix);

        epsilon *= (1 - normAlpha) / normAlpha;

        double[] xOldSolution = new double[n];

        for (int k = 0; ; k++) {

            for (int i = 0; i < n; i++) {

                xOldSolution[i] = xSolution[i];

                double temp = 0;

                for (int j = 0; j < n; j++) {

                    temp += (i != j) ? ( A[i][j] * xSolution[j] ) : 0;

                }

                xSolution[i] = (B[i] - temp) / A[i][i];

            }

            for (int i = 0; i < n; i++) {
                System.out.print(xSolution[i] + " ");
            }
            System.out.println();


            if ( ( getNormVector(getVectorResidual(xSolution, xOldSolution)) - epsilon ) < 0 ) {

                return xSolution;

            }

        }

    }

    double getNormMatrix(final double[][] matrix) {

        int n = matrix.length;

        double result = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                result += Math.pow(matrix[i][j], 2);

            }

        }

        result = Math.sqrt(result);

        return result;

    }

    double getNormVector(double[] vector) {

        int n = vector.length;
        double result = 0;

        for (int i = 0; i < n; i++) {

            result += Math.pow(vector[i], 2);

        }

        result = Math.sqrt(result);

        return result;

    }

    double[] getVectorResidual(double[] vector1, double[] vector2) {

        int n = vector1.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {

            result[i] = vector1[i] - vector2[i];

        }

        return result;

    }

}
