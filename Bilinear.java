//file creator packages
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;

/* Gradient Descent on maps of the form
				   | a	b |   	    	|x|
	f(x,y) = [x y] | 	  | + [d e]		| |      + f
				   | b	c |   	    	|y|
*/
public class Bilinear{
	
	private double a,b,c,d,e,f;
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	public Bilinear(double a, double b, double c, double d, double e, double f) {
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	//Returns the map in polynomial form
	public String toString() {
		
		double twob = this.b * 2;
		String S = "f(x,y) = " + this.a + "x^2 + " + this.d + "x + " + twob + "xy + " + this.d + "y + " + this.c + "y^2" + this.f;
		return S;
		
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	//Finds the directional derivative in the x-direction
	//0: constant term, 1:x-term, 2:y-term
	public double[] directDerivX() {
		
		double[] x = new double[3];
		x[0] = this.d;
		x[1] = 2*this.a;
		x[2] = this.b;
		
		return x;
	}
	
	//Finds the directional derivative in the y-direction
	//0: constant term, 1:x-term, 2:y-term
	public double[] directDerivY() {
		
		double[] y = new double[3];
		y[0] = this.e;
		y[1] = this.b;
		y[2] = 2*this.c;
		
		return y;
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------	
	
	//Evaluates the map at a point P
	public double evalMap(Point P) {
		
		double X = P.getX();
		double Y = P.getY();
		
		double sum = this.a*X*X + 2*this.b*X*Y + this.c*Y*Y + this.d*X + this.e*Y + this.f;
		return sum;
		
	}
	
	//Evaluates a directional derivative d at a point P
	public double evalDeriv(Point P, double[] d) {
		
		double X = P.getX();
		double Y = P.getY();
		
		double sum = d[0] + d[1]*X + d[2]*Y;
		return sum;
		
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	//Returns the Gradient Descent step direction at a point P, as a point
	public Point GDStepDirection(Point P) {
		
		double[] x = directDerivX();
		double[] y = directDerivY();
		
		double X = evalDeriv(P, x);
		double Y = evalDeriv(P, y);
		
		Point p = new Point(X,Y);
		
		Point q = p.scale(-1);
		return q;
		
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	//Conducts Gradient descent with step length L and tolerance E
	public Point[] GD(Point P, double L, double E) {
		
		Point Old = new Point();
		Point New = P;
		Point Step = new Point();
		double difference;
		double NoSteps = 0;
		
		List<Point> samples = new ArrayList<>();
		
		do {
			Old = New;
			samples.add(Old);
			
			Step = GDStepDirection(Old);
			New = Old.add(Step.scale(L));
			
			difference = New.distance(Old);
			NoSteps += 1;
			
		} while (difference > E);
		
		samples.add(New);
		
		Point[] pts = new Point[samples.size()];
		for (int i = 0; i < samples.size(); i++) {
			pts[i] = samples.get(i);
		}
		
		return pts;
	}
	
	//Conducts Stochastic Gradient descent with step length L and tolerance E, choosing the step direction using a uniform distribution
	//Returns an array with: Number of steps taken, approximate minimizer, approximate minimal value
	public Point[] SGD(Point P, double L, double E) {
		
		Point Old = new Point();
		Point New = P;
		Point Step = new Point();
		double difference;
		double NoSteps = 0;
		double direction = 0;
		
		List<Point> samples = new ArrayList<>();
		
		do {
			direction = Math.random();
			Old = New;
			samples.add(Old);
			
			if (direction < 0.5) {
				double x = evalDeriv(Old, directDerivX());
				Point Q = new Point(x,0);
				New = Old.add(Q.scale(-L));
			} else {
				double y = evalDeriv(Old, directDerivY());
				Point Q = new Point(0,y);
				New = Old.add(Q.scale(-L));
			}
			
			difference = New.distance(Old);
			NoSteps += 1;
			
		} while (difference > E);
		
		samples.add(New);
		
		Point[] pts = new Point[samples.size()];
		for (int i = 0; i < samples.size(); i++) {
			pts[i] = samples.get(i);
		}
		
		return pts;
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	
	public String excelSet(Point[] p) {
			
			String s = "Step Number 	x-coordinate	y-coordinate	Value 	Error \n";
			double error;
			
			for (int i = 0; i < p.length; i++) {
				Point q = p[i];
				s += i + "	";
				s += q.getX() + "	";
				s += q.getY() + "	";
				s += evalMap(q) + "	";
				
				if(i > 0) {
					error = q.distance(p[i-1]);
				} else {
					error = 0;
				}
				s += error + "	";
				
				s += "\n";
			}
			
			return s;
	}
	
	private static void writeFile(String data, String fileName) {
        try {
            Files.write(Paths.get("/Users/danie/OneDrive/Documents/Uni/4th Year Project/Java Programs/Gradient Descent/" + fileName + ".txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	public static void main (String[] args) {
		
		Bilinear B = new Bilinear(1,0.5,1,-2,-10,25);
		Point P = new Point(0,0);
		
		Point[] GDresults = B.GD(P,0.1,0.001);
		Point[] SGDresults = B.SGD(P,0.1,0.001);
		String S = B.excelSet(GDresults);
		String eS = B.excelSet(SGDresults);
		writeFile(S, "GD");
		writeFile(eS, "SGD");
		
	}
}








