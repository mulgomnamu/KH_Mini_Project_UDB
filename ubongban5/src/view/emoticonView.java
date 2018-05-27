package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import chatting.ChatClient;

public class emoticonView extends JFrame implements ActionListener {

	JButton btn_1, btn_2, btn_3, btn_4, 
	btn_5, btn_6, btn_7, btn_8, 
	btn_9, btn_10, btn_11, btn_12, 
	btn_13, btn_14, btn_15, btn_16;

		ChatClient cc;
		
		public emoticonView() {
		
		setLayout(null);
		
		cc = new ChatClient();
		
		btn_1 = new JButton(new ImageIcon("src\\chatting\\pikachu_50.png"));
		btn_2 = new JButton(new ImageIcon("src\\chatting\\charmander_50.png"));
		btn_3 = new JButton(new ImageIcon("src\\chatting\\dratini_50.png"));
		btn_4 = new JButton(new ImageIcon("src\\chatting\\snorlax_50.png"));
		btn_5 = new JButton(new ImageIcon("src\\chatting\\5_50.gif"));
		btn_6 = new JButton(new ImageIcon("src\\chatting\\6_50.gif"));
		btn_7 = new JButton(new ImageIcon("src\\chatting\\7_50.gif"));
		btn_8 = new JButton(new ImageIcon("src\\chatting\\8_50.gif"));
		btn_9 = new JButton(new ImageIcon("src\\chatting\\9_50.gif"));
		btn_10 = new JButton(new ImageIcon("src\\chatting\\10_50.gif"));
		btn_11 = new JButton(new ImageIcon("src\\chatting\\11_50.gif"));
		btn_12 = new JButton(new ImageIcon("src\\chatting\\12_50.gif"));
		btn_13 = new JButton(new ImageIcon("src\\chatting\\13_50.gif"));
		btn_14 = new JButton(new ImageIcon("src\\chatting\\14_50.gif"));
		btn_15 = new JButton(new ImageIcon("src\\chatting\\15_50.gif"));
		btn_16 = new JButton(new ImageIcon("src\\chatting\\16_50.gif"));
		
		btn_1.setBounds(0, 0, 50, 50);
		btn_2.setBounds(50, 0, 50, 50);
		btn_3.setBounds(100, 0, 50, 50);
		btn_4.setBounds(150, 0, 50, 50);
		btn_5.setBounds(0, 50, 50, 50);
		btn_6.setBounds(50, 50, 50, 50);
		btn_7.setBounds(100, 50, 50, 50);
		btn_8.setBounds(150, 50, 50, 50);
		btn_9.setBounds(0, 100, 50, 50);
		btn_10.setBounds(50, 100, 50, 50);
		btn_11.setBounds(100, 100, 50, 50);
		btn_12.setBounds(150, 100, 50, 50);
		btn_13.setBounds(0, 150, 50, 50);
		btn_14.setBounds(50, 150, 50, 50);
		btn_15.setBounds(100, 150, 50, 50);
		btn_16.setBounds(150, 150, 50, 50);
		
		add(btn_1);
		add(btn_2);
		add(btn_3);
		add(btn_4);
		add(btn_5);
		add(btn_6);
		add(btn_7);
		add(btn_8);
		add(btn_9);
		add(btn_10);
		add(btn_11);
		add(btn_12);
		add(btn_13);
		add(btn_14);
		add(btn_15);
		add(btn_16);
		
		
		setBounds(900,600,200,330);
		
		}
		
		public JButton getBtn_1() {
		return btn_1;
		}
		
		public void setBtn_1(JButton btn_1) {
		this.btn_1 = btn_1;
		}
		
		public JButton getBtn_2() {
		return btn_2;
		}
		
		public void setBtn_2(JButton btn_2) {
		this.btn_2 = btn_2;
		}
		
		public JButton getBtn_3() {
		return btn_3;
		}
		
		public void setBtn_3(JButton btn_3) {
		this.btn_3 = btn_3;
		}
		
		public JButton getBtn_4() {
		return btn_4;
		}
		
		public void setBtn_4(JButton btn_4) {
		this.btn_4 = btn_4;
		}
		
		public JButton getBtn_5() {
		return btn_5;
		}
		
		public void setBtn_5(JButton btn_5) {
		this.btn_5 = btn_5;
		}
		
		public JButton getBtn_6() {
		return btn_6;
		}
		
		public void setBtn_6(JButton btn_6) {
		this.btn_6 = btn_6;
		}
		
		public JButton getBtn_7() {
		return btn_7;
		}
		
		public void setBtn_7(JButton btn_7) {
		this.btn_7 = btn_7;
		}
		
		public JButton getBtn_8() {
		return btn_8;
		}
		
		public void setBtn_8(JButton btn_8) {
		this.btn_8 = btn_8;
		}
		
		public JButton getBtn_9() {
		return btn_9;
		}
		
		public void setBtn_9(JButton btn_9) {
		this.btn_9 = btn_9;
		}
		
		public JButton getBtn_10() {
		return btn_10;
		}
		
		public void setBtn_10(JButton btn_10) {
		this.btn_10 = btn_10;
		}
		
		public JButton getBtn_11() {
		return btn_11;
		}
		
		public void setBtn_11(JButton btn_11) {
		this.btn_11 = btn_11;
		}
		
		public JButton getBtn_12() {
		return btn_12;
		}
		
		public void setBtn_12(JButton btn_12) {
		this.btn_12 = btn_12;
		}
		
		public JButton getBtn_13() {
		return btn_13;
		}
		
		public void setBtn_13(JButton btn_13) {
		this.btn_13 = btn_13;
		}
		
		public JButton getBtn_14() {
		return btn_14;
		}
		
		public void setBtn_14(JButton btn_14) {
		this.btn_14 = btn_14;
		}
		
		public JButton getBtn_15() {
		return btn_15;
		}
		
		public void setBtn_15(JButton btn_15) {
		this.btn_15 = btn_15;
		}
		
		public JButton getBtn_16() {
		return btn_16;
		}
		
		public void setBtn_16(JButton btn_16) {
		this.btn_16 = btn_16;
		}

			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
			}
		
		}
