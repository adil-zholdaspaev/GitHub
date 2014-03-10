package ru.gmail.adilzholdaspaev.reflectionMethod;

/**
 * Created by Адиль on 03.03.14.
 */
public class Main {
    public static void main(String args[]) {

        Matrix m = new Matrix(3, 3);
        Matrix n = new Matrix(3, 3);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                m.setElem(i, j, i * j);
            }
        }

        m.print();

        System.out.println();

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                n.setElem(i, j, (i + j) * (j + 1));
            }
        }
        n.print();


       n.transposition();

        System.out.println();
        n.print();



    }
}
