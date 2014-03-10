package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 03.03.14.
 */
public class Matrix {
    private double[][] matr;
    private int quantityOfRows;
    private int quantityOfColumns;

    Matrix() {
        quantityOfRows = quantityOfColumns = 0;
    }

    Matrix(final int size) {
        if (size < 0){
            System.out.println("Incorrent parametr");
        } //throw(s)

        quantityOfRows = quantityOfColumns = size;

        matr = new double[quantityOfRows][quantityOfColumns];
    }

    Matrix (final int quantityRow, final int quantityColumn) {
        if (quantityColumn < 0){
            System.out.println("Incorrent parametr");
        } //throw(s)

        if (quantityRow < 0){
            System.out.println("Incorrent parametr");
        } //throw(s)

        quantityOfRows = quantityRow;
        quantityOfColumns = quantityColumn;

        matr = new double[quantityOfRows][quantityOfColumns];
    }

    Matrix( Matrix matrix) {
        quantityOfRows = matrix.quantityOfRows;
        quantityOfColumns = matrix.quantityOfColumns;

        matr = new double[quantityOfRows][quantityOfColumns];

        for (int i = 0; i < quantityOfRows; i++) {
            for (int j = 0; j < quantityOfColumns; j++){
                matr[i][j] = matrix.matr[i][j];
            }
        }
    }

    public void setElem(final int row, final int column, final double element) {
        if (row < 0 || row >= quantityOfRows) {
            System.out.println("Incorrent parametr of row");
        }
        if (column < 0 || column >= quantityOfColumns) {
            System.out.println("Incorrent parametr of column");
        }

        matr[row][column] = element;

    }

    public double getElem(final int row, final int column) {
        if (row < 0 || row >= quantityOfRows) {
            System.out.println("Incorrent parametr of row");
        }
        if (column < 0 || column >= quantityOfColumns) {
            System.out.println("Incorrent parametr of column");
        }

        return matr[row][column];
    }

    public Matrix addtition(final Matrix matrix) {
        if (quantityOfRows != matrix.quantityOfRows || quantityOfColumns != matrix.quantityOfColumns){
            System.out.println("The parametrs is not equal!");
        }

        Matrix newMatr = new Matrix(quantityOfRows, quantityOfColumns);

        for (int i = 0; i < quantityOfRows; i++) {
            for (int j = 0; j < quantityOfColumns; j++){
                newMatr.matr[i][j] = matr[i][j] + matrix.matr[i][j];
            }
        }

        return newMatr;
    }

    public Matrix subtraction(final Matrix matrix) {
        if (quantityOfRows != matrix.quantityOfRows || quantityOfColumns != matrix.quantityOfColumns){
            System.out.println("The parametrs is not equal!");
        }

        Matrix newMatr = new Matrix(quantityOfRows, quantityOfColumns);

        for (int i = 0; i < quantityOfRows; i++) {
            for (int j = 0; j < quantityOfColumns; j++){
                newMatr.matr[i][j] = matr[i][j] - matrix.matr[i][j];
            }
        }

        return newMatr;
    }

    public Matrix multiplication(final Matrix matrix) {
        if (quantityOfColumns != matrix.quantityOfRows) {
            System.out.println("The parametrs is not equal!");
        }

        Matrix newMatr = new Matrix(quantityOfRows, quantityOfColumns);

        for (int i = 0; i < quantityOfRows; i++){
            for (int j = 0; j < matrix.quantityOfColumns; j++){

                for (int k = 0; k < quantityOfColumns; k++){
                    newMatr.matr[i][j] += ( matr[i][k] * matrix.matr[k][j] );
                }

            }
        }

        return newMatr;
    }

    public void transposition() {
        for (int i = 0; i < quantityOfRows; i++){
            for (int j = i + 1; j < quantityOfColumns; j++){
                swapElement(i, j);
            }
        }
    }

    private void swapElement(final int i, final int j) {
        double temp;

        temp = matr[i][j];
        matr[i][j] = matr[j][i];
        matr[j][i] = temp;
    }

    public void print() {
        for (int i = 0; i < quantityOfRows; i++) {
            for (int j = 0; j < quantityOfColumns; j++){
               System.out.print(matr[i][j] + " ");
            }
            System.out.println();
        }
    }




}
