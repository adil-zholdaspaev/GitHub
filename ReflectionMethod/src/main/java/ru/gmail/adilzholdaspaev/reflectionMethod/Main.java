package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 03.03.14.
 */
public class Main {

    public static void main(String args[]) {

        double matrix[][] = new double[4][4];

        matrix[0][0] = 3;
        matrix[0][1] = 2;
        matrix[0][2] = 1;
        matrix[0][3] = 0.153165;
        matrix[1][0] = 2;
        matrix[1][1] = 2;
        matrix[1][2] = 1;
        matrix[1][3] = 8;
        matrix[2][0] = 1;
        matrix[2][1] = 134;
        matrix[2][2] = 0.2165461;
        matrix[2][3] = 30.165486;
        matrix[3][0] = 1;
        matrix[3][1] = 9;
        matrix[3][2] = 1;
        matrix[3][3] = 5;



        Methods example = new Methods();
        double[][] matr = example.reflectionMethod(matrix);

        //System.out.println();
        double[][] matr3 = example.reflectionMethodWithReplacement(matrix);

       /* Tester test = new Tester();
        test.Test();*/



    }

}
