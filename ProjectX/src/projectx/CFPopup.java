package projectx;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.lang.instrument.ClassFileTransformer;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;

public class CFPopup extends JFrame {

	private String clientName;
	private String clientAddress;
	private int _timeSlice = 50;  // update every 50 milliseconds
	private Timer _timer ;
    public TweenManager manager;
	
	
	
	public CFPopup(String name, String address, TweenManager tweenManager){
	 clientName = name;
	 clientAddress = address;
	 setSize(321, 89);
	    //setBounds(-321, 0, 321, 89);
		setTitle("Project-X");
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0,0,0,0));
		//setOpacity(0);
		//setResizable(false); //frame jumps because of this command 
         setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBackground(new Color(0,0,0,0));
		//contentPane.setBackground(new Color(51, 204, 255));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel deviceName = new JLabel(name);
		deviceName.setBounds(99, 51, 135, 16);
		contentPane.add(deviceName);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setOpaque(true);
		backgroundImage.setIcon(new ImageIcon(CFPopup.class.getResource("/projectx/Resources/TabletConnectedPlugged.png")));
		backgroundImage.setBounds(0, 0, 321, 89);
		backgroundImage.setBackground(new Color(0,0,0,0));
		contentPane.add(backgroundImage);
		setLocation(-321,100);
		setVisible(true);
		
		System.out.println("bounds: " + getBounds());
		Tween.registerAccessor(CFPopup.class, new CFPopupAccessor());
		
	    
		Timeline.createSequence()
	    .push(Tween.to(this, CFPopupAccessor.POSITION_X, 0.5f).target(0))
	    .pushPause(5.0f)
	    .push(Tween.to(this, CFPopupAccessor.POSITION_X, 0.5f).target(-321))
	    .start(tweenManager);
		
		

	}
	
	
	
       static public void incoming(String name, String address, TweenManager manager){
		CFPopup popup = new CFPopup(name, address, manager);
	}



public void setX(float f) {
	// TODO Auto-generated method stub
	System.out.println(f);
	setLocation((int)f, getLocation().y);
}



public void setY(float f) {
	// TODO Auto-generated method stub
	setLocation(getLocation().x, (int)f);

}


}
