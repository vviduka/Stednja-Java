import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class jpane extends JFrame {

	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JTextField tf5;
	JButton button;
	float num1, num2, num3, num4, num5;
	String stednje[] = { "Stednja po viðenju", "Oroèena štednja",
			"Stambena štednja" };
	String vrijeme[] = { "3 mj / 3.5%", "6 mj / 3.7%", "12 mj / 4.0%",
			"24 mj / 4.2%", "36 mj / 4.3%" };
	JComboBox<?> combo = new JComboBox<Object>(stednje);
	JComboBox<?> combo2 = new JComboBox<Object>(vrijeme);

	public jpane() {
		JFrame jpane = new JFrame();
		setLayout(new GridLayout(0, 1));
		combo2.setVisible(false);

		tf1 = new JTextField(3);
		tf2 = new JTextField(3);
		tf3 = new JTextField(3);
		tf4 = new JTextField(3);
		tf5 = new JTextField(3);

		tf3.setEditable(false);
		tf4.setVisible(false);
		tf4.setEditable(false);
		tf5.setEditable(false);

		label1 = new JLabel("Odaberi vrstu stednje");
		label2 = new JLabel("Glavnica:");
		label3 = new JLabel("Vrijeme:");
		label4 = new JLabel("Iznos kn/eu");
		label5 = new JLabel("Drzavni poticaj:");
		label5.setVisible(false);

		add(label1);
		add(combo);
		add(label2);
		add(tf1);
		add(label3);
		add(tf2);
		add(combo2);
		add(label5);
		add(tf4);

		add(label4);
		add(tf3);
		add(tf5);

		button = new JButton("Izracunaj");
		add(button);

		eventButton e = new eventButton();
		button.addActionListener((ActionListener) e);

		eventCombo b = new eventCombo();
		combo.addActionListener((ActionListener) b);

	}

	public class eventButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
				
			

				String input1 = tf1.getText();
				String input2 = tf2.getText();
				try{
					num1 = Float.parseFloat(input1);
		 		} catch(NumberFormatException nfe) {
			 
		 				num1 = 0;
		 		}
				try{
				num2 = Float.parseFloat(input2);
				 } catch(NumberFormatException nfe) {
			            num2 = 0;
			        }

				String tip = (String)combo.getSelectedItem();
				String tmp = (String)combo2.getSelectedItem();
				float postotak = 0;
				float vr = 0;
		
				switch (tip) {
				
					case "Stednja po viðenju": 
					
						if(num1<2000){
							JOptionPane.showMessageDialog(null, "Minimalni iznos glavnice mora biti 2000 kn" , "Greška", JOptionPane.ERROR_MESSAGE);
							break;
						}
					
						postotak = (float) 0.25;
						num3 = (num1 + ((num1 * (num2/12) * postotak)/100));
						num5 = (float) (num3 / 7.46);
					break;
					
					
					
					
					case "Oroèena štednja":
						
						
					
						if(num1<5000){
							JOptionPane.showMessageDialog(null, "Minimalni iznos glavnice mora biti 5000 kn" , "Greška", JOptionPane.ERROR_MESSAGE);
							break;
						}
					
						switch (tmp) {
						
							case "3 mj / 3.5%":
							
								num3 = (float) (num1 + ((num1 * 0.25 * 3.5)/100));
							break;
						
							case "6 mj / 3.7%":
								num3 = (float) (num1 + ((num1 * 0.5 * 3.5)/100));
							break;
						
							case "12 mj / 4.0%":
								num3 = (float) (num1 + ((num1 * 1 * 4)/100));
							break;
							
							
							case  "24 mj / 4.2%":
							
								num3 = (float) (num1 + ((num1 * 2 * 4.2)/100));
							break;
						
							
							case "36 mj / 4.3%":
							
								num3 = (float) (num1 + ((num1 * 3 * 4.3)/100));
							break;

							default:
							break;
							}
							num5 = (float) (num3 / 7.46);
					break;
					
				case "Stambena štednja":
					double poticaj = 0;
					int faktor;
					postotak = 2;
					num3 = num1 + ((num1 * num2 * postotak)/100);
					
					num5 = (float) (num3 / 7.46);
					
					faktor = (int)num2/5;
					poticaj = faktor* 0.02 * num1;
					
					String input4 = Double.toString(poticaj);		
					tf4.setText(input4);
					
					
					break;

				default:
					break;
				}
				String input5 = Float.toString(num5);
				tf5.setText(input5);
				
				String input3 = Float.toString(num3);
				tf3.setText(input3);
		}
	}

	public class eventCombo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent b) {

			String temp1;
			temp1 = (String) combo.getSelectedItem();


			switch (temp1) {
			case "Stednja po viðenju":
				tf2.setVisible(true);
				combo2.setVisible(false);
				label5.setVisible(false);
				tf4.setVisible(false);
				break;
			case "Oroèena štednja":
				combo2.setVisible(true);
				tf2.setVisible(false);
				label5.setVisible(false);
				tf4.setVisible(false);
				break;
			case "Stambena štednja":
				tf2.setVisible(true);
				combo2.setVisible(false);
				label5.setVisible(true);
				tf4.setVisible(true);
				break;

			default:
				break;
			}

		}

	}

	public static void main(String[] args) {
		jpane gui = new jpane();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(500, 500);
		gui.setTitle("Stednja");
		gui.setVisible(true);

	}

}
