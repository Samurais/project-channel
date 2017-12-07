package com.wph.kmeans;

import java.util.Date;

public class Rfm {
	
	private double r;
	private double f;
	private double m;
	
	private static double maxR ;
	private static double maxF ;
	private static double maxM ;
	
	private static double minR;
	private static double minF;
	private static double minM;
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}
	public double getF() {
		return f;
	}
	public void setF(double f) {
		this.f = f;
	}
	public double getM() {
		return m;
	}
	public void setM(double m) {
		this.m = m;
	}
	public static double getMaxR() {
		return maxR;
	}
	public static void setMaxR(double maxR) {
		Rfm.maxR = maxR;
	}
	public static double getMaxF() {
		return maxF;
	}
	public static void setMaxF(double maxF) {
		Rfm.maxF = maxF;
	}
	public static double getMaxM() {
		return maxM;
	}
	public static void setMaxM(double maxM) {
		Rfm.maxM = maxM;
	}
	public static double getMinR() {
		return minR;
	}
	public static void setMinR(double minR) {
		Rfm.minR = minR;
	}
	public static double getMinF() {
		return minF;
	}
	public static void setMinF(double minF) {
		Rfm.minF = minF;
	}
	public static double getMinM() {
		return minM;
	}
	public static void setMinM(double minM) {
		Rfm.minM = minM;
	}
	@Override
	public String toString() {
		return "Rfm [r=" + r + ", f=" + f + ", m=" + m + "]";
	}
	
	
	
	
}


