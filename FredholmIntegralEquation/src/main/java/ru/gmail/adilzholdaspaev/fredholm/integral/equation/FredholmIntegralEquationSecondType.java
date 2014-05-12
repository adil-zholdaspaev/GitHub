package ru.gmail.adilzholdaspaev.fredholm.integral.equation;

/**
 * Created by Адиль on 12.05.14.
 */

public class FredholmIntegralEquationSecondType {

    private final double lambda = 1;
    private final double a = 0;
    private final double b = 1;
    private final double epsilon = 1e-2;

    private double h;

    public void decide() {

        for (int n = 50; n <= 500; n += 50) {

            double[][] A = new double[n + 1][n + 1];
            double[] f = new double[n + 1];

            h = (a - b) / n;

            for (int i = 0; i <= n; i++) {
                f[i] = getFunctionValue();
            }

            for (int i = 0; i <= n; i++) {

                for (int j = 0; j <= n; j++) {

                    A[i][j] = -h * lambda * getKerValue(getArgumentValue(i), getArgumentValue(j));

                    if ( ( j == 0 ) || ( j == n ) ) {
                        A[i][j] /= 2;
                    }

                    if (i == j) {
                        A[i][j]++;
                    }

                }

            }

            GaussSeidelMethod gsm = new GaussSeidelMethod();

            double[] solution = gsm.getSolution(A, f, epsilon);

            for (int i = 0; i < n; i++) {
                System.out.print(solution[i] + " ");
            }
            System.out.println();

        }

    }

    private double getFunctionValue() {
        return 1;
    }

    private double getKerValue(final double xi, final double xj) {
        return xi * xj;
    }

    private double getArgumentValue(final double index) {
        return a + index * h;
    }

}
