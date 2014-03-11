package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 10.03.14.
 */
public class ReflectionMethod {
    private Matrix matrix;
    private Matrix inverseMatrix;

    private Matrix solution;

    ReflectionMethod( final Matrix matr, final Matrix inverseMatr ) {
        matrix = matr;
        inverseMatrix = inverseMatr;
    }

    ReflectionMethod( ReflectionMethod object ) {
        matrix = object.matrix;
        inverseMatrix = object.inverseMatrix;
        solution = object.solution;
    }

    /* public Matrix getInverseMatrix() {


    }*/

    public Matrix getUpperTriangularMatrix() {
        int dimension = matrix.getColumnsNumber();

        Matrix upperTriangularMatrix = new Matrix(dimension);

        for (int k = 0; k < dimension; k++) {

            Matrix vector = matrix.getVector(k);

            vector.print();
            System.out.println();


            double s = 0;
            for (int j = k + 1; j < dimension; j++) {
                s += Math.pow(vector.getElem(j, 0), 2);
            }



            double norma1 = Math.sqrt( Math.pow(vector.getElem(k, 0), 2) + s );

            Matrix vec = new Matrix(dimension - k, 1);
            vec.setElem(0, 0, vector.getElem(k, 0) - norma1);

            for (int j = 1; j < dimension - k; j++) {
                vec.setElem(j, 0, vector.getElem(j, 0));
            }
            vec.print();
            System.out.println();


            double norma2 = Math.sqrt( Math.pow( vec.getElem(0, 0), 2) + s );
            vec = vec.multiplyToNumber( 1/norma2 );
            vec.print();
            System.out.println();

            Matrix m = vec.multiplication(vec.transposition());
            m = m.multiplyToNumber(2);

            m.print();
            System.out.println();


            Matrix identity = new Matrix(dimension - k);
            for (int j = 0; j < dimension - k; j++) {
                identity.setElem(j, j, 1);
            }

            identity.print();

            System.out.println();

            upperTriangularMatrix = identity.subtraction(m);

            upperTriangularMatrix.print();
            System.out.println();

            Matrix newm = upperTriangularMatrix.multiplication(matrix);

            newm.print();
            System.out.println();
        }

        return upperTriangularMatrix;

    }


}
