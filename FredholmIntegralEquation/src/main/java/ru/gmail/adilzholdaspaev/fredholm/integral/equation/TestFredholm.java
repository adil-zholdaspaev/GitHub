package ru.gmail.adilzholdaspaev.fredholm.integral.equation;

/**
 * Created by Адиль on 03.06.14.
 */
public class TestFredholm {

    public void test() {

        double a = 0;
        double b = 1;

        double lambda = 0.5;
        double epsilon = 1e-5;

        int n = 100;

        FredholmIntegralEquationSecondType fe = new FredholmIntegralEquationSecondType(lambda, a, b, epsilon, n);

        double[] exactSolution = new double[n + 1];

        for (int i = 0; i <= n; i++) {
            exactSolution[i] = fe.getSolutionValue(fe.getArgumentValue(i), lambda);
            System.out.print(exactSolution[i] + " ");
        }
        System.out.println();

        System.out.println();

        double[] solution = fe.decideIteration(n, 1);

        System.out.println();

        double temp = normVector(vectorResidual(exactSolution, solution));

        System.out.println(temp);

        double normApproximate = normVector(solution);

        System.out.println(temp / normApproximate);

    }


    public double normVector(final double[] vector) {

        double result = 0;

        for (int i = 0; i < vector.length; i++) {
            result += Math.pow(vector[i], 2.0);
        }

        result = Math.sqrt(result);

        return result;

    }

    public double[] vectorResidual(final double vectorExact[], final double vectorApproximate[]) {

        int n = vectorExact.length;

        double[] vector = new double[n];

        for (int i = 0; i < n; i++) {

            vector[i] = vectorExact[i] - vectorApproximate[i];

        }

        return vector;
    }

   /* public void test2() {

        double a = 0;
        double b = 1;

        double lambda = 1;
        double epsilon = 1e-9;

        int n = 50;

        FredholmIntegralEquationSecondType fe = new FredholmIntegralEquationSecondType(lambda, a, b, epsilon, n);

        double y[] = new double[n];

        for (int i = 0; i < n; i++) {
            y[i] = fe.getSolutionValue(fe.getArgumentValue(i));

            System.out.print(y[i] + " ");
        }
        System.out.println();
        System.out.println();

        double f[] = fe.getVectorFunctionValue(y, n);

        double solution[] = fe.decideIteration(f, n);

        for (int i = 0; i < n; i++) {
            System.out.print(solution[i] + " ");

        }
        System.out.println();

    }*/

}
