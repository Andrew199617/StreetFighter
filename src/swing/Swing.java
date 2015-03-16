package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Swing
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               SliderTestFrame frame = new SliderTestFrame();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}

/**
 * A frame with many sliders and a text field to show slider values.
 */
class SliderTestFrame extends JFrame implements ChangeListener, ActionListener
{
	JButton saveButton = new JButton("Save");
	FileWriter outputStream;
	JSlider slider1 = new JSlider();
	JSlider slider2 = new JSlider();
	JSlider slider3 = new JSlider();
	JSlider slider4 = new JSlider();
	JSlider slider5 = new JSlider();
	JSlider slider6 = new JSlider();
    
	int monster1Health = 50;
	int monster1MovementSpeed = 50;
	int monster1Damage = 50;
	int monster2Health = 50;
	int monster2MovementSpeed = 50;
	int monster2Damage = 50;
	
   public SliderTestFrame()
   {
	   
	   
	   
	   
	   
      setTitle("Monster Config");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      sliderPanel = new JPanel();
      sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

      // add a plain slider

      
      //Save Button
      ButtonListener listen = new ButtonListener();
	   saveButton.addActionListener(this);  //Calls the ButtonListener Class.
	  
	 //  this.add(saveButton); // I have no idea what this does, but it seems to work without it.
	   
      // add a slider that snaps to ticks depending on the snap to ticks condition.

      slider1 = new JSlider();
      slider1.setPaintTicks(true);
      slider1.setSnapToTicks(false);
      slider1.setMajorTickSpacing(20);
      slider1.setMinorTickSpacing(5);
      addSlider(slider1, "Monster 1 Health");
      
      slider2.setPaintTicks(true);
      slider2.setSnapToTicks(false);
      slider2.setMajorTickSpacing(20);
      slider2.setMinorTickSpacing(5);
      addSlider(slider2, "Monster 1 Movement Speed");
      
      slider3.setPaintTicks(true);
      slider3.setSnapToTicks(false);
      slider3.setMajorTickSpacing(20);
      slider3.setMinorTickSpacing(5);
      addSlider(slider3, "Monster 1 Damage");
      
      slider4.setPaintTicks(true);
      slider4.setSnapToTicks(false);
      slider4.setMajorTickSpacing(20);
      slider4.setMinorTickSpacing(5);
      addSlider(slider4, "Monster 2 Health");
      
      slider5.setPaintTicks(true);
      slider5.setSnapToTicks(false);
      slider5.setMajorTickSpacing(20);
      slider5.setMinorTickSpacing(5);
      addSlider(slider5, "Monster 2 Movement Speed");
      
      slider6.setPaintTicks(true);
      slider6.setSnapToTicks(false);
      slider6.setMajorTickSpacing(20);
      slider6.setMinorTickSpacing(5);
      addSlider(slider6, "Monster 2 Damage");
   
      // add the text field that displays the slider value

      textField = new JTextField();
      add(sliderPanel, BorderLayout.CENTER);
      add(textField, BorderLayout.SOUTH);
   }

   /**
    * Adds a slider to the slider panel and hooks up the listener
    * @param s the slider
    * @param description the slider description
    */
   public void addSlider(JSlider s, String description)
   {
      s.addChangeListener(this); // Common listener for sliders
      JPanel panel = new JPanel();
      panel.add(s);
      panel.add(new JLabel(description));
      sliderPanel.add(panel);
      panel.add(saveButton);
   }

   public static final int DEFAULT_WIDTH = 430;
   public static final int DEFAULT_HEIGHT = 450;

   private JPanel sliderPanel;
   private JTextField textField;
@Override
public void stateChanged(ChangeEvent event)
{
   // update text field when the slider value changes
	monster1Health = slider1.getValue();
	monster1MovementSpeed = slider2.getValue();
	monster1Damage = slider3.getValue();
	monster2Health = slider4.getValue();
	monster2MovementSpeed = slider5.getValue();
	monster2Damage = slider6.getValue();
   JSlider source = (JSlider) event.getSource();
   textField.setText("" + source.getValue());
   
   
}

@Override
public void actionPerformed(ActionEvent e) {
	//SliderTestFrame.dispose();
		System.out.println("Monster 1 Health: " + monster1Health);
		System.out.println("Monster 1 Movement Speed: " + monster1MovementSpeed);
		System.out.println("Monster 1 Damage: " + monster1Damage);
		System.out.println("Monster 2 Health: " + monster2Health);
		System.out.println("Monster 2 Movement Speed: " + monster2MovementSpeed);
		System.out.println("Monster 2 Damage: " + monster2Damage);
		
	FileWriter outputStream = null; //I think this makes the file writer itself.
	try {
		outputStream = new FileWriter("Properties.txt");
		outputStream.write("Monster 1 Health: " + monster1Health + "\n");
		outputStream.write("Monster 1 Movement Speed: " + monster1MovementSpeed + "\n");
		outputStream.write("Monster 1 Damage: " + monster1Damage + "\n");
		outputStream.write("Monster 2 health: " + monster2Health + "\n");
		outputStream.write("Monster 2 Movement Speed: " + monster2MovementSpeed + "\n");
		outputStream.write("Monster 2 Damage: " + monster2Damage + "\n");
		outputStream.close();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
		
}
}