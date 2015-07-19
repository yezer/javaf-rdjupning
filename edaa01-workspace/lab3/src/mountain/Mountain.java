package mountain;
import fractal.*;
import java.lang.Math;
import java.util.ArrayList;

public class Mountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private ArrayList<Point> points;
	
	
	public Mountain(Point p1, Point p2, Point p3){
		super();
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
		points.add(p1);
		points.add(p2);
		points.add(p3);
		
	}

public String getTitle() {
	return "Mountain";
}

/** Draws the fractal.  
 * @param turtle the turtle graphic object
 */
public void draw(TurtleGraphics turtle) {
	turtle.moveTo(p1.getX(),p1.getY());
	fractalLine(turtle, order, points.get(2), p3);
	fractalLine(turtle, order, p3, p1);
	fractalLine(turtle, order, p1, p2);
}



private void fractalLine(TurtleGraphics turtle, int order, Point nextP, Point nextnextP) {
	
	
	if(order==0){
		turtle.forwardTo(nextP.getX(),nextP.getY());	
	}
	
	else{
		Point stand= new Point(turtle.getX(),turtle.getY());	
		Point middle = middlePoint(stand,nextP);
		turtle.moveTo(middle.getX(), middle.getY());
		Point nextMiddle = middlePoint(nextP, nextnextP);
		fractalLine(turtle,order-1,nextMiddle, nextnextP);
		turtle.moveTo(nextP.getX(), nextP.getY());

	}
	}


private Point middlePoint(Point p1, Point p2){
	Point middle = new Point(p1.getX()+(p2.getX()-p1.getX())/2, p1.getY()+(p2.getY()-p1.getY())/2);
	return middle;
}

























private double distance(Point q1, Point q2){
double deltaX = q1.getX()-q2.getX();
double deltaY=q1.getY()-q2.getY();
double distance= Math.sqrt(deltaX*deltaX+deltaY*deltaY);

return distance;
}
}

