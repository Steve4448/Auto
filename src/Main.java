// Steve4448.
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main implements ActionListener, WindowListener {
	public JFrame mainFrame;

	public static void main(String args[]) {
		new Main();
	}

	public Main() {
		mainFrame = new JFrame("Steve4448");
		Container frameContainer = mainFrame.getContentPane();
		frameContainer.setLayout(new FlowLayout());

		JLabel hiddenSpacingLabel[] = new JLabel[2];
		JLabel descriptionLabel = new JLabel("What would you like to use?");
		JButton autoClickButton = new JButton("Auto clicker");
		JButton autoTypeButton = new JButton("Auto typer");
		for(int i = 0; i < 2; i++) { // Setting up hidden spacer labels.
			hiddenSpacingLabel[i] = new JLabel("");
			hiddenSpacingLabel[i].setPreferredSize(new Dimension(350, 5));
		}

		frameContainer.add(descriptionLabel);
		frameContainer.add(hiddenSpacingLabel[0]);
		frameContainer.add(autoClickButton);
		frameContainer.add(hiddenSpacingLabel[1]);
		frameContainer.add(autoTypeButton);

		mainFrame.setSize(350, 150);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);

		mainFrame.addWindowListener(this);
		autoClickButton.addActionListener(this);
		autoTypeButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand.equalsIgnoreCase("auto clicker"))
			new AutoClicker();
		if(actionCommand.equalsIgnoreCase("auto typer"))
			new AutoTyper();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		mainFrame.dispose();
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