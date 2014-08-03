import java.io.*;

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

public class rotmgAutoNexus extends JFrame implements ActionListener {
	int started = 0;
	private static final long serialVersionUID = 1L;
	private Robot robot;
	
	public static void main(String[] args) {
		new rotmgAutoNexus().setVisible(true);
	}
	
	public void Open() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}
	public void Wait(int time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	JLabel text = new JLabel("Text");
	
	private rotmgAutoNexus() {
		super("AutoNexus Script");
		setSize(700, 60);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		add(text);
		text.setText("Welcome. Start in a few seconds");
		Wait(5000);
		text.setText("Put the cursor on the grey color (at fame maybe).");
		repaint();
		Open();
		Wait(5000);
		PointerInfo a = MouseInfo.getPointerInfo();
		int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
		int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
		
		Color color = robot.getPixelColor(x, y);
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		
		text.setText("Now put the cursor on the hp bar, at which point you want it to teleport.");
		Wait(5000);
		a = MouseInfo.getPointerInfo();
		x = (int) MouseInfo.getPointerInfo().getLocation().getX();
		y = (int) MouseInfo.getPointerInfo().getLocation().getY();
		
		while (true) {
			text.setText("Current coordinates are at " + x + " " + y + ".");
			color = robot.getPixelColor(x, y);
			int nred = color.getRed();
			int ngreen = color.getGreen();
			int nblue = color.getBlue();
			if (
				nred - 3 <= red && red <= nred + 3
				&&
				ngreen - 3 <= green && green <= ngreen + 3
				&&
				nblue - 3 <= blue && blue <= nblue + 3
			) {
				for (int i = 0; i < 10; ++i) {
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.keyPress('R');
					robot.keyRelease('R');
				}
				text.setText("You've been saved :)");
				repaint();
				Wait(5000);
			}
			Wait(10);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
	}
}
