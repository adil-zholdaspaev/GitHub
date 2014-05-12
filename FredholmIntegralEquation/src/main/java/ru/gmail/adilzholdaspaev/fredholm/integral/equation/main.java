package ru.gmail.adilzholdaspaev.fredholm.integral.equation;

/**
 * Created by Адиль on 06.05.14.
 */
public class main {

    public static void main(String[] args) {

        GaussSeidelMethod M = new GaussSeidelMethod();

        int n = 3;

        double[][] A = new double[n][n];
        double[] B = new double[n];

        A[0][0] = 10;
        A[0][1] = 2;
        A[0][2] = 1;

        A[1][0] = 3;
        A[1][1] = 20;
        A[1][2] = 2;

        A[2][0] = 5;
        A[2][1] = 1;
        A[2][2] = 25;


        B[0] = 1;
        B[1] = 2;
        B[2] = 3;

        FredholmIntegralEquationSecondType fi = new FredholmIntegralEquationSecondType();

        fi.decide();

        /*double epsilon = 0.01;

        double[] xSolution = M.getSolution(A, B, epsilon);

        for (int i = 0; i < n; i++) {
            System.out.print(xSolution[i] + " ");
        }
        System.out.println();*/

    }

}
