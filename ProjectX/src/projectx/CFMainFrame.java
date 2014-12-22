package projectx;


import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenManager;

import java.awt.Component;






public class CFMainFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JLabel statusLabel;
    private JButton startButton;
    private JButton minimizeButton;
    private JButton settingButton;
    private CFService service;
    private JLabel backbgroundImage;
    private PopupMenu popup;
    private  TrayIcon trayIcon;
    private  SystemTray tray;
    private boolean isMinimized = false;
    private  MenuItem showApp;
    public static TweenManager tweenManager = new TweenManager();
    public boolean running;
	/**
	 * Launch the application.
	 */
    
    
    /*
    public class CFMainPanel extends JPanel{
    	
    	
		private static final long serialVersionUID = 1L;

		
    	@Override
        protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            System.out.println("WTFFFf");
            try {
    			g.drawImage(ImageIO.read(new File("/Resources/Icons/AppBackground.png")), 0, 0, null);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} // see javadoc for more info on the parameters            
        }
    }
    */
    
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CFMainFrame frame = new CFMainFrame();
					CFTools.log(frame.getBounds().toString());
					frame.setFocusable(true);
					frame.setVisible(true);
					frame.requestFocus();
					CFTools.log(frame.getBounds().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CFMainFrame() {
		
		
		setSize(197, 216);
		setTitle("Project-X");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); //frame jumps because of this command 
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(51, 204, 255));
		contentPane.setLayout(null);
		
	//	setContentPane(new JLabel(new ImageIcon(CFMainFrame.class.getResource("/projectx/Resources/AppBackground.png"))));
		
		setUndecorated(true);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int x = (int)rect.getMinX();
		int y = (int)rect.getMaxY()-getHeight();
		setLocation(x,y);
		

	    
		startButton = new JButton("");
		startButton.setBorder(null);
		startButton.setIcon(new ImageIcon(CFMainFrame.class.getResource("/projectx/Resources/ConnectButton.png")));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setBounds(59, 66, 82, 83);
		startButton.addActionListener(this);
		contentPane.add(startButton);
		
	    statusLabel = new JLabel("Server Stopped");
	    statusLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setBounds(10, 161, 181, 28);
		contentPane.add(statusLabel);
		
		
		
		setContentPane(contentPane);
		
		minimizeButton = new JButton("");
		
		minimizeButton.setIcon(new ImageIcon(CFMainFrame.class.getResource("/projectx/Resources/MinimizeButton.png")));
		minimizeButton.setBounds(10, 10, 18, 5);
		minimizeButton.setOpaque(false);
		minimizeButton.setContentAreaFilled(false);
		minimizeButton.setBorderPainted(false);
		minimizeButton.addActionListener(this);

		contentPane.add(minimizeButton);
		
	    settingButton = new JButton("");
		settingButton.setIcon(new ImageIcon(CFMainFrame.class.getResource("/projectx/Resources/SettingButton.png")));
		settingButton.setBounds(175, 10, 16, 16);
		settingButton.setOpaque(false);
		settingButton.setContentAreaFilled(false);
		settingButton.setBorderPainted(false);
		settingButton.addActionListener(this);
		contentPane.add(settingButton);
		
		backbgroundImage = new JLabel("");
		backbgroundImage.setIcon(new ImageIcon(CFMainFrame.class.getResource("/projectx/Resources/AppBackground.png")));
		backbgroundImage.setBounds(0, 0, 197, 216);
		contentPane.add(backbgroundImage);
		running = true;

		
		
		new Thread(new Runnable() {
		    private long lastMillis = -1;

		    @Override
		    public void run() {
		        while (running) {
		            if (lastMillis > 0) {
		                long currentMillis = System.currentTimeMillis();
		                final float delta = (currentMillis - lastMillis) / 1000f;
		                tweenManager.update(delta);
		                new Thread(new Runnable() {
		                    @Override public void run() {
		                   //??
		                    }
		                });

		                lastMillis = currentMillis;
		            } else {
		                lastMillis = System.currentTimeMillis();
		            }

		            try {
		                Thread.sleep(1000/60);
		                
		            } catch(InterruptedException ex) {
		            }
		        }
		    }
		}).start();
	}

	public void actionPerformed(ActionEvent e) {
		//decide which button was pressed.
		
		if((e.getSource() instanceof JButton)){ //just buttons
			
			if(e.getSource().equals(startButton)){
				CFTools.log("Start");
				if(service == null){
			   try {
				   CFTools.log("fuck");
				service = new CFService(tweenManager);
				service.start();
			} catch (IOException e1) {
				CFTools.log("Couldn't start serversocket(0)");
				e1.printStackTrace();
			}
				statusLabel.setText("Server Started");
				//we should register a server.
				}
			}
			
			if(e.getSource().equals(minimizeButton)){
		     minimizeToTray();
			}
			if(e.getSource().equals(settingButton)){
			     CFPopup.incoming("dude", "wtf", tweenManager);
			     System.out.println("WTF OMFG");
				}
			
			/*
	       if(e.getSource().equals(stopButton)){
	    	   CFTools.log("Stop");
				statusLabel.setText("Server Stopped");

			}
	    */
			
		}else{
			if(e.getSource().equals(showApp)){
			     setVisible(true);
				}
		}
		
	}
	
	public void minimizeToTray(){
		   //Check the SystemTray is supported
		if(popup == null){
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
          popup = new PopupMenu();
          trayIcon = new TrayIcon(new ImageIcon(CFMainFrame.class.getResource("/projectx/Resources/TrayIcon.png")).getImage());
          tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("About");
        Menu displayMenu = new Menu("Display");
        showApp = new MenuItem("Show App");

        showApp.addActionListener(this);
        MenuItem exitItem = new MenuItem("Exit");
       
        //Add components to pop-up menu
        popup.add(aboutItem);       
        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(showApp);
        popup.add(exitItem);
       
        trayIcon.setPopupMenu(popup);
       
        try {
            tray.add(trayIcon);
        } catch (AWTException e1) {
            System.out.println("TrayIcon could not be added.");
        }
		}
        setVisible(false);
	}
	
	
	
}

