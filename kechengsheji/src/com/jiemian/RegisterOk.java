package com.jiemian;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class RegisterOk extends JFrame {
	public void register(){
		JFrame jf = new JFrame();
		Container cp = jf.getContentPane();
		jf.setVisible(true);
		jf.setSize(200, 200);
		cp.setBounds(0, 0, 300, 200);
		JLabel label = new JLabel("<html><font size = 4 color = red>" +
				"¹§Ï²Äã£¬×¢²á³É¹¦</font></html>");
		label.setBounds(30, 50, 200, 30);
		cp.add(label);
	}
	
	/*public static void main(String[] args) {
		RegisterOk rs = new RegisterOk();
		rs.register();
	}*/
}
