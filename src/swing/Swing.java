package swing;

import javax.swing.*;

public class Swing extends JFrame {
	
	
	public static void main(String[]args){
		MySwingTry first = new MySwingTry();
		first.setTitle("First try");
		first.setSize(300,200);
		first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		first.setVisible(true);
		
	}
	
	
	JPanel mypanel;
	JButton mybutton;
	JLabel mylabel;
	
	public void MySwingTry(){
		mypanel = new JPanel();
		mybutton = new JButton("OK");
		mylabel = new JLabel();
		
		mypanel.add(mybutton);
		mypanel.add(mylabel);
		this.add(mypanel);
	}
	
	

	//test
}
