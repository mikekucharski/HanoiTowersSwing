/**
 * @author Mike Kucharski
 * Fall 2012 | COMP 285 Object Oriented Programming
 */

package towersofhanoi;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HowToPlay extends JFrame {
	
	//components necessary
	JTextArea howToPlay;
	JLabel lblTitle;
	JPanel panel;
	
	public HowToPlay()
	{
		initUI();
	}

	//initialize all components in the right location
	private void initUI()
	{	
		panel = new JPanel();
		panel.setBounds(0, 0, 646, 295);
        panel.setBackground(new Color(114, 226, 126));
        setResizable(false);
        getContentPane().setLayout(null);
        panel.setLayout(null);

        lblTitle = new JLabel("How To Play");
        lblTitle.setForeground(new Color(51, 51, 204));
        lblTitle.setBounds(178, 0, 279, 50);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 36));
        panel.add(lblTitle);
        
        howToPlay = new JTextArea();
        howToPlay.setEditable(false);
        howToPlay.setForeground(new Color(102, 0, 0));
        howToPlay.setBounds(28, 48, 596, 236);
        howToPlay.setLineWrap(true);
        howToPlay.setWrapStyleWord(true);
        howToPlay.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        howToPlay.setBackground(new Color(114, 226, 126));
        howToPlay.setText( "  " + 
        		"Welcome to the Towers of Hanoi puzzle!  The object of the game is to move all the discs from the left peg to the " +
        		"right peg.  The rules of the game state that only one disc may be moved at a time. Also, a bigger disc cannot be placed on top of a smaller disk.  " +
        		"Click one of the three move buttons to move a disk.  Try to move all the discs using the smallest number of moves possible.\n\n" +
        		"  If you get stuck you can always click \"Reset\" to start over or \"New Disks\" to change the number of disks you start with.  " +
        		"Solving this puzzle in the fewest number of moves is challenging.  If you would like to see it done simply click \"Show me how to solve\"");
        
	    panel.add(howToPlay);
	    
	    getContentPane().add(panel);
        
        setTitle("How To Play");
        setSize(652, 323);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}