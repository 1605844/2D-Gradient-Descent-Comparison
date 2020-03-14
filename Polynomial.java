import java.util.Scanner;


public class Polynomial {
	
	private double[] p;
	
	public Polynomial(double[] poly) {
		
		int dim = poly.length;
		
		for (int i = poly.length - 1; i >= 0; i--) {
			if (poly[i] != 0) {
			break;
			}
			dim--;
		}
		
		this.p = new double[dim];
		
		for (int i = 0; i < dim; i++) {
			this.p[i] = poly[i];
		}
		
		
	}
	
	public int getLength() {
		return this.p.length;
	}
	
	public String toString() {
		String S = new String("");
		
		for (int i = getLength() - 1; i >= 0; i--) {
			if (this.p[i] != 0 && this.p[i] != 1) {
				S += this.p[i];
				if (i != 0) {
					S += "x^";
					S += i;
				}
			} else if (this.p[i] == 1) {
				if (i != 0) {
					S += "x^";
					S += i;
				}
			}
			
			if (i > 0) {
				if (this.p[i-1] != 0)
					S += " + ";
			}
			
		}
		
		return S;		
	}
	
	public double evaluate(double X) {
		
		int L = getLength();
		double N = 0;
		for (int i = 0; i < L; i++) {
			N += this.p[i] * Math.pow(X,i);
		}
		
		return N;
		
	}
	
	public Polynomial differentiate() {
		
		double[] deriv;
		
		if (getLength() == 0) {
			deriv = new double[1];
			deriv[0] = 0;
		} else {
			deriv = new double[getLength() - 1];
			for (int i = 1; i < getLength(); i++) {
				deriv[i-1] = i * this.p[i];
			}
		}
		
		Polynomial Q = new Polynomial(deriv);
		return Q;
		
	}
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter Polynomial Dimension:");
		int d = s.nextInt() + 1;
		
		System.out.println("Enter Coefficients");
		double[] polynomial = new double[d];
		for (int i = d - 1; i >= 0; i--) {
			double c = s.nextDouble();
			polynomial[i] = c;
		}
		
		Polynomial P = new Polynomial(polynomial);
		Polynomial Q = P.differentiate();
		System.out.println(Q.toString());
		
		
		
		
	}
	
	
}