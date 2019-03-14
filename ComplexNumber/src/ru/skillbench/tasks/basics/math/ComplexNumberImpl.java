package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class ComplexNumberImpl implements ComplexNumber {
	private double re;
    private double im;

    public ComplexNumberImpl() {
        re = 0;
        im = 0;
    }
    
    public ComplexNumberImpl(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
	public ComplexNumberImpl clone() throws CloneNotSupportedException {
		ComplexNumberImpl copy = new ComplexNumberImpl();
        copy.set(re, im);
        return copy;
 	}

	public double getRe() {
		return re;
	}

	public double getIm() {
		// TODO Auto-generated method stub
		return im;
	}

	public boolean isReal() {
		if(im == 0){
			return true;
		}
		else{
			return false;
		}
	}

	public void set(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public void set(String value) throws NumberFormatException {
		if(value.split("-")[1].contains("-")){
			this.im = - Double.parseDouble(value.split("-")[1].split("i")[0]);
		}else{
			this.im = - Double.parseDouble(value.split("-")[1].split("i")[0]);
		}
		System.out.println(s[0]+" "+s[1]);
		if (value.isEmpty()) {
            throw new NumberFormatException();
            }
	}

	public ComplexNumber copy() {
		return new ComplexNumberImpl(re, im);
	}

	public int compareTo(ComplexNumber other) {
		Double abs = Math.pow(re, 2) + Math.pow(im, 2);
        return abs.compareTo(Math.pow(other.getIm(), 2) + Math.pow(other.getRe(), 2));
	}

	public void sort(ComplexNumber[] array) {
		// TODO Auto-generated method stub
		Arrays.sort(array);
	}

	public ComplexNumber negate() {
		this.re = -re;
        this.im = -im;
        return this;
	}

	public ComplexNumber add(ComplexNumber arg2) {
		re += arg2.getRe();
        im += arg2.getIm();
        return this;
	}

	public ComplexNumber multiply(ComplexNumber arg2) {
		double temp = re * arg2.getRe() - im * arg2.getIm();
        this.im = im * arg2.getRe() + re * arg2.getIm();
        this.re = temp;
        return this;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
