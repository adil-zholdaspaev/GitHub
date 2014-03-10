package ru.gmail.adilzholdaspaev.reflectionMethod;

import java.lang.Math;

/**
 * Created by Адиль on 03.03.14.
 */


public class Matrix {
    private double[][] data;

    private int rowsNumber;
    private int columnsNumber;

    Matrix() {
        rowsNumber = columnsNumber = 0;
    }

    Matrix(final int dimension) {
        if (dimension < 0) {
            System.out.println("Incorrent parametr");
        } //throw(s)

        rowsNumber = columnsNumber = dimension;

        data = new double[rowsNumber][columnsNumber];
    }

    Matrix(final int rows, final int columns) {
        if (columns < 0) {
            System.out.println("Incorrent parametr");
        } //throw(s)

        if (rows < 0) {
            System.out.println("Incorrent parametr");
        } //throw(s)

        rowsNumber = rows;
        columnsNumber = columns;

        data = new double[rowsNumber][columnsNumber];
    }

    Matrix(Matrix matrix) {
        rowsNumber = matrix.rowsNumber;
        columnsNumber = matrix.columnsNumber;

        data = new double[rowsNumber][columnsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                data[i][j] = matrix.data[i][j];
            }
        }
    }

    public void setElem(final int row, final int column, final double element) {
        if (row < 0 || row >= rowsNumber) {
            System.out.println("Incorrent parametr of row");
        }
        if (column < 0 || column >= columnsNumber) {
            System.out.println("Incorrent parametr of column");
        }

        data[row][column] = element;

    }

    public double getElem(final int row, final int column) {
        if (row < 0 || row >= rowsNumber) {
            System.out.println("Incorrent parametr of row");
        }
        if (column < 0 || column >= columnsNumber) {
            System.out.println("Incorrent parametr of column");
        }

        return data[row][column];
    }

    public Matrix addtition(final Matrix matrix) {
        if (rowsNumber != matrix.rowsNumber || columnsNumber != matrix.columnsNumber) {
            System.out.println("The parametrs is not equal!");
        }

        Matrix newMatr = new Matrix(rowsNumber, columnsNumber);

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                newMatr.data[i][j] = data[i][j] + matrix.data[i][j];
            }
        }

        return newMatr;
    }

    public Matrix subtraction(final Matrix matrix) {
        if (rowsNumber != matrix.rowsNumber || columnsNumber != matrix.columnsNumber) {
            System.out.println("The parametrs is not equal!");
        }

        Matrix newMatr = new Matrix(rowsNumber, columnsNumber);

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                newMatr.data[i][j] = data[i][j] - matrix.data[i][j];
            }
        }

        return newMatr;
    }

    public Matrix multiplication(final Matrix matrix) {
        if (columnsNumber != matrix.rowsNumber) {
            System.out.println("The parametrs is not equal!");
        }

        Matrix newMatr = new Matrix(rowsNumber, matrix.columnsNumber);

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < matrix.columnsNumber; j++) {

                for (int k = 0; k < columnsNumber; k++) {
                    newMatr.data[i][j] += (data[i][k] * matrix.data[k][j]);
                }
            }
        }

        return newMatr;
    }

    public Matrix multiplyToNumber(final double number) {
        Matrix matrix = new Matrix(rowsNumber, columnsNumber);

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                matrix.data[i][j] = number * data[i][j];
            }
        }

        return matrix;
    }

    public Matrix transposition() {
        Matrix matr = new Matrix(columnsNumber, rowsNumber);

        double temp;
        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                matr.data[j][i] = data[i][j];
            }
        }

        return matr;
    }

    public double norma() {
        double result = 1e-10;

        for (int i = 0; i < rowsNumber; i++) {

            double temp = 0;
            for (int j = 0; j < columnsNumber; j++) {
                temp += Math.abs(data[i][j]);
            }

            if (temp > result) {
                result = temp;
            }

        }

        return result;
    }

    public Matrix getVector(final int j) {
        if (j < 0 || j >= columnsNumber) {
            System.out.println("Incorrect parametr");
        }

        Matrix vector = new Matrix(rowsNumber, 1);

        for (int i = 0; i < rowsNumber; i++) {
            vector.data[i][j] = data[i][j];
        }

        return vector;
    }

    public void print() {
        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }


}
