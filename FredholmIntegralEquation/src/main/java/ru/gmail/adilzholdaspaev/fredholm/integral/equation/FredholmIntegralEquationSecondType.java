package ru.gmail.adilzholdaspaev.fredholm.integral.equation;

import sun.net.www.content.audio.x_wav;

/**
 * Created by Адиль on 12.05.14.
 */

public class FredholmIntegralEquationSecondType {

    private double lambda;
    private double a;
    private double b;
    private double epsilon;

    private double h;

    private double hk;

    FredholmIntegralEquationSecondType(final double lambdaInput, final double aInput, final double bInput,
                                       final double epsilonInput, final int n) {

        lambda = lambdaInput;
        a = aInput;
        b = bInput;
        epsilon = epsilonInput;

        h = Math.abs(b - a) / n;

        hk = lambda * Math.abs(b - a) / n;

    }


    public double[] decideIteration(final int n, final double x) {

        double[][] A = new double[n + 1][n + 1];

        double[] f = new double[n + 1];

        for (int i = 0; i <= n; i++) {
            f[i] = getFunctionValue(getArgumentValue(i));
        }

        for (int i = 0; i < n + 1; i++) {

            for (int j = 0; j < n + 1; j++) {

                double c = 1;
                    /*if( j % 2 == 0 ) c = 2;
                    if( (j == 0) || (j == n) )  c = 1;*/

                if ((j == 0) || (j == n)) {
                    c /= 2;
                }

                A[i][j] = -hk * getKerValue(getArgumentValue(i), getArgumentValue(j)) * c;

                if (i == j) {
                    A[i][j]++;
                }

            }

        }

        GaussSeidelMethod gsm = new GaussSeidelMethod();

        double[] solution = gsm.getSolution(A, f, epsilon);


        System.out.println("Кол-во итераций: " + gsm.getQuantityIterations());

        return solution;

    }


    public double getKerValue(final double x, final double t) {
        return (x - 1);
    }

    public double getArgumentValue(final double index) {
        return a + index * h;
    }

    public double getSolutionValue(final double x, final double lambda) {
        double temp = ( lambda / (2 + lambda) ) * ( x - 1 ) + x;//1 + 2 * Math.sin(x);//Math.exp(-x) + x;//(Math.sin(Math.PI * x) + 2 / Math.PI);
        return temp;
    }

    public double getFunctionValue(final double x) {
        return x;
    }

    public double[] getVectorFunctionValue(final double[] y, final int n) {

        double[][] A = new double[n][n];
        double[] f = new double[n];

        h = (a - b) / n;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                A[i][j] = -h * lambda * getKerValue(getArgumentValue(i), getArgumentValue(j));

                if (i == j) {
                    A[i][j]++;
                }

                if ((j == 0) || (j == (n - 1))) {
                    A[i][j] /= 2;
                }

                if (i == j) {
                    A[i][j]++;
                }

            }

        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                f[i] += A[i][j] * y[j];

            }

        }

        return f;

    }


}
