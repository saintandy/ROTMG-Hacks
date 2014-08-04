import java.io.*;
import java.lang.Thread;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

class Pixel implements Runnable {
	private Robot robot;
	private Thread T;
	private String threadName;
	private int red, green, blue, x, y;
	Pixel(String name, int _red, int _green, int _blue, int _x, int _y) {
		threadName = name;
		red = _red; green = _green; blue = _blue; x = _x; y = _y;
		// System.out.println("Creating " + threadName + " thread.");
	}
	private boolean alike(int first, int second) {
		if (first - 3 <= second && second <= first + 3) {
			return true;
		}
		return false;
	}
	private void StartRobot() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}
	private void Sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	private void FuckOFF() {
		for (int i = 0; i < 2; ++i) {
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress('R');
			robot.keyRelease('R');
		}
		// System.out.println("You were saved :)");
	}
	public void run() {
		StartRobot();
		Color color;
		while (true) {
			// System.out.println("Working " + threadName);
			color = robot.getPixelColor(x, y);
			int _red = color.getRed();
			int _green = color.getGreen();
			int _blue = color.getBlue();
			if (alike(_red, red) && alike(_green, green) && alike(_blue, blue)) {
				FuckOFF();
				Sleep(10000);
			}
			Sleep(200);
		}
	}	 
	public void start() {
		// System.out.println("Starting " + threadName + " thread");
		if (T == null) {
			T = new Thread(this, threadName);
			T.start();
		}
	}
}

public class RealmAutoNexus extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Robot robot;
	JLabel text = new JLabel();
	JButton on = new JButton("ON");
	JButton off = new JButton("OFF");

	private Color color;
	private PointerInfo a;
	private int x, y, red, green, blue;
	private boolean started = false;
	
	private void StartRobot() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}
	private static void Sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	public static void main(String[] args) {
		new RealmAutoNexus().setVisible(true);
	}
	private RealmAutoNexus() {
		super("AutoNexus");
		setSize(220, 80);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new FlowLayout());
		
		on.setActionCommand("ON");
		off.setActionCommand("OFF");
		on.addActionListener(this);
		off.addActionListener(this);
		add(text);
		
		StartRobot();
		
		text.setText("Put the cursor on the grey");
		Sleep(3000);
		
		a = MouseInfo.getPointerInfo();
		x = (int) MouseInfo.getPointerInfo().getLocation().getX();
		y = (int) MouseInfo.getPointerInfo().getLocation().getY();
		color = robot.getPixelColor(x, y);
		red = color.getRed();
		green = color.getGreen();
		blue = color.getBlue();
		
		text.setText("Put the cursor on the health bar");
		Sleep(3000);
		
		a = MouseInfo.getPointerInfo();
		x = (int) MouseInfo.getPointerInfo().getLocation().getX();
		y = (int) MouseInfo.getPointerInfo().getLocation().getY();

		text.setText("Press ON when you're ready :)");
		Sleep(1000);
		add(on, BorderLayout.SOUTH);
		add(off, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent event) {
		String which = event.getActionCommand();
		if (which.equals("ON") && started == false) {
			started = true;
			text.setText("The hack is ON. Good luck");
			repaint();
			for (int i = 1; i <= 10; ++i) {
				new Pixel("Process " + i, red, green, blue, x, y).start();
				Sleep(100);
			}
		} else if (which.equals("OFF")) {
			text.setText("The hack is OFF. Goodbye");
			repaint();
			System.exit(1);
		}
	}
}







