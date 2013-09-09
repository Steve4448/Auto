import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AutoClicker extends JFrame implements ActionListener, WindowListener {
	public JButton startStopAutoClickerButton;
	public JTextField delayTextField;
	public JLabel clicksDoneField;
	public boolean autoClick = false;
	public long millisecondsToSleep = 0L;
	public Robot autoTypeBot;
	public Thread autoClickThread = null;
	public int totalClicks = 0;
	public Object lock = new Object();

	public AutoClicker() {
		super("Auto Clicker");
		setLayout(new FlowLayout());
		JLabel description = new JLabel("Select the delay per click and press start.");
		JLabel delayLabel = new JLabel("Delay (Milliseconds): ");
		clicksDoneField = new JLabel("Total Clicks: 0");
		delayTextField = new JTextField("500");
		delayTextField.setPreferredSize(new Dimension(100, 25));
		startStopAutoClickerButton = new JButton("Start");
		JLabel hiddenSpacingLabel[] = new JLabel[3];
		for(int i = 0; i < 3; i++) { // Setting up hidden spacer labels. -- hacky
			hiddenSpacingLabel[i] = new JLabel("");
			hiddenSpacingLabel[i].setPreferredSize(new Dimension(400, 5));
		}

		add(description);
		add(hiddenSpacingLabel[0]);
		add(delayLabel);
		add(delayTextField);
		add(hiddenSpacingLabel[1]);
		add(startStopAutoClickerButton);
		add(hiddenSpacingLabel[2]);
		add(clicksDoneField);

		setSize(400, 200);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		addWindowListener(this);
		startStopAutoClickerButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand.equalsIgnoreCase("start")) {
			try {
				millisecondsToSleep = Long.parseLong(delayTextField.getText());
			} catch(Exception _e) {
				JOptionPane.showMessageDialog(this, "Error", "Invalid number arguments, please use a numerical value for the milliseconds between clicks.", JOptionPane.ERROR_MESSAGE);
				return;
			}
			autoClick = true;
			autoClickThread = new Thread() {
				@Override
				public void run() {
					try {
						autoTypeBot = new Robot();
						Thread.sleep(1000L); // Gives a bit of a startup delay.
						while(autoClick)
							synchronized(lock) {
								Thread.sleep(millisecondsToSleep);
								autoTypeBot.mousePress(InputEvent.BUTTON1_MASK);
								Thread.sleep(5);
								autoTypeBot.mouseRelease(InputEvent.BUTTON1_MASK);
								totalClicks++;
								clicksDoneField.setText("Total Clicks: " + totalClicks);
							}
					} catch(InterruptedException e) {
						stopThread();
						return;
					} catch(Exception e) {
						stopThread();
						e.printStackTrace();
						return;
					}
					startStopAutoClickerButton.setText("Start");
				}

				public void stopThread() {
					autoClick = false;
					millisecondsToSleep = 0L;
					totalClicks = 0;
					startStopAutoClickerButton.setText("Start");
				}
			};
			autoClickThread.start();
			startStopAutoClickerButton.setText("Stop");
		} else if(actionCommand.equalsIgnoreCase("stop"))
			stop();
	}

	public void stop() {
		autoClick = false;
		millisecondsToSleep = 0L;
		if(autoClickThread != null)
			autoClickThread.interrupt();
		startStopAutoClickerButton.setText("Start");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		stop();
		dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
