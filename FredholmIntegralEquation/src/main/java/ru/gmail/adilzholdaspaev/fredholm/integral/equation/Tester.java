package ru.gmail.adilzholdaspaev.fredholm.integral.equation;

import java.util.Random;
import java.lang.Math;
import java.io.Console;

/**
 * Created by Адиль on 11.04.14.
 * Generate different matrix
 */


public class Tester {

    public double[][] Z;                //A - A*
    public double norm_Z;
    public double norm_A;
    public double norm_A_Reverse;
    public double V;                //maxL(A) / min L(A)
    public double[][] Residual;         //невязка
    public double norm_Residual;
    public double[][] A_Reverse_Accurate;
    public double[][] A_Reverse_Approximate;
    public double[][] A;
    public double[][] E;
    public double[][] T;
    public double[][] T_Reverse;
    public double[][] J;
    public final int N = 100;
    public boolean flag = true;   // простая структура или с жорд клеткой
    //true - Jordan
    public double numberOfConditionality;
    public double relativeNormZ;

    // T*T^-1 or T*J // result in T
    public void Mult_For_Generate_A(double[][] T, double[][] T_Reverse) {

        double[] x = new double[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                x[j] = 0;
                for (int k = (i == 0) ? 0 : i - 1; k < ((i == N - 1) ? i + 1 : i + 2); k++)
                    x[j] += T[i][k] * T_Reverse[k][j];
            }
            for (int j = 0; j < N; j++)
                T[i][j] = x[j];
        }
    }

    //result in T
    public double[][] Generate_A_Reverse_Accurate(double[][] J, double[][] T, double[][] T_Reverse) {
        /*if (flag)
            J[0][1] = -(1 / (J[0][0] * J[1][1]));

        InvertibleMatrix_L(J);*/

        //Methods m = new Methods();
        //J = m.reflectionMethodWithReplacement(J);

        Mult_For_Generate_A(T, J);
        Mult_For_Generate_A(T, T_Reverse);
        return T;
    }

    public void InvertibleMatrix_L(double[][] Array) {
        double summ;
        for (int i = 0; i < N; i++)
            for (int j = 0; j <= i; j++) {
                summ = 0;
                for (int k = j; k < i; k++)
                    summ += Array[i][k] * Array[k][j];
                summ -= E[i][j];
                Array[i][j] = -summ / Array[i][i];
            }
    }

    //result in T
    public double[][] Generate_A(double L, double B) {
        T = Generate_T();
        J = Generate_J(L, B);
        T_Reverse = Generate_T_Reverse();
        if (flag) J[0][1] = 1;

        Mult_For_Generate_A(T, J);
        Mult_For_Generate_A(T, T_Reverse);
        return T;
    }

    public double[][] Generate_J(double L, double B) {
        Random rand = new Random();
        double[][] J = new double[N][N];
        double max = 0, min = 0;
        boolean z = false;
        for (int i = 0; i < N; i++) {
            double x = ((double) i/ (N - 1));
            double r = Math.pow(-1.0, rand.nextInt(5));
            J[i][i] = r * ((1 - x) * L + x * B);
            if (!z) {
                max = min = Math.abs(J[i][i]);
                z = true;
            }
            if (Math.abs(J[i][i]) > max) max = Math.abs(J[i][i]);
            else if (Math.abs(J[i][i]) < min) min = Math.abs(J[i][i]);
        }
        V = max / min;
        return J;
    }

    public double[][] Generate_T() {
        double[][] T = new double[N][N];

        T[0][0] = 1;
        T[0][1] = T[N - 1][N - 2] = -1;
        T[N - 1][N - 1] = 2;

        for (int i = 1; i < N - 1; i++) {
            T[i][i - 1] = -1;
            T[i][i] = 2;
            T[i][i + 1] = -1;
        }

        return T;
    }

    public double[][] Generate_T_Reverse() {
        double[][] T_Reverse = new double[N][N];
        int n;
        for (int j = 0; j < N; j++) {
            n = N - j;
            for (int i = j; i < N; i++) {
                T_Reverse[i][j] = n;
                T_Reverse[j][i] = n--;
            }
        }
        return T_Reverse;
    }

    public double[][] Generate_E() {
        E = new double[N][N];
        for (int i = 0; i < N; i++)
            E[i][i] = 1;
        return E;
    }

    // result in A_Reverse_Accurate
    public double[][] Error_Z(double[][] A_Reverse_Accurate, double[][] A_Reverse_Approximate) {
        double[][] M = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                M[i][j] = A_Reverse_Accurate[i][j] - A_Reverse_Approximate[i][j];
        return M;
    }

    public double Norm(double[][] Matrix) {
        double norm = 0;
        for (int i = 0; i < N; i++)
            norm += Matrix[i][i];
        return norm;
    }

    public double[][] Multiplication_Matrix(double[][] M1, double[][] M2) {
        double[][] Result = new double[N][N];
        double summ = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                summ = 0;
                for (int k = 0; k < N; k++)
                    summ += M1[i][k] * M2[k][j];
                Result[i][j] = summ;
            }
        return Result;
    }


    //E-AX0  // result in E
    public double[][] Residual_method(double[][] A_Reverse_Approximate, double[][] A) {
        double[][] Mult = Multiplication_Matrix(A, A_Reverse_Approximate);
        for (int i = 0; i < N; i++)
            E[i][i] = E[i][i] - Mult[i][i];
        return E;

    }

    public void Print_Matrix(double[][] Array) {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(Array[i][j] + "  ");
            System.out.println();
        }
    }

    public double[][] Copy(double[][] Array) {
        double[][] addArray = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                addArray[i][j] = Array[i][j];
        return addArray;
    }

    public double getNormOfMatrix(final double[][] matrix) {

        int n = matrix.length;

        double norm = 0;
        double max = 0;
        for (int i = 0; i < N; i++)
        {
            max = 0;
            for (int j = 0; j < N; j++)
                max += Math.abs(matrix[i][j]);
            if (max > norm) norm = max;
        }
        return norm;

    }

    public void Test() {



        for (int i = 0; i < 15; i++) {

            //Methods m = new Methods();

            E = Generate_E();
            A = Generate_A(Math.pow(10, -(i + 1)), 1);

            double[][] A1 = Copy(A);

            norm_A = getNormOfMatrix(A);
            double[][] T1 = Generate_T();
            A_Reverse_Accurate = Generate_A_Reverse_Accurate(J, T1, T_Reverse);
            norm_A_Reverse = getNormOfMatrix(A_Reverse_Accurate);

            //A_Reverse_Approximate = m.reflectionMethodWithReplacement(A1);
            norm_Z = getNormOfMatrix(Error_Z(A_Reverse_Accurate, A_Reverse_Approximate));
            norm_Residual = getNormOfMatrix(Residual_method(A_Reverse_Approximate, A));

            numberOfConditionality = norm_A * norm_A_Reverse;
            relativeNormZ = norm_Z / norm_A_Reverse;


        /*Print_Matrix(A_Reverse_Accurate);
        System.out.println();

        Print_Matrix(A_Reverse_Approximate);
        System.out.println();*/

            System.out.println(norm_A + "  " + norm_A_Reverse + "  " + numberOfConditionality + "  " + norm_Z + "  " + relativeNormZ +
                    "  " + norm_Residual);

        }

    }
}

