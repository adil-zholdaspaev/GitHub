package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 03.03.14.
 */
public class Matrix {
    private double[][] matr;

    Matrix() {
        matr = new double[5][5];
    }

    Matrix(final int size) {
        if (size < 0){
            System.out.println("Incorrent parametr");
        } //throw(s)

        matr = new double[size][size];
    }

    Matrix( Matrix matrix) {
        int size = matrix.matr.length;

        matr = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                matr[i][j] = matrix.matr[i][j];
            }
        }
    }

    public void setElem(final int row, final int column, final double element) {
        int size = matr.length;

        if (row <= 0 || row > size) {
            System.out.println("Incorrent parametr of row");
        }
        if (column <= 0 || column > size) {
            System.out.println("Incorrent parametr of column");
        }

        matr[row][column] = element;

    }

    public double getElem(final int row, final int column) {
        int size = matr.length;

        if (row <= 0 || row > size) {
            System.out.println("Incorrent parametr of row");
        }
        if (column <= 0 || column > size) {
            System.out.println("Incorrent parametr of column");
        }
        return matr[row][column];
    }



}
