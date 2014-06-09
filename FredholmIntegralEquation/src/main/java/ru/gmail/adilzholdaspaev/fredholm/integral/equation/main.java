package ru.gmail.adilzholdaspaev.fredholm.integral.equation;

/**
 * Created by Адиль on 06.05.14.
 */
public class main {

    public static void main2(String[] args) {

        int n = 100;
        double eps = 1e-8;
        //double q = Q;



        //выделение памяти под матрицу
        double[][] A = new double[n][n];


        //выделяем память под обратную матрицу
        double[][] AInverse = new double[n][n];


        double alpha = 10000.0;
        double beta = 1.0;

        Gen myGen = new Gen();

        myGen.mygen(A, AInverse, n, alpha, beta, 1, 2, 0, 1); // симметричная
        //myGen.mygen(A, AInverse, n, alpha, beta, 1, 2, 1, 1); //проостой структуры
        //myGen.mygen ( A, AInverse, n, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка

        double[][] Acopy = new double[n][n];   //копия матрицы

        double[][] Acopy2 = new double[n][n];   //копия матрицы

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                Acopy[i][j] = A[i][j];
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                Acopy2[i][j] = A[i][j];
        }


        double[] f = new double[n];    //правая часть

        double[] f2 = new double[n];   //правая часть копия

        double[] x = new double[n];
        double[] xS = new double[n];        //точный вектор

        for (int i = 0; i < n; i++)
            xS[i] = (double)(i);


        GaussSeidelMethod gsm = new GaussSeidelMethod();

        f = FindRes(xS, A, n);
        //FindRes(xS, A, n, f);

        for (int i = 0; i < n; i++)
            f2[i] = f[i];

        System.out.println();
    /*cout<<"Правая часть"<<endl;
	for (int i=0; i<n; i++)
	cout<<f[i]<<endl;*/

       /* double[] solution = new double[n];    //приближенный вектор
        for (int i = 0; i < n; i++)
            solution[i] = f[i];*/

        double norm = gsm.getNormVector(f);
        System.out.println("Норма правой части : " + norm);
       /* cout << "Норма правой части" << endl;
        cout << norm << endl;*/


	/*cout<<"Приближенный вектор"<<endl;
	for (int i=0; i<n; i++)
	{
	cout<<solution[i]<<" ";
	}*/
	/*double z = NormaVectora(xS,n);
	cout<<z<< " ";*/

        double[] solution = gsm.getSolution(A, f, eps);

        //Solve(A, f, solution, n, col, eps);//,q);

        //cout << endl;
        //cout << "---------------------------------------" << endl;
        System.out.println();
        System.out.println("---------------------------------------");

        double matrnorm = gsm.getNormMatrix(Acopy);
        System.out.println("Норма матрицы: " + matrnorm);


        /*cout << "Норма матрицы:" << endl;
        cout << matrnorm << endl;*/


        double matrnormobr = gsm.getNormMatrix(AInverse);
        System.out.println("Норма обратной матрицы: " + matrnormobr);
        /*cout << "Норма обратной матрицы:" << endl;
        cout << matrnormobr << endl;*/

        double nu;
        nu = matrnorm*matrnormobr;
        System.out.println("Ню: " + nu);
        /*cout << "Ню: " << endl;
        cout << nu << endl;*/

        double err = FindErr(xS, solution, n);
        System.out.println("Ошибка: " + err);
        /*cout << "Ошибка: " << endl;
        cout << err << endl;*/

        double nev = FindNev(Acopy2, solution, f2, n);
        System.out.println("Невязка: " + nev);
        /*cout << "Невязка: " << endl;
        cout << nev << endl;*/

        double ro = nev / norm;
        System.out.println("Относительная невязка: " + ro);
        /*cout << "Относительная невязка ro" << endl;
        cout << ro << endl;*/


        double qq = gsm.getNormVector(solution);//(xS);
        double ksi = err / qq;
        System.out.println("Относительная ошибка: " + ksi);
        /*cout << "Относительная ошибка ksi" << endl;
        cout << ksi << endl;*/

        System.out.println("Число итераций: " + gsm.getQuantityIterations());
        //system("pause");

    }

    public static double[] FindRes(double[] x, double[][] A, int n)     //находим правую часть
    {

        double[] f = new double[n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                f[i] += A[i][j] * x[j];
        }

        return f;

    }

    public static  double FindErr(double[] x, double[] solution, int n)
    {
        double[] z = new double[n];

        GaussSeidelMethod g = new GaussSeidelMethod();

        for (int i = 0; i < n; i++)
        {
            z[i] = x[i] - solution[i];
        }
        double k = g.getNormVector(z);
        return k;
    }


    static double  FindNev(double[][] Acopy2, double[] solution, double[] f2, int n)
    {
        double[] r = new double[n];

        double[] w = new double[n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                w[i] += Acopy2[i][j] * solution[j];
            r[i] = w[i] - f2[i];
        }
        GaussSeidelMethod g = new GaussSeidelMethod();

        return g.getNormVector(r);
    }

    public static void main(String[] args) {

        GaussSeidelMethod M = new GaussSeidelMethod();

        int n = 10;

        /*double[][] A = new double[n][n];
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
        B[2] = 3;*/

        double a = 0;
        double b = 1;


        double h = (a - b) / n;



        TestFredholm tf = new TestFredholm();

        tf.test();


    }

}
