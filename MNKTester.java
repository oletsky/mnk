package mathcomp.oletsky.mnk;

import mathcomp.oletsky.mathhelper.SLAUSolver;
import mathcomp.oletsky.mathhelper.VectMatr;

public class MNKTester {
    static double a=2.;
    static double b=3.;
    static double sigma=0.1;

    public static void main(String[] args) {

        //Points
        double[] xPoints = {0., 0.1, 0.2, 0.3, 0.4,
                0.5, 0.6, 0.7, 0.8, 0.9, 1.};

        int n = xPoints.length;

        //Experimental data

        double[] y = new double[n];

        //Simulation
        for (int i=0; i<n; i++) {
            double x = xPoints[i];
            y[i]=pointSimulate(x,a,b,sigma);
        }

        System.out.println("Simulation:");
        VectMatr.defaultOutputVector(y);

        //Getting parameters
        double[][] fMatr = new double[n][2];
        for (int i = 0; i <n ; i++) {

            fMatr[i][0]=f1(xPoints[i]);
            fMatr[i][1]=f2();

        }

        double[] params= SLAUSolver.solveSLAR(fMatr, y,
                SLAUSolver.SolverType.SVD);

        System.out.println("Restored params:");
        VectMatr.defaultOutputVector(params);

    }

    public static double pointSimulate(
            double x,
            double a,
            double b,
            double sigma
    ) {
        return a*f1(x)+b*f2()+whiteNoise(sigma);
    }

    public static double f1(double x) {
        return x;
    }

    public static double f2() {
        return 1;
    }

    public static double whiteNoise(double sigma) {
        return Math.sqrt(sigma)*(Math.random()-0.5);
    }
}

