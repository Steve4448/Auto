import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AutoTyper extends JFrame implements ActionListener, WindowListener {
	public JButton startStopAutoTyperButton;
	public JTextField delayTextField;
	public JTextField messageToAutoTypeTextField;
	public JLabel messagesTypedLabel;
	public boolean autoType = false;
	public long millisecondsToSleep = 0L;
	public String messageToAutoType;
	public Robot autoTypeBot;
	public Thread autoTypeThread = null;
	public int totalMessagesTyped = 0;
	public Object lock = new Object();

	public AutoTyper() {
		super("Auto Typer");
		setLayout(new FlowLayout());
		JLabel description = new JLabel("Type in your message, set the delay, press start.");
		messageToAutoTypeTextField = new JTextField("Message to auto type.");
		messageToAutoTypeTextField.setPreferredSize(new Dimension(380, 25));
		messagesTypedLabel = new JLabel("Messages Typed: 0");
		JLabel delayLabel = new JLabel("Delay (Milliseconds): ");
		delayTextField = new JTextField("500");
		delayTextField.setPreferredSize(new Dimension(100, 25));
		startStopAutoTyperButton = new JButton("Start");
		JLabel hiddenSpacingLabel[] = new JLabel[3];
		for(int i = 0; i < 3; i++) { // Setting up hidden spacer labels. -- hacky
			hiddenSpacingLabel[i] = new JLabel("");
			hiddenSpacingLabel[i].setPreferredSize(new Dimension(400, 5));
		}

		add(description);
		add(hiddenSpacingLabel[0]);
		add(messageToAutoTypeTextField);
		add(delayLabel);
		add(delayTextField);
		add(hiddenSpacingLabel[1]);
		add(startStopAutoTyperButton);
		add(hiddenSpacingLabel[2]);
		add(messagesTypedLabel);

		setSize(400, 200);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		addWindowListener(this);
		startStopAutoTyperButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand.equalsIgnoreCase("start")) {
			try {
				millisecondsToSleep = Long.parseLong(delayTextField.getText());
			} catch(Exception _e) {
				messageToAutoTypeTextField.setText("Please only use numbers in the delay area.");
				return;
			}
			if(messageToAutoTypeTextField.getText().equals(""))
				return;
			messageToAutoType = messageToAutoTypeTextField.getText();
			autoType = true;
			autoTypeThread = new Thread() {
				@Override
				public void run() {
					try {
						autoTypeBot = new Robot();
						Thread.sleep(1000L); // Gives a bit of a startup delay.
						while(autoType)
							synchronized(lock) {
								Thread.sleep(millisecondsToSleep);
								for(int i = 0; i < messageToAutoType.length(); i++)
									if(messageToAutoType.substring(i, i + 1).equals("0")) {
										autoTypeBot.keyPress(KeyEvent.VK_0);
										autoTypeBot.keyRelease(KeyEvent.VK_0);
									} else if(messageToAutoType.substring(i, i + 1).equals(")")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_0);
										autoTypeBot.keyRelease(KeyEvent.VK_0);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("1")) {
										autoTypeBot.keyPress(KeyEvent.VK_1);
										autoTypeBot.keyRelease(KeyEvent.VK_1);
									} else if(messageToAutoType.substring(i, i + 1).equals("!")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_1);
										autoTypeBot.keyRelease(KeyEvent.VK_1);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("2")) {
										autoTypeBot.keyPress(KeyEvent.VK_2);
										autoTypeBot.keyRelease(KeyEvent.VK_2);
									} else if(messageToAutoType.substring(i, i + 1).equals("@")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_2);
										autoTypeBot.keyRelease(KeyEvent.VK_2);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("3")) {
										autoTypeBot.keyPress(KeyEvent.VK_3);
										autoTypeBot.keyRelease(KeyEvent.VK_3);
									} else if(messageToAutoType.substring(i, i + 1).equals("#")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_3);
										autoTypeBot.keyRelease(KeyEvent.VK_3);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("4")) {
										autoTypeBot.keyPress(KeyEvent.VK_4);
										autoTypeBot.keyRelease(KeyEvent.VK_4);
									} else if(messageToAutoType.substring(i, i + 1).equals("$")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_4);
										autoTypeBot.keyRelease(KeyEvent.VK_4);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("5")) {
										autoTypeBot.keyPress(KeyEvent.VK_5);
										autoTypeBot.keyRelease(KeyEvent.VK_5);
									} else if(messageToAutoType.substring(i, i + 1).equals("%")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_5);
										autoTypeBot.keyRelease(KeyEvent.VK_5);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("6")) {
										autoTypeBot.keyPress(KeyEvent.VK_6);
										autoTypeBot.keyRelease(KeyEvent.VK_6);
									} else if(messageToAutoType.substring(i, i + 1).equals("^")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_6);
										autoTypeBot.keyRelease(KeyEvent.VK_6);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("7")) {
										autoTypeBot.keyPress(KeyEvent.VK_7);
										autoTypeBot.keyRelease(KeyEvent.VK_7);
									} else if(messageToAutoType.substring(i, i + 1).equals("&")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_7);
										autoTypeBot.keyRelease(KeyEvent.VK_7);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("8")) {
										autoTypeBot.keyPress(KeyEvent.VK_8);
										autoTypeBot.keyRelease(KeyEvent.VK_8);
									} else if(messageToAutoType.substring(i, i + 1).equals("*")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_8);
										autoTypeBot.keyRelease(KeyEvent.VK_8);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("9")) {
										autoTypeBot.keyPress(KeyEvent.VK_9);
										autoTypeBot.keyRelease(KeyEvent.VK_9);
									} else if(messageToAutoType.substring(i, i + 1).equals("(")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_9);
										autoTypeBot.keyRelease(KeyEvent.VK_9);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("a")) {
										autoTypeBot.keyPress(KeyEvent.VK_A);
										autoTypeBot.keyRelease(KeyEvent.VK_A);
									} else if(messageToAutoType.substring(i, i + 1).equals("A")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_A);
										autoTypeBot.keyRelease(KeyEvent.VK_A);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("b")) {
										autoTypeBot.keyPress(KeyEvent.VK_B);
										autoTypeBot.keyRelease(KeyEvent.VK_B);
									} else if(messageToAutoType.substring(i, i + 1).equals("B")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_B);
										autoTypeBot.keyRelease(KeyEvent.VK_B);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("c")) {
										autoTypeBot.keyPress(KeyEvent.VK_C);
										autoTypeBot.keyRelease(KeyEvent.VK_C);
									} else if(messageToAutoType.substring(i, i + 1).equals("C")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_C);
										autoTypeBot.keyRelease(KeyEvent.VK_C);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("d")) {
										autoTypeBot.keyPress(KeyEvent.VK_D);
										autoTypeBot.keyRelease(KeyEvent.VK_D);
									} else if(messageToAutoType.substring(i, i + 1).equals("D")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_D);
										autoTypeBot.keyRelease(KeyEvent.VK_D);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("e")) {
										autoTypeBot.keyPress(KeyEvent.VK_E);
										autoTypeBot.keyRelease(KeyEvent.VK_E);
									} else if(messageToAutoType.substring(i, i + 1).equals("E")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_E);
										autoTypeBot.keyRelease(KeyEvent.VK_E);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("f")) {
										autoTypeBot.keyPress(KeyEvent.VK_F);
										autoTypeBot.keyRelease(KeyEvent.VK_F);
									} else if(messageToAutoType.substring(i, i + 1).equals("F")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_F);
										autoTypeBot.keyRelease(KeyEvent.VK_F);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("g")) {
										autoTypeBot.keyPress(KeyEvent.VK_G);
										autoTypeBot.keyRelease(KeyEvent.VK_G);
									} else if(messageToAutoType.substring(i, i + 1).equals("G")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_G);
										autoTypeBot.keyRelease(KeyEvent.VK_G);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("h")) {
										autoTypeBot.keyPress(KeyEvent.VK_H);
										autoTypeBot.keyRelease(KeyEvent.VK_H);
									} else if(messageToAutoType.substring(i, i + 1).equals("H")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_H);
										autoTypeBot.keyRelease(KeyEvent.VK_H);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("i")) {
										autoTypeBot.keyPress(KeyEvent.VK_I);
										autoTypeBot.keyRelease(KeyEvent.VK_I);
									} else if(messageToAutoType.substring(i, i + 1).equals("I")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_I);
										autoTypeBot.keyRelease(KeyEvent.VK_I);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("j")) {
										autoTypeBot.keyPress(KeyEvent.VK_J);
										autoTypeBot.keyRelease(KeyEvent.VK_J);
									} else if(messageToAutoType.substring(i, i + 1).equals("J")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_J);
										autoTypeBot.keyRelease(KeyEvent.VK_J);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("k")) {
										autoTypeBot.keyPress(KeyEvent.VK_K);
										autoTypeBot.keyRelease(KeyEvent.VK_K);
									} else if(messageToAutoType.substring(i, i + 1).equals("K")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_K);
										autoTypeBot.keyRelease(KeyEvent.VK_K);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("l")) {
										autoTypeBot.keyPress(KeyEvent.VK_L);
										autoTypeBot.keyRelease(KeyEvent.VK_L);
									} else if(messageToAutoType.substring(i, i + 1).equals("L")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_L);
										autoTypeBot.keyRelease(KeyEvent.VK_L);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("m")) {
										autoTypeBot.keyPress(KeyEvent.VK_M);
										autoTypeBot.keyRelease(KeyEvent.VK_M);
									} else if(messageToAutoType.substring(i, i + 1).equals("M")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_M);
										autoTypeBot.keyRelease(KeyEvent.VK_M);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("n")) {
										autoTypeBot.keyPress(KeyEvent.VK_N);
										autoTypeBot.keyRelease(KeyEvent.VK_N);
									} else if(messageToAutoType.substring(i, i + 1).equals("N")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_N);
										autoTypeBot.keyRelease(KeyEvent.VK_N);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("o")) {
										autoTypeBot.keyPress(KeyEvent.VK_O);
										autoTypeBot.keyRelease(KeyEvent.VK_O);
									} else if(messageToAutoType.substring(i, i + 1).equals("O")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_O);
										autoTypeBot.keyRelease(KeyEvent.VK_O);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("p")) {
										autoTypeBot.keyPress(KeyEvent.VK_P);
										autoTypeBot.keyRelease(KeyEvent.VK_P);
									} else if(messageToAutoType.substring(i, i + 1).equals("P")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_P);
										autoTypeBot.keyRelease(KeyEvent.VK_P);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("q")) {
										autoTypeBot.keyPress(KeyEvent.VK_Q);
										autoTypeBot.keyRelease(KeyEvent.VK_Q);
									} else if(messageToAutoType.substring(i, i + 1).equals("Q")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_Q);
										autoTypeBot.keyRelease(KeyEvent.VK_Q);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("r")) {
										autoTypeBot.keyPress(KeyEvent.VK_R);
										autoTypeBot.keyRelease(KeyEvent.VK_R);
									} else if(messageToAutoType.substring(i, i + 1).equals("R")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_R);
										autoTypeBot.keyRelease(KeyEvent.VK_R);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("s")) {
										autoTypeBot.keyPress(KeyEvent.VK_S);
										autoTypeBot.keyRelease(KeyEvent.VK_S);
									} else if(messageToAutoType.substring(i, i + 1).equals("S")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_S);
										autoTypeBot.keyRelease(KeyEvent.VK_S);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("t")) {
										autoTypeBot.keyPress(KeyEvent.VK_T);
										autoTypeBot.keyRelease(KeyEvent.VK_T);
									} else if(messageToAutoType.substring(i, i + 1).equals("T")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_T);
										autoTypeBot.keyRelease(KeyEvent.VK_T);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("u")) {
										autoTypeBot.keyPress(KeyEvent.VK_U);
										autoTypeBot.keyRelease(KeyEvent.VK_U);
									} else if(messageToAutoType.substring(i, i + 1).equals("U")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_U);
										autoTypeBot.keyRelease(KeyEvent.VK_U);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("v")) {
										autoTypeBot.keyPress(KeyEvent.VK_V);
										autoTypeBot.keyRelease(KeyEvent.VK_V);
									} else if(messageToAutoType.substring(i, i + 1).equals("V")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_V);
										autoTypeBot.keyRelease(KeyEvent.VK_V);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("w")) {
										autoTypeBot.keyPress(KeyEvent.VK_W);
										autoTypeBot.keyRelease(KeyEvent.VK_W);
									} else if(messageToAutoType.substring(i, i + 1).equals("W")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_W);
										autoTypeBot.keyRelease(KeyEvent.VK_W);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("x")) {
										autoTypeBot.keyPress(KeyEvent.VK_X);
										autoTypeBot.keyRelease(KeyEvent.VK_X);
									} else if(messageToAutoType.substring(i, i + 1).equals("X")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_X);
										autoTypeBot.keyRelease(KeyEvent.VK_X);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("y")) {
										autoTypeBot.keyPress(KeyEvent.VK_Y);
										autoTypeBot.keyRelease(KeyEvent.VK_Y);
									} else if(messageToAutoType.substring(i, i + 1).equals("Y")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_Y);
										autoTypeBot.keyRelease(KeyEvent.VK_Y);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("z")) {
										autoTypeBot.keyPress(KeyEvent.VK_Z);
										autoTypeBot.keyRelease(KeyEvent.VK_Z);
									} else if(messageToAutoType.substring(i, i + 1).equals("Z")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_Z);
										autoTypeBot.keyRelease(KeyEvent.VK_Z);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals(" ")) {
										autoTypeBot.keyPress(KeyEvent.VK_SPACE);
										autoTypeBot.keyRelease(KeyEvent.VK_SPACE);
									} else if(messageToAutoType.substring(i, i + 1).equals("/")) {
										autoTypeBot.keyPress(KeyEvent.VK_SLASH);
										autoTypeBot.keyRelease(KeyEvent.VK_SLASH);
									} else if(messageToAutoType.substring(i, i + 1).equals("?")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_SLASH);
										autoTypeBot.keyRelease(KeyEvent.VK_SLASH);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals(".")) {
										autoTypeBot.keyPress(KeyEvent.VK_PERIOD);
										autoTypeBot.keyRelease(KeyEvent.VK_PERIOD);
									} else if(messageToAutoType.substring(i, i + 1).equals(">")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_PERIOD);
										autoTypeBot.keyRelease(KeyEvent.VK_PERIOD);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals(",")) {
										autoTypeBot.keyPress(KeyEvent.VK_COMMA);
										autoTypeBot.keyRelease(KeyEvent.VK_COMMA);
									} else if(messageToAutoType.substring(i, i + 1).equals("<")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_COMMA);
										autoTypeBot.keyRelease(KeyEvent.VK_COMMA);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("'")) {
										autoTypeBot.keyPress(KeyEvent.VK_QUOTE);
										autoTypeBot.keyRelease(KeyEvent.VK_QUOTE);
									} else if(messageToAutoType.substring(i, i + 1).equals("\"")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_QUOTE);
										autoTypeBot.keyRelease(KeyEvent.VK_QUOTE);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals(";")) {
										autoTypeBot.keyPress(KeyEvent.VK_SEMICOLON);
										autoTypeBot.keyRelease(KeyEvent.VK_SEMICOLON);
									} else if(messageToAutoType.substring(i, i + 1).equals(":")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_SEMICOLON);
										autoTypeBot.keyRelease(KeyEvent.VK_SEMICOLON);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("]")) {
										autoTypeBot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
										autoTypeBot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
									} else if(messageToAutoType.substring(i, i + 1).equals("}")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
										autoTypeBot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("[")) {
										autoTypeBot.keyPress(KeyEvent.VK_OPEN_BRACKET);
										autoTypeBot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
									} else if(messageToAutoType.substring(i, i + 1).equals("{")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_OPEN_BRACKET);
										autoTypeBot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("\\")) {
										autoTypeBot.keyPress(KeyEvent.VK_BACK_SLASH);
										autoTypeBot.keyRelease(KeyEvent.VK_BACK_SLASH);
									} else if(messageToAutoType.substring(i, i + 1).equals("|")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_BACK_SLASH);
										autoTypeBot.keyRelease(KeyEvent.VK_BACK_SLASH);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("=")) {
										autoTypeBot.keyPress(KeyEvent.VK_EQUALS);
										autoTypeBot.keyRelease(KeyEvent.VK_EQUALS);
									} else if(messageToAutoType.substring(i, i + 1).equals("+")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_EQUALS);
										autoTypeBot.keyRelease(KeyEvent.VK_EQUALS);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else if(messageToAutoType.substring(i, i + 1).equals("-")) {
										autoTypeBot.keyPress(KeyEvent.VK_MINUS);
										autoTypeBot.keyRelease(KeyEvent.VK_MINUS);
									} else if(messageToAutoType.substring(i, i + 1).equals("_")) {
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_MINUS);
										autoTypeBot.keyRelease(KeyEvent.VK_MINUS);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									} else {
										// For every key that isn't definied it
										// will
										// be a question mark.
										autoTypeBot.keyPress(KeyEvent.VK_SHIFT);
										autoTypeBot.keyPress(KeyEvent.VK_SLASH);
										autoTypeBot.keyRelease(KeyEvent.VK_SLASH);
										autoTypeBot.keyRelease(KeyEvent.VK_SHIFT);
									}
								autoTypeBot.keyPress(KeyEvent.VK_ENTER);
								autoTypeBot.keyRelease(KeyEvent.VK_ENTER);
								totalMessagesTyped++;
								messagesTypedLabel.setText("Messages Typed: " + totalMessagesTyped);
							}
					} catch(InterruptedException e) {
						stopThread();
						return;
					} catch(Exception e) {
						stopThread();
						e.printStackTrace();
						return;
					}
					startStopAutoTyperButton.setText("Start");
				}

				public void stopThread() {
					autoType = false;
					millisecondsToSleep = 0L;
					messageToAutoType = "";
					totalMessagesTyped = 0;
					startStopAutoTyperButton.setText("Start");
				}
			};
			autoTypeThread.start();
			startStopAutoTyperButton.setText("Stop");
		} else if(actionCommand.equalsIgnoreCase("stop"))
			stop();
	}

	public void stop() {
		autoType = false;
		millisecondsToSleep = 0L;
		messageToAutoType = "";
		if(autoTypeThread != null)
			autoTypeThread.interrupt();
		startStopAutoTyperButton.setText("Start");
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