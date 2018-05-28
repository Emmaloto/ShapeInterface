<<<<<<< HEAD
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class ShapeInterface extends JComponent implements ActionListener,ChangeListener {

	private JFrame fr;
	private JPanel bottomPanel, bottomOuterPanel, topPanel1, topPanel2, topPanel3,  topOuterPanel, warPanel;
	private JButton test;
	private JComboBox<String> shapes;
	private JComboBox<String> colors;
	private JSlider redSlider, blueSlider, greenSlider;
	private JTextField redQ, greenQ, blueQ, hexCode;
	private JLabel rl, gl, bl;
	
	private JLabel dimLabels[] = {new JLabel("Size: "), 
			                      new JLabel("Width: "), 
			                      new JLabel("Height: "), 
			                      new JLabel("Corner Arc: ")};
	private JTextField dimFields[];
	
	private String prevHex;
	private JLabel warning;
	
	private ImageIcon rIcon, bIcon, gIcon;
	private int dimQty[] = {300,300,300,10};
	//private int shapeWidth, shapeHeight;
	
	public static void main(String[] args) {
		new ShapeInterface();

	}
	
	/*
	 * Remember
	 * 0 - Size
	 * 1-  Width
	 * 2 - Height
	 * 3 - Corner Arc
	 * */
	
	public ShapeInterface(){
		loadImages();
		
		String[] s = {"Square", "Circle","Rectangle", "Rounded Rect", "Polygon (not yet available)"};
		String[] c = {"None", "Yellow", "Brown", "Gray", "Magenta", 
				      "Orange", "Pink", "Cyan", "Purple", "Black", "White"};
		shapes = new JComboBox<String>(s);
		colors = new JComboBox<String>(c);
		shapes.setSelectedIndex(0);
		
		//colors.setSize(50, 100);
		
		fr = new JFrame();
		bottomPanel = new JPanel();
		bottomOuterPanel = new JPanel();
		topPanel2  = new JPanel();
		topPanel1  = new JPanel();
		topPanel3  = new JPanel();
		topOuterPanel  = new JPanel();
		warPanel = new JPanel();
		
	    fr.setTitle("Simple Slider");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Close window on exit
	 	fr.setSize(900,900);
		
		test = new JButton("Rotate Image");
		  
		redSlider   = new JSlider(0, 255, 0);
		blueSlider  = new JSlider(0, 255, 0);
		greenSlider = new JSlider(0, 255, 0);		
		rl = new JLabel( "Red", rIcon, 0);
		gl = new JLabel("Green",gIcon, 0);
		bl = new JLabel("Blue", bIcon, 0);
		
		// Different fields for different properties of different shapes
		dimFields = new JTextField[dimLabels.length];
		
		for(int i = 0; i < dimFields.length; i++ ){			  
			dimFields[i] = new JTextField(dimQty[i]+"");	
			dimFields[i].setPreferredSize(   new Dimension(40, 20) );
		}

		// R G B Text fields & Hexcode
		redQ   = new JTextField(  redSlider.getValue() + "");
		greenQ = new JTextField(greenSlider.getValue() + "");
		blueQ  = new JTextField( blueSlider.getValue() + "");
		

		hexCode= new JTextField( "#" + hexVal(redSlider.getValue()) + 
				                       hexVal(greenSlider.getValue()) + 
				                       hexVal(blueSlider.getValue()) );
		warning = new JLabel("afjoiafjowafjaowjewaoj");
		
		redQ.setPreferredSize(   new Dimension(40, 20) );
		greenQ.setPreferredSize( new Dimension(40, 20) );
		blueQ.setPreferredSize(  new Dimension(40, 20) );
		hexCode.setPreferredSize(new Dimension(60, 20) );
		
		redQ.setForeground(Color.RED);
		greenQ.setForeground(Color.GREEN);
		greenQ.setBackground(Color.GRAY);
		blueQ.setForeground(Color.BLUE);
		
	 	bottomPanel.setBackground(new Color(217, 217, 217));
		
	 	// Add sliders
	 	bottomPanel.add(rl);
	 	bottomPanel.add(redSlider);
	 	
	 	bottomPanel.add(gl);
	 	bottomPanel.add(greenSlider);
	 	
	 	bottomPanel.add(bl);
	 	bottomPanel.add(blueSlider);
	 	
	 	
	 	

	 	// Shapes
	 	//topPanel1.setPreferredSize(new Dimension(100,50));
	 	topPanel1.setBackground(new Color(130,138,140));
	 	topPanel1.add(shapes);	 	
	 	topPanel1.add(dimLabels[0]);
	 	topPanel1.add(dimFields[0]);
	 	
		topPanel1.add(dimLabels[1]);
		topPanel1.add(dimFields[1]);
		
		topPanel1.add(dimLabels[2]);
		topPanel1.add(dimFields[2]);
		
		topPanel1.add(dimLabels[3]);
		topPanel1.add(dimFields[3]);
		removeAll();

	 	// Color fields
	 	topOuterPanel.setLayout(new GridLayout(0,1,3,3));
	 	topPanel2.setBackground(Color.WHITE);
	 	topPanel2.add(new JLabel ("Red:") );
	 	topPanel2.add(redQ);
	 	
	 	topPanel2.add(new JLabel ("Green:") );
	 	topPanel2.add(greenQ);
	 	
	 	topPanel2.add(new JLabel ("Blue:") );
	 	topPanel2.add(blueQ);
	 	
	 	//topPanel2.add(new JLabel ("Hex Code: In Progress") );
	 	topPanel2.add(new JLabel ("Hex Code:") );
	 	topPanel2.add(hexCode);
	 	
	 	
	 	
	 	// Default colors
	 	/*"None", "Yellow", "Brown", "Gray", "Magenta", 
				      "Orange", "Pink", "Cyan", "Purple", "Black", "White"*/
	 	topPanel3.setBackground(Color.WHITE);
	 	colors.setPreferredSize(new Dimension(100,30));
	 	colors.setBackground(Color.WHITE);
	 	topPanel3.add(new JLabel("Default Color: "));
	 	topPanel3.add(colors);
	 	
	 	warPanel.add(new JLabel("WARNING: "));
	 	warPanel.add(warning);
	 	
		topOuterPanel.add(topPanel1);
		topOuterPanel.add(topPanel2);
		topOuterPanel.add(topPanel3);
		topOuterPanel.add(warPanel);
	 	
		fr.getContentPane().add(this);    			    
		
		// Extra paneling because I may need to add something else here
		bottomOuterPanel.add(bottomPanel);
		
		fr.add(topOuterPanel,  BorderLayout.NORTH); 
		fr.add(bottomOuterPanel,  BorderLayout.SOUTH); 
			

		fr.setVisible(true);		
		
		redSlider.addChangeListener(this);
		greenSlider.addChangeListener(this);
		blueSlider.addChangeListener(this);
		
		shapes.addActionListener(this);
		colors.addActionListener(this);
		redQ.addActionListener(this);
		greenQ.addActionListener(this);
		blueQ.addActionListener(this);
		hexCode.addActionListener(this);
		
		for(int i = 0; i < dimFields.length; i++ )
		  dimFields[i].addActionListener(this);
		  
		  /* VERY IMPORTANT with multiple GUI elements*/
		fr.setFocusable(true);
	    fr.requestFocusInWindow();

		test.addActionListener(this);
		
		warPanel.setVisible(false);
		prevHex = hexCode.getText();
				
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
		
		// Show needed fields and draw shape
		if(shapes.getSelectedItem() == "Square"   ){
			dimFields[0].setVisible(true);
			dimLabels[0].setVisible(true);
			
		  g.fillRect(fr.getWidth()/2 - dimQty[0]/2, fr.getHeight()/2 - dimQty[0]/2 - topOuterPanel.getHeight(), 
				  dimQty[0], dimQty[0]);
		  
		}else if(shapes.getSelectedItem() == "Circle"){
			dimFields[0].setVisible(true);
			dimLabels[0].setVisible(true);
			
			g.fillOval(fr.getWidth()/2 - dimQty[0]/2, fr.getHeight()/2 - dimQty[0]/2 - topOuterPanel.getHeight(), 
					  dimQty[0], dimQty[0]);	
			  
		}else if(shapes.getSelectedItem() == "Rectangle"){
			dimFields[1].setVisible(true);
			dimLabels[1].setVisible(true);
			
			dimFields[2].setVisible(true);
			dimLabels[2].setVisible(true);
			
			g.fillRect(fr.getWidth()/2 - dimQty[1]/2, fr.getHeight()/2 - dimQty[2]/2 - topOuterPanel.getHeight(), 
					  dimQty[1], dimQty[2]);
			  
		}else if(shapes.getSelectedItem() == "Rounded Rect"){
			dimFields[1].setVisible(true);
			dimLabels[1].setVisible(true);
			
			dimFields[2].setVisible(true);
			dimLabels[2].setVisible(true);
			
			dimFields[3].setVisible(true);
			dimLabels[3].setVisible(true);
			
	  	  g.fillRoundRect(fr.getWidth()/2 - dimQty[1]/2, fr.getHeight()/2 - dimQty[2]/2 - topOuterPanel.getHeight(), 
	  			dimQty[1], dimQty[2], dimQty[3], dimQty[3]);
	  	  
		}else if(shapes.getSelectedItem() == "Polygon (not yet available)"){
			
		}
	}

	
	public void actionPerformed(ActionEvent a) {
		// Defaults
		/*"None", "Yellow", "Brown", "Gray", "Magenta", 
				      "Orange", "Pink", "Cyan", "Purple", "Black", "White"};*/
		if(a.getSource() == colors){
			if(colors.getSelectedItem() == "Yellow"){		 
				redQ.setText  ( Integer.toString(Color.YELLOW.getRed()) );
				greenQ.setText( Integer.toString(Color.YELLOW.getGreen()) );
				blueQ.setText ( Integer.toString(Color.YELLOW.getBlue()) );
				
			} else if(colors.getSelectedItem() == "Brown"){		 
				redQ.setText  ( Integer.toString(165) );
				greenQ.setText( Integer.toString(42) );
				blueQ.setText ( Integer.toString(42) );
				
			} else if(colors.getSelectedItem() == "Gray"){		 
				redQ.setText  ( Integer.toString(Color.GRAY.getRed()) );
				greenQ.setText( Integer.toString(Color.GRAY.getGreen()) );
				blueQ.setText ( Integer.toString(Color.GRAY.getBlue()) );	
				
			} else if(colors.getSelectedItem() == "Magenta"){		 
				redQ.setText  ( Integer.toString(Color.MAGENTA.getRed()) );
				greenQ.setText( Integer.toString(Color.MAGENTA.getGreen()) );
				blueQ.setText ( Integer.toString(Color.MAGENTA.getBlue()) );	
				
			} else if(colors.getSelectedItem() == "Orange"){		 
				redQ.setText  ( Integer.toString(Color.ORANGE.getRed()) );
				greenQ.setText( Integer.toString(Color.ORANGE.getGreen()) );
				blueQ.setText ( Integer.toString(Color.ORANGE.getBlue()) );		
				
			} else if(colors.getSelectedItem() == "Pink"){		 
				redQ.setText  ( Integer.toString(Color.PINK.getRed()) );
				greenQ.setText( Integer.toString(Color.PINK.getGreen()) );
				blueQ.setText ( Integer.toString(Color.PINK.getBlue()) );		
				
			} else if(colors.getSelectedItem() == "Cyan"){		 
				redQ.setText  ( Integer.toString(Color.CYAN.getRed()) );
				greenQ.setText( Integer.toString(Color.CYAN.getGreen()) );
				blueQ.setText ( Integer.toString(Color.CYAN.getBlue()) );		
				
			} else if(colors.getSelectedItem() == "Purple"){		 
				redQ.setText  ( "160" );
				greenQ.setText( "32" );
				blueQ.setText ( "240" );
				
			} else if(colors.getSelectedItem() == "Black"){		 
				redQ.setText  ( Integer.toString(Color.BLACK.getRed()) );
				greenQ.setText( Integer.toString(Color.BLACK.getGreen()) );
				blueQ.setText ( Integer.toString(Color.BLACK.getBlue()) );		
				
			} else if(colors.getSelectedItem() == "White"){		 
				redQ.setText  ( Integer.toString(Color.WHITE.getRed()) );
				greenQ.setText( Integer.toString(Color.WHITE.getGreen()) );
				blueQ.setText ( Integer.toString(Color.WHITE.getBlue()) );	
				
			}
		}
		
		// if(a.getSource() == redQ ||a.getSource() == greenQ|| a.getSource() == blueQ){
		
			// Text to Slider
			// Check here for errors
			int redVal   =  Integer.parseInt(redQ.getText());
			int greenVal =  Integer.parseInt(greenQ.getText());
			int blueVal  =  Integer.parseInt(blueQ.getText());
			
		
			redSlider.setValue(redVal);	
			greenSlider.setValue(greenVal);	
			blueSlider.setValue(blueVal);	
		
		//}else if(a.getSource() == hexCode){
			//System.out.println("Hex to Slider");
			
			
			// Hex to Slider
			// Check here for errors
			
			checkHex();
			int val[] = getRGB(hexCode.getText()); 
			
			System.out.println("RGB:"+val[0] +" "+ val[1] + " " + val[2]);
			
			redSlider.setValue(val[0]);	
			greenSlider.setValue(val[1]);	
			blueSlider.setValue(val[2]);

		//}

		
		// Text to Drawing
		// Check here for errors
		for(int i = 0; i < dimFields.length; i++ )		
			dimQty[i] = Integer.parseInt(dimFields[i].getText());			
		
		// Remove all the fields
		if(a.getSource() instanceof JComboBox){
			  removeAll();			  
		}
		
		
		repaint();
		//prevHex = hexCode.getText();

	}
	
	// Slider to text
	public void stateChanged(ChangeEvent c) {
		
		//if(c.getSource() == redQ ||c.getSource() == greenQ|| c.getSource() == blueQ);
		
		String redVal   = Integer.toString(redSlider.getValue());
		String greenVal = Integer.toString(greenSlider.getValue());
		String blueVal  = Integer.toString(blueSlider.getValue());
		
		
		redQ.setText(redVal);
		greenQ.setText(greenVal);
		blueQ.setText(blueVal);
		
		changeHex();

		repaint();
	}	
	
	public void checkHex(){
		
		String code = hexCode.getText().toLowerCase();	
		String finalHex = "";
		
		// Make sure length of string and first char are appropriate
		// If string too long, shorten it,  else use previous 
		if(code.length() > 7 ){
			System.out.println(code.length());
			String c = code;
			code = c.substring(0, 7);
			warning.setText("Hex Code too Long");
			warPanel.setVisible(true);
		}		
		
	    if(code.length() < 7 || code.charAt(0) != '#'){
			finalHex = prevHex;
			warning.setText("Invalid Hex Code");
			warPanel.setVisible(true);
		}else		
			// Check to make sure all characters are in hex
			for(int ch = 1; ch < code.length(); ch++)		
				if( !(Character.isDigit(code.charAt(ch)) 
						|| code.charAt(ch) == 'a' 					                                    
						|| code.charAt(ch) == 'b'					                                    
						|| code.charAt(ch) == 'c'		                                    
						|| code.charAt(ch) == 'd'                      
						|| code.charAt(ch) == 'e'    
						|| code.charAt(ch) == 'f')  ){				
					finalHex = prevHex;
					warning.setText("Invalid Characters in Hex Code");
					warPanel.setVisible(true);
				}else{
					finalHex = code;
					warPanel.setVisible(false);
				}
		
		hexCode.setText(finalHex);
		//warPanel.setVisible(true);

	}

	
	// Change hex text
	private void changeHex(){
		
		hexCode.setText("#" + hexVal(redSlider.getValue()) + 
				              hexVal(greenSlider.getValue()) + 
				              hexVal(blueSlider.getValue()));

	}
	
	
	// Convert value to hex
	private String hexVal(int v){
		String newVal = Integer.toHexString(v);		
		if(newVal.length() == 1) newVal = "0" + newVal;
		
		return newVal;
	}
	
	//Convert Hex value into array of strings
	private int[] getRGB(String hex){
		char chars[] = hex.toCharArray();
		
		/*
		for(int i =0;i<chars.length;i++)
		  System.out.println(i+": " +chars[i] + " b4");
		for(int i =0;i < c.length;i++)
			  System.out.println(i+": " +c[i]);
		*/
		
	
		int r1 = (int) Long.parseLong(chars[1]+""+chars[2], 16);  // R
		int r2 = (int) Long.parseLong(chars[3]+""+chars[4], 16);  // G
		int r3 = (int) Long.parseLong(chars[5]+""+chars[6], 16);  // B
		
		int c[] = {r1,r2,r3};
		
	
		return c;
	}
	
	public void removeAll(){
		
		for(int i = 0; i < dimFields.length; i++ )	{
			dimFields[i].setVisible(false);
			dimLabels[i].setVisible(false);
			
		}
		
	}
	

	private void loadImages(){
		
		try {
			rIcon = new ImageIcon( ImageIO.read(this.getClass().getResource("pics/red_square.png")) );
			gIcon = new ImageIcon( ImageIO.read(this.getClass().getResource("pics/green_square.png")) );
			bIcon = new ImageIcon( ImageIO.read(this.getClass().getResource("pics/blue_square.png")) );
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		
	}



		

}
=======
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class ShapeInterface extends JComponent implements ActionListener,ChangeListener {

	private JFrame fr;
	private JPanel bottomPanel, topPanel1, topPanel2, topPanel3,  topOuterPanel;
	private JButton test;
	private JComboBox<String> shapes;
	private JComboBox<String> colors;
	private JSlider redSlider, blueSlider, greenSlider;
	private JTextField redQ, greenQ, blueQ, hexCode;
	private JLabel rl, gl, bl;
	
	private JLabel dimLabels[] = {new JLabel("Size: "), 
			                      new JLabel("Width: "), 
			                      new JLabel("Height: "), 
			                      new JLabel("Corner Arc: ")};
	private JTextField dimFields[];
	
	private ImageIcon rIcon, bIcon, gIcon;
	private int dimQty[] = {300,300,300,10};
	//private int shapeWidth, shapeHeight;
	
	public static void main(String[] args) {
		new ShapeInterface();

	}
	
	/*
	 * Remember
	 * 0 - Size
	 * 1-  Width
	 * 2 - Height
	 * 3 - Corner Arc
	 * */
	
	public ShapeInterface(){
		loadImages();
		
		String[] s = {"Square", "Circle","Rectangle", "Rounded Rect", "Polygon"};
		String[] c = {"None", "Yellow", "Brown", "Gray", "Magenta", 
				      "Orange", "Pink", "Cyan", "Purple", "Black", "White"};
		shapes = new JComboBox<String>(s);
		colors = new JComboBox<String>(c);
		shapes.setSelectedIndex(0);
		
		//colors.setSize(50, 100);
		
		fr = new JFrame();
		bottomPanel = new JPanel();
		topPanel2  = new JPanel();
		topPanel1  = new JPanel();
		topPanel3  = new JPanel();
		topOuterPanel  = new JPanel();
		
	    fr.setTitle("Simple Slider");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Close window on exit
	 	fr.setSize(900,900);
		
		test = new JButton("Rotate Image");
		  
		redSlider   = new JSlider(0, 255, 0);
		blueSlider  = new JSlider(0, 255, 0);
		greenSlider = new JSlider(0, 255, 0);		
		rl = new JLabel( "Red", rIcon, 0);
		gl = new JLabel("Green",gIcon, 0);
		bl = new JLabel("Blue", bIcon, 0);
		
		// Different fields for different properties of different shapes
		dimFields = new JTextField[dimLabels.length];
		
		for(int i = 0; i < dimFields.length; i++ ){			  
			dimFields[i] = new JTextField(dimQty[i]+"");	
			dimFields[i].setPreferredSize(   new Dimension(40, 20) );
		}

		// R G B Text fields & Hexcode
		redQ   = new JTextField(  redSlider.getValue() + "");
		greenQ = new JTextField(greenSlider.getValue() + "");
		blueQ  = new JTextField( blueSlider.getValue() + "");
		

		hexCode= new JTextField( "#" + hexVal(redSlider.getValue()) + 
				                       hexVal(greenSlider.getValue()) + 
				                       hexVal(blueSlider.getValue()) );
		
		redQ.setPreferredSize(   new Dimension(40, 20) );
		greenQ.setPreferredSize( new Dimension(40, 20) );
		blueQ.setPreferredSize(  new Dimension(40, 20) );
		hexCode.setPreferredSize(new Dimension(60, 20) );
		
		redQ.setForeground(Color.RED);
		greenQ.setForeground(Color.GREEN);
		greenQ.setBackground(Color.GRAY);
		blueQ.setForeground(Color.BLUE);
		
	 	bottomPanel.setBackground(new Color(217, 217, 217));
		
	 	// Add sliders
	 	bottomPanel.add(rl);
	 	bottomPanel.add(redSlider);
	 	
	 	bottomPanel.add(gl);
	 	bottomPanel.add(greenSlider);
	 	
	 	bottomPanel.add(bl);
	 	bottomPanel.add(blueSlider);
	 	

	 	// Shapes
	 	//topPanel1.setPreferredSize(new Dimension(100,50));
	 	topPanel1.setBackground(new Color(130,138,140));
	 	topPanel1.add(shapes);	 	
	 	topPanel1.add(dimLabels[0]);
	 	topPanel1.add(dimFields[0]);
	 	
		topPanel1.add(dimLabels[1]);
		topPanel1.add(dimFields[1]);
		
		topPanel1.add(dimLabels[2]);
		topPanel1.add(dimFields[2]);
		
		topPanel1.add(dimLabels[3]);
		topPanel1.add(dimFields[3]);
		removeAll();

	 	// Color fields
	 	topOuterPanel.setLayout(new GridLayout(0,1,3,3));
	 	topPanel2.setBackground(Color.WHITE);
	 	topPanel2.add(new JLabel ("Red:") );
	 	topPanel2.add(redQ);
	 	
	 	topPanel2.add(new JLabel ("Green:") );
	 	topPanel2.add(greenQ);
	 	
	 	topPanel2.add(new JLabel ("Blue:") );
	 	topPanel2.add(blueQ);
	 	
	 	//topPanel2.add(new JLabel ("Hex Code: In Progress") );
	 	topPanel2.add(new JLabel ("Hex Code:") );
	 	topPanel2.add(hexCode);
	 	
	 	
	 	// Default colors
	 	/*"None", "Yellow", "Brown", "Gray", "Magenta", 
				      "Orange", "Pink", "Cyan", "Purple", "Black", "White"*/
	 	topPanel3.setBackground(Color.WHITE);
	 	colors.setPreferredSize(new Dimension(100,30));
	 	colors.setBackground(Color.WHITE);
	 	topPanel3.add(new JLabel("Default Color: "));
	 	topPanel3.add(colors);
	 			
		topOuterPanel.add(topPanel1);
		topOuterPanel.add(topPanel2);
		topOuterPanel.add(topPanel3);
	 	
		fr.getContentPane().add(this);    			    
		
		fr.add(topOuterPanel,  BorderLayout.NORTH); 
		fr.add(bottomPanel,  BorderLayout.SOUTH); 

		fr.setVisible(true);		
		
		redSlider.addChangeListener(this);
		greenSlider.addChangeListener(this);
		blueSlider.addChangeListener(this);
		
		shapes.addActionListener(this);
		colors.addActionListener(this);
		redQ.addActionListener(this);
		greenQ.addActionListener(this);
		blueQ.addActionListener(this);
		hexCode.addActionListener(this);
		
		for(int i = 0; i < dimFields.length; i++ )
		  dimFields[i].addActionListener(this);
		  
		  /* VERY IMPORTANT with multiple GUI elements*/
		fr.setFocusable(true);
	    fr.requestFocusInWindow();

		test.addActionListener(this);
				
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
		
		// Show needed fields and draw shape
		if(shapes.getSelectedItem() == "Square"   ){
			dimFields[0].setVisible(true);
			dimLabels[0].setVisible(true);
			
		  g.fillRect(fr.getWidth()/2 - dimQty[0]/2, fr.getHeight()/2 - dimQty[0]/2 - topOuterPanel.getHeight(), 
				  dimQty[0], dimQty[0]);
		  
		}else if(shapes.getSelectedItem() == "Circle"){
			dimFields[0].setVisible(true);
			dimLabels[0].setVisible(true);
			
			g.fillOval(fr.getWidth()/2 - dimQty[0]/2, fr.getHeight()/2 - dimQty[0]/2 - topOuterPanel.getHeight(), 
					  dimQty[0], dimQty[0]);	
			  
		}else if(shapes.getSelectedItem() == "Rectangle"){
			dimFields[1].setVisible(true);
			dimLabels[1].setVisible(true);
			
			dimFields[2].setVisible(true);
			dimLabels[2].setVisible(true);
			
			g.fillRect(fr.getWidth()/2 - dimQty[1]/2, fr.getHeight()/2 - dimQty[2]/2 - topOuterPanel.getHeight(), 
					  dimQty[1], dimQty[2]);
			  
		}else if(shapes.getSelectedItem() == "Rounded Rect"){
			dimFields[1].setVisible(true);
			dimLabels[1].setVisible(true);
			
			dimFields[2].setVisible(true);
			dimLabels[2].setVisible(true);
			
			dimFields[3].setVisible(true);
			dimLabels[3].setVisible(true);
			
	  	  g.fillRoundRect(fr.getWidth()/2 - dimQty[1]/2, fr.getHeight()/2 - dimQty[2]/2 - topOuterPanel.getHeight(), 
	  			dimQty[1], dimQty[2], dimQty[3], dimQty[3]);
	  	  
		}else if(shapes.getSelectedItem() == "Polygon"){
			
		}
	}

	
	public void actionPerformed(ActionEvent a) {
		// Defaults
		/*"None", "Yellow", "Brown", "Gray", "Magenta", 
				      "Orange", "Pink", "Cyan", "Purple", "Black", "White"};*/
		if(a.getSource() == colors){
			if(colors.getSelectedItem() == "Yellow"){		 
				redQ.setText  ( Integer.toString(Color.YELLOW.getRed()) );
				greenQ.setText( Integer.toString(Color.YELLOW.getGreen()) );
				blueQ.setText ( Integer.toString(Color.YELLOW.getBlue()) );
				
			} else if(colors.getSelectedItem() == "Brown"){		 
				redQ.setText  ( Integer.toString(165) );
				greenQ.setText( Integer.toString(42) );
				blueQ.setText ( Integer.toString(42) );
				
			} else if(colors.getSelectedItem() == "Gray"){		 
				redQ.setText  ( Integer.toString(Color.GRAY.getRed()) );
				greenQ.setText( Integer.toString(Color.GRAY.getGreen()) );
				blueQ.setText ( Integer.toString(Color.GRAY.getBlue()) );	
				
			} else if(colors.getSelectedItem() == "Magenta"){		 
				redQ.setText  ( Integer.toString(Color.MAGENTA.getRed()) );
				greenQ.setText( Integer.toString(Color.MAGENTA.getGreen()) );
				blueQ.setText ( Integer.toString(Color.MAGENTA.getBlue()) );	
				
			} else if(colors.getSelectedItem() == "Orange"){		 
				redQ.setText  ( Integer.toString(Color.ORANGE.getRed()) );
				greenQ.setText( Integer.toString(Color.ORANGE.getGreen()) );
				blueQ.setText ( Integer.toString(Color.ORANGE.getBlue()) );		
				
			} else if(colors.getSelectedItem() == "Pink"){		 
				redQ.setText  ( Integer.toString(Color.PINK.getRed()) );
				greenQ.setText( Integer.toString(Color.PINK.getGreen()) );
				blueQ.setText ( Integer.toString(Color.PINK.getBlue()) );		
				
			} else if(colors.getSelectedItem() == "Cyan"){		 
				redQ.setText  ( Integer.toString(Color.CYAN.getRed()) );
				greenQ.setText( Integer.toString(Color.CYAN.getGreen()) );
				blueQ.setText ( Integer.toString(Color.CYAN.getBlue()) );		
				
			} else if(colors.getSelectedItem() == "Purple"){		 
				redQ.setText  ( "160" );
				greenQ.setText( "32" );
				blueQ.setText ( "240" );
				
			} else if(colors.getSelectedItem() == "Black"){		 
				redQ.setText  ( Integer.toString(Color.BLACK.getRed()) );
				greenQ.setText( Integer.toString(Color.BLACK.getGreen()) );
				blueQ.setText ( Integer.toString(Color.BLACK.getBlue()) );		
				
			} else if(colors.getSelectedItem() == "White"){		 
				redQ.setText  ( Integer.toString(Color.WHITE.getRed()) );
				greenQ.setText( Integer.toString(Color.WHITE.getGreen()) );
				blueQ.setText ( Integer.toString(Color.WHITE.getBlue()) );	
				
			}
		}
		
		// if(a.getSource() == redQ ||a.getSource() == greenQ|| a.getSource() == blueQ){
		
			// Text to Slider
			// Check here for errors
			int redVal   =  Integer.parseInt(redQ.getText());
			int greenVal =  Integer.parseInt(greenQ.getText());
			int blueVal  =  Integer.parseInt(blueQ.getText());
			
		
			redSlider.setValue(redVal);	
			greenSlider.setValue(greenVal);	
			blueSlider.setValue(blueVal);	
		
		//}else if(a.getSource() == hexCode){
			System.out.println("Hex to Slider");
			
			// Hex to Slider
			// Check here for errors
			int val[] = getRGB(hexCode.getText()); 
			
			System.out.println("RGB:"+val[0] +" "+ val[1] + " " + val[2]);
			
			redSlider.setValue(val[0]);	
			greenSlider.setValue(val[1]);	
			blueSlider.setValue(val[2]);

		//}

		
		// Text to Drawing
		// Check here for errors
		for(int i = 0; i < dimFields.length; i++ )		
			dimQty[i] = Integer.parseInt(dimFields[i].getText());			
		
		// Remove all the fields
		if(a.getSource() instanceof JComboBox){
			  removeAll();			  
		}
		
		
		repaint();

	}
	
	// Slider to text
	public void stateChanged(ChangeEvent c) {
		
		//if(c.getSource() == redQ ||c.getSource() == greenQ|| c.getSource() == blueQ);
		
		String redVal   = Integer.toString(redSlider.getValue());
		String greenVal = Integer.toString(greenSlider.getValue());
		String blueVal  = Integer.toString(blueSlider.getValue());
		
		
		redQ.setText(redVal);
		greenQ.setText(greenVal);
		blueQ.setText(blueVal);
		
		changeHex();

		repaint();
	}	

	
	// Change hex text
	private void changeHex(){
		
		hexCode.setText("#" + hexVal(redSlider.getValue()) + 
				              hexVal(greenSlider.getValue()) + 
				              hexVal(blueSlider.getValue()));

	}
	
	// Convert value to hex
	private String hexVal(int v){
		String newVal = Integer.toHexString(v);		
		if(newVal.length() == 1) newVal = "0" + newVal;
		
		return newVal;
	}
	
	//Convert Hex value into array of strings
	private int[] getRGB(String hex){
		char chars[] = hex.toCharArray();
		
		/*
		for(int i =0;i<chars.length;i++)
		  System.out.println(i+": " +chars[i] + " b4");
		for(int i =0;i < c.length;i++)
			  System.out.println(i+": " +c[i]);
		*/
		
	
		int r1 = (int) Long.parseLong(chars[1]+""+chars[2], 16);  // R
		int r2 = (int) Long.parseLong(chars[3]+""+chars[4], 16);  // G
		int r3 = (int) Long.parseLong(chars[5]+""+chars[6], 16);  // B
		
		int c[] = {r1,r2,r3};
		

		
		return c;
	}
	
	public void removeAll(){
		
		for(int i = 0; i < dimFields.length; i++ )	{
			dimFields[i].setVisible(false);
			dimLabels[i].setVisible(false);
			
		}
		
	}
	

	private void loadImages(){
		
		try {
			rIcon = new ImageIcon( ImageIO.read(this.getClass().getResource("pics/red_square.png")) );
			gIcon = new ImageIcon( ImageIO.read(this.getClass().getResource("pics/green_square.png")) );
			bIcon = new ImageIcon( ImageIO.read(this.getClass().getResource("pics/blue_square.png")) );
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		
	}



		

}
>>>>>>> ae432b12449cfa992443fcf255288a14ff4674c7
