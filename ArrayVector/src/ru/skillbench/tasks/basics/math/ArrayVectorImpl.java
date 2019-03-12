package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {
	private double[] arr;
	int length;

    public ArrayVectorImpl() {
    	arr = null;
    	length = 0;
    }
	
	public ArrayVector clone() {
        ArrayVector cloneArr = new ArrayVectorImpl();
        cloneArr.set(get().clone());
        return cloneArr;
    }

	public void set(double... elements) {
		arr = elements;
		length = elements.length;
	}

	public double[] get() {
		return arr;
	}

	public int getSize() {
		return length;
	}

	public void set(int index, double value) {
		if(index >= 0){
			/*if(index < arr.length){
				arr[index] = value;
			}
			else{
				double[] temp = Arrays.copyOf(arr, arr.length + 1);
				temp[arr.length] = value;
				arr = temp;
			}*/
			if (index >= 0) {
	            if (index > arr.length - 1) {
	                double[] temp = new double[index + 1];
	                System.arraycopy(arr, 0, temp, 0, arr.length);
	                arr = temp;
	                length = index + 1;
	            }
	            arr[index] = value;
			}
		}
			

	}

	public double get(int index) throws ArrayIndexOutOfBoundsException {
		if(index < 0 && index > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[index];
	}

	public double getMax() {
		double max = arr[0];
        for(int i = 0; i < length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
	}

	public double getMin() {
		double min = arr[0];
        for(int i = 0 ;i < length; i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
	}

	public void sortAscending() {
		double temp;
        for(int j = 0;j < get().length;j++){
            for(int i = 0; i < get().length-j-1; i++) {
                if(get()[i] > get()[i+1]) {
                    temp = get()[i];
                    get()[i] = get()[i+1];
                    get()[i+1] = temp;
                }
            }
        }
	}

	public void mult(double factor) {
		for(int i = 0; i < get().length; i++){
            get()[i] = get()[i] * factor;
        }
	}

	public ArrayVector sum(ArrayVector anotherVector) {
		if(anotherVector.get().length < get().length) {
			for (int i = 0;i < anotherVector.get().length; i++){
				arr[i] += anotherVector.get()[i];
			}
		}
		else{
			for (int i = 0;i < get().length; i++){
				arr[i] += anotherVector.get()[i];
			}
			
		}
		return this;
		/*for (int i = 0;i < get().length; i++){
			arr[i] += anotherVector.get()[i];
        }
    return this;*/
	}

	public double scalarMult(ArrayVector anotherVector) {
		double result = 0;
		 
        if(anotherVector.get().length < length) {
            for (int i = 0; i < anotherVector.get().length; i++) {
                result += arr[i] * anotherVector.get(i);
            }
        }
        else{
            for (int i = 0; i < length; i++) {
                result += arr[i] * anotherVector.get(i);
            }
            
        }
        return result;
	}

	public double getNorm() {
        double summ = 0;
        for(int i = 0;i < arr.length;i++){
            summ += arr[i] * arr[i];
        }
        return Math.sqrt(summ);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayVectorImpl a = new ArrayVectorImpl();
		ArrayVectorImpl b = new ArrayVectorImpl();
		ArrayVectorImpl c = new ArrayVectorImpl();
		a.set(8, 3, 12, 1);
		b.set(1, 1, 1, 1, 1);
		c.set(2, 2, 2, 2, 2);
		System.out.println(Arrays.toString(a.get()));
		System.out.println(a.getSize());
		a.set(0, 2);
		a.set(7, 5);
		System.out.println(Arrays.toString(a.get()));
		System.out.println(a.get(1));
		System.out.println(a.getMin());
		System.out.println(a.getMax());
		a.mult(2);
		System.out.println(Arrays.toString(a.get()));
		a.sortAscending();
		System.out.println(Arrays.toString(a.get()));
		a.mult(4);
		System.out.println(Arrays.toString(a.get()));
		
		System.out.println(Arrays.toString(a.sum(b).get()));
		System.out.println("alloha ");
		System.out.println(Arrays.toString(b.get()));
		System.out.println(Arrays.toString(c.get()));
		ArrayVectorImpl d = new ArrayVectorImpl();
		d.set(1, 1, 1, 1, 1);
		ArrayVectorImpl e = new ArrayVectorImpl();
		e.set(2, 2, 2, 2, 2);
		System.out.println(Arrays.toString(d.get()));
		System.out.println(Arrays.toString(e.get()));
		System.out.println(d.scalarMult(e));
		
		
		

	}

}
