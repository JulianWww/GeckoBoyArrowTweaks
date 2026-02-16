package net.denanu.geckoboyarrowtweaks.utils;

import com.google.common.base.Function;

public class Newton {
	private static double ERROR = 0.000001;
	private static int STEPS = 50;

	public static double solve(double x, Function<Double, Double> f, Function<Double, Double> df) {
		return solve(x, f, df, ERROR, STEPS);
	}

	public static double solve(double x, Function<Double, Double> f, Function<Double, Double> df, double func_acc, int steps) {
		for (int idx=0; idx<steps; idx++) {
			double value = f.apply(x);
			if (Math.abs(value) < func_acc) {
				return x;
			}
			x = x - value/df.apply(x);
		}
		return Double.MAX_VALUE;
	}
}
