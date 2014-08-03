import java.awt.Color;
import java.awt.Robot;
import java.util.Scanner;

public class getcolor {
	public static void main(String[] args) throws Exception{
		Robot robot = new Robot();
		Scanner cin = new Scanner(System.in);
		int x, y;
		x = cin.nextInt(); y = cin.nextInt();
		Color color = robot.getPixelColor(x, y);
		System.out.println("Red   = " + color.getRed());
		System.out.println("Green = " + color.getGreen());
		System.out.println("Blue  = " + color.getBlue());
	}
}
 

   
