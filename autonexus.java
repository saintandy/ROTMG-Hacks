
import java.awt.Color;
import java.awt.Robot;
import java.util.Scanner;
import java.awt.event.KeyEvent;

public class autonexus {
	public static boolean inside(int a, int b) {
		if (b - 3 <= a && a <= b + 3)
			return true;
		return false;
	}
	public static void main(String[] args) throws Exception {
		Robot robot = new Robot();
		Scanner cin = new Scanner(System.in);
		int x, y, c;
		System.out.print("Enter the coordinates of the hp bar: ");
		x = cin.nextInt(); y = cin.nextInt();
		System.out.print("Enter the color of the grey hp bar: ");
		c = cin.nextInt();
		while (true) {
			Color color = robot.getPixelColor(x, y);
			if (inside(color.getRed(), c) == true && inside(color.getGreen(), c) == true && inside(color.getBlue(), c) == true) {
				for (int i = 0; i < 10; ++i) {
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.keyPress('R');
					robot.keyRelease('R');
				}
				Thread.sleep(5000);
			}
			Thread.sleep(10);
		}
	}
}
