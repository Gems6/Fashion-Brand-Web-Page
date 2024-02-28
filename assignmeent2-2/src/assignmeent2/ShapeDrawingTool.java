package assignmeent2;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class ShapeDrawingTool {
	
	 private static final String filename1 = "src/assignment2/ShapeDrawingTool.dat";

	 //save
	private void saveDrawing() {
		 if (shapelndex >= 1) {
		        MyShape lastShape = shapes[(shapelndex - 1) % MAX_SHAPE];

		        JOptionPane.showMessageDialog(frame, "Drawing successfully saved!", "Message", JOptionPane.INFORMATION_MESSAGE);
		        clearDrawingArea();
		    } else {
		        JOptionPane.showMessageDialog(frame, "No shape to save!", "Message", JOptionPane.ERROR_MESSAGE);
		    }
	}
	
	//load, an error somewhere
	private void loadDrawing() {
		  try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename1))) {
		        shapes = (MyShape[]) ois.readObject();
		        shapelndex = shapes.length;
		        frame.repaint();
		        JOptionPane.showMessageDialog(frame, "Drawing successfully loaded!", "Message", JOptionPane.INFORMATION_MESSAGE);
		    } catch (Exception e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(frame, "Error loading drawing!", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		
	}
	//clear space for new
	
	private void clearDrawingArea() {
	    for (int i = 0; i < MAX_SHAPE; i++) {
	        shapes[i] = null;
	    }
	    shapelndex = 0;
	    frame.repaint();
	}
	
	//Ui shit
	private static final int MAX_SHAPE = 5;
	private static final int WIDTH = 800; 
	private static final int HEIGHT = 600; 
	private static final String[] menuItems = {"Big", "Medium", "Small"};
	private static final String filename = "myDrawingTool.dat";
	private JFrame frame;
	private MyShape[] shapes; 
	private int shapelndex; 
	private Color color;
	private JComboBox<String> sizeMenu;
	private JRadioButton circle;
	private JRadioButton rectangle;
	
	public ShapeDrawingTool() {
		shapes = new MyShape[MAX_SHAPE];
		for(int i=0; i<MAX_SHAPE; i++) {
		shapes[i] = null;
		}
		shapelndex = 0;
		createDrawingWindow();
		}
	
	//drawing window
		private void createDrawingWindow() {
		frame = new JFrame("Shape Drawing Tool");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//menu bar
		JMenuBar menu = createMenuBar();
		JPanel selection = createSelectionArea();
		JPanel drawing = createDrawingArea();
		frame.setJMenuBar(menu);
		frame.add(selection, BorderLayout.NORTH);
		frame.add(drawing, BorderLayout.CENTER);
		frame.setVisible(true);
		}
		private JMenuBar createMenuBar() {
		JMenuBar menu = new JMenuBar();	
			JMenu file = new JMenu("file");
			JMenuItem save = new JMenuItem ("Save");
			JMenuItem load = new JMenuItem ("Load");
			
			file.add(save);
			file.add(load);
			
			save.addActionListener(e -> {
		        saveDrawing();
		    });
			load.addActionListener(e -> {
		        loadDrawing();
		    });
		    menu.add(file);
			return menu;
		}
		
		//jpanel for selection area
			private JPanel createSelectionArea() {
			JPanel selection = new JPanel();
			Border title = BorderFactory.createTitledBorder("Selection Area");
			selection.setBorder(title);
			 
			//buttons for shape selection
			circle = new JRadioButton("circle");
			rectangle = new JRadioButton("rectangle");
			ButtonGroup shapeType = new ButtonGroup();
			
			shapeType.add(circle);
			shapeType.add(rectangle);
			circle.setSelected(true); 
			JLabel shapeText = new JLabel("Shape");
			JPanel shapePanel = new JPanel();
			shapePanel.add(shapeText);
			shapePanel.add(circle);
			shapePanel.add(rectangle);
			
			
			JLabel colorText = new JLabel("Color");
			JLabel red = new JLabel ("RED");
			red.setOpaque(true);
			red.setBackground(Color.RED);
			red.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			JLabel yellow = new JLabel ("YELLOW");
			yellow.setOpaque(true);
			yellow.setBackground(Color.YELLOW);
			yellow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			JLabel blue = new JLabel ("BLUE");
			blue.setOpaque(true);
			blue.setBackground(Color.BLUE);
			blue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			
			JPanel colorPanel = new JPanel();
			colorPanel.add(colorText);
			colorPanel.add(red);
			colorPanel.add(yellow);
			colorPanel.add(blue);
			color = red.getBackground ();
			
			
			red.addMouseListener(new MouseListener() {
				@Override
				public void mousePressed (MouseEvent e) {
				color=red.getBackground();
			}
				
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
			});
			
		yellow.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				color= yellow.getBackground();
			}
			
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
		});
		
		blue.addMouseListener(new MouseListener () {
			@Override
			public void mousePressed(MouseEvent e) {
				color= blue.getBackground();
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		JLabel sizeText = new JLabel("Size");
		sizeMenu = new JComboBox<String>(menuItems);
		JPanel sizePanel = new JPanel();
		sizePanel.add(sizeText);
		sizePanel.add(sizeMenu);
		selection.setLayout(new BoxLayout(selection, BoxLayout.Y_AXIS));
		selection.add(shapePanel);
		selection.add(colorPanel);
		selection.add(sizePanel);
		return selection;
			
			}
			private JPanel createDrawingArea() {
				JPanel draw = new JPanel() {
				@Override
				public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				for(int i=0; i<MAX_SHAPE; i++) {
				if(shapes[i] != null) {
				g2.setColor(shapes[i].getColor());
				g2.fill(shapes[i].getShape());
				}
				}
				}
				};
				Border title = BorderFactory.createTitledBorder("Draw Area");
				draw.setBorder(title);
				draw.addMouseListener(new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
			
				int shapeWidth;
				int shapeHeight;
				if(sizeMenu.getSelectedItem().equals(menuItems[0])) {
					shapeWidth=WIDTH/4;
					shapeHeight=HEIGHT/4;
				}
			
				else if (sizeMenu.getSelectedItem().equals(menuItems[1])) {
					shapeWidth =WIDTH/8;
					shapeHeight= HEIGHT/8;
				}
				
				else {
					shapeWidth=WIDTH/12;
					shapeHeight = HEIGHT/12;
				}
				
				if(rectangle.isSelected()) {
				shapes[shapelndex] = new MyShape(new Rectangle(e.getX(), e.getY(), shapeWidth, shapeHeight), color);
				}
				else {
				shapes[shapelndex] = new MyShape(new Ellipse2D.Double(e.getX(), e.getY(), shapeWidth, shapeHeight), color);
				}
				shapelndex++;
				if(shapelndex >= MAX_SHAPE) {
				shapelndex = shapelndex - MAX_SHAPE;
				}
				frame.repaint();
				}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				
				});
				return draw;
				}
			}
				
				
			
	