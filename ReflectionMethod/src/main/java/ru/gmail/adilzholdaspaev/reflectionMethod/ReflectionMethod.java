package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 10.03.14.
 */
public class ReflectionMethod {
    private Matrix matrix;
    private Matrix inverseMatrix;

    private Matrix solution;

    ReflectionMethod(final Matrix matr, final Matrix inverseMatr) {
        matrix = matr;
        inverseMatrix = inverseMatr;
    }

    ReflectionMethod( ReflectionMethod object) {
        matrix = object.matrix;
        inverseMatrix = object.inverseMatrix;
        solution = object.solution;
    }

   /* public Matrix misalignment() {
        Matrix solution;

        return solution;
    }*/


}
