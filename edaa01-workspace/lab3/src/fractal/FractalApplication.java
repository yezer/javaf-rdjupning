package fractal;

import mountain.Point;
import koch.Koch;
import mountain.Mountain;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[1];
		Point p1 = new Point(200,300);
		Point p2 = new Point(100,500);
		Point p3 = new Point(400,200);
		
		fractals[0] = new Mountain(p1,p2,p3);
		new FractalView(fractals,"Fraktaler");
		
		
	//	fractals[0] = new Koch(100);
	 //new FractalView(fractals, "Fraktaler");
	}

}
