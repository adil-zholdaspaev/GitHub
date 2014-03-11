package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 03.03.14.
 */
public class Main {
    public static void main(String args[]) {

        Matrix m = new Matrix(3, 3);
        m.setElem(0, 0, 1);
        m.setElem(0, 1, 4);
        m.setElem(0, 2, 3);

        m.setElem(1, 0, 8);
        m.setElem(1, 1, 0);
        m.setElem(1, 2, 1);

        m.setElem(2, 0, 1);
        m.setElem(2, 1, 2);
        m.setElem(2, 2, 1);

        m.print();

        Matrix a = new Matrix(3, 1);
        a.setElem(0, 0, 1);
        a.setElem(1, 0, 4);
        a.setElem(2, 0, 3);

        a.print();

        Matrix b = new Matrix(1, 3);
        b.setElem(0, 0, 2);
        b.setElem(0, 1, 3);
        b.setElem(0, 2, 6);

        b.print();

        Matrix res = a.multiplication(b);

        res.print();

        System.out.println();

        ReflectionMethod reflection = new ReflectionMethod(m, m);

        Matrix matr =  reflection.getUpperTriangularMatrix();

        matr.print();
    }
}
