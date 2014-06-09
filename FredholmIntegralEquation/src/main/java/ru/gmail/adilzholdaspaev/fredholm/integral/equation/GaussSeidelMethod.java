package ru.gmail.adilzholdaspaev.fredholm.integral.equation;

/**
 * Created by Адиль on 06.05.14.
 */
public class GaussSeidelMethod {
    // проверка на строгое диагональное преобладание
    // действие

    private int nIterations;

    public double[] getSolution(double[][] A, double[] B, double epsilon) {
        int n = B.length;

        double[] xSolution = new double[n];

        double alpha = 0;
        double normAlpha = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                alpha = (i != j) ? (-A[i][j] / A[i][i]) : 0;
                normAlpha += Math.pow(alpha, 2);

            }

        }

        normAlpha = Math.sqrt(normAlpha);

        epsilon *= (1 - normAlpha) / normAlpha;

        epsilon = Math.abs(epsilon);

        double[] xOldSolution = new double[n];

        for (nIterations = 0; nIterations < 100000; nIterations++) {

            for (int i = 0; i < n; i++) {

                xOldSolution[i] = xSolution[i];

                double temp = 0;

                for (int j = 0; j < n; j++) {

                    temp += (i != j) ? ( A[i][j] * xSolution[j] ) : 0;

                }

                xSolution[i] = (B[i] - temp) / A[i][i];

            }

            if (getNormVector( getVectorResidual(xSolution, xOldSolution) ) < epsilon ) {

                return xSolution;

            }

        }

        return xSolution;

    }

    public double getNormMatrix(final double[][] matrix) {

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

    public double getNormVector(double[] vector) {

        int n = vector.length;
        double result = 0;

        for (int i = 0; i < n; i++) {

            result += Math.pow(vector[i], 2);

        }

        result = Math.sqrt(result);

        return result;

    }

    public double[] getVectorResidual(double[] vector1, double[] vector2) {

        int n = vector1.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {

            result[i] = vector1[i] - vector2[i];

        }

        return result;

    }

    public int getQuantityIterations() {

        return nIterations;
    }

}
