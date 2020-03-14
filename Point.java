
public class Point {
	
	private double a,b;
	
	
	//creates point at (a,b)
	public Point(double a, double b) {
		this.a = a;
		this.b = b;
	}

	
	public double getX() {
		return this.a;
	}
	
	public double getY() {
		return this.b;
	}
	
	public Point add(Point q) {
		double x = this.a + q.getX();
		double y = this.b + q.getY();
		
		return new Point(x,y);
	}
	
	public Point scale(double N) {
		double x = this.a * N;
		double y = this.b * N;
		
		return new Point(x,y);
	}
	
	//initialises point at origin
	public Point() {
		this.a = 0;
		this.b = 0;
	}
	
	
	public double distance(Point p) {
		
		double x = this.a - p.getX();
		double y = this.b - p.getY();
		
		double dist = Math.sqrt(x*x + y*y);
		return dist;
		
	}
	
	public String toString() {
		String S = "(" + this.a + "," + this.b + ")";
		return S;
	}
	
	public String xToString() {
		String S = "";
		S += this.a;
		return S;
	}
	
	public String yToString() {
		String S = "" + this.b;
		return S;
	}
	
	public static void main(String[] args) {
		
		Point p = new Point();
		System.out.println(p.toString());
		System.out.println(p.yToString());
		
	}
		
}






