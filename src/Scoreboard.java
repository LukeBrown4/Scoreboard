
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

/** Hockey Scoreboard v1.0
 * 
 *  A small java project by Luke Brown
 *  
 * @author Luke Brown
 */
public class Scoreboard extends JFrame {

	private JPanel contentPane;
	public int home = 0;
	public int away = 0;
	public int homeSOG = 0;
	public int awaySOG = 0;
	public static JLabel time;
	public static JLabel timeSecs;
	
	static Timer t;
	static int interval;
	static int delay = 1000;
	static int period = 1000;
	static boolean clockRunning = false;
	static int per = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scoreboard frame = new Scoreboard();
					frame.setVisible(true);
				    t = new Timer();
				    t.scheduleAtFixedRate(new TimerTask() {
				        public void run() {
				        	if(clockRunning == true) {
				        		if(interval > 60 && interval < 600) {
				        			time.setText("0" + Integer.toString(setInterval()/60));
				        		} else {
				        		    time.setText(Integer.toString(setInterval()/60));
				        		}
				        		if(setSecInterval()%60 < 10) {
				        			timeSecs.setText("0" + Integer.toString(setSecInterval()%60));
				        		} else {
				        			timeSecs.setText(Integer.toString(setSecInterval()%60));
				        		}
				           if((setSecInterval()) == 0) {
				   	    	SimpleAudioPlayer.main("src/audio/BUZZER.wav");
				           }
				        	}
				        }
				    }, delay, period);
	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Scoreboard() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Scoreboard.class.getResource("/images/puck.png")));
		setTitle("Hockey Scoreboard");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel homeLabel = new JLabel("Home");
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeLabel.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		homeLabel.setForeground(Color.WHITE);
		homeLabel.setBounds(43, 21, 210, 30);
		contentPane.add(homeLabel);
		
		JLabel awayLabel = new JLabel("Away");
		awayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		awayLabel.setForeground(Color.WHITE);
		awayLabel.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		awayLabel.setBounds(656, 21, 150, 30);
		contentPane.add(awayLabel);
		
		Button homeScore = new Button("0");
		homeScore.setForeground(Color.WHITE);
		homeScore.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		homeScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home = home + 1;
				homeScore.setLabel(Integer.toString(home));
				clockRunning = false;
			}
		});
		homeScore.setBackground(new Color(0, 0, 0));
		homeScore.setBounds(74, 57, 150, 70);
		contentPane.add(homeScore);
		
		Button awayScore = new Button("0");
		awayScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				away = away + 1;
				awayScore.setLabel(Integer.toString(away));
			}
		});
		awayScore.setForeground(Color.WHITE);
		awayScore.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		awayScore.setBackground(Color.BLACK);
		awayScore.setBounds(656, 57, 150, 70);
		contentPane.add(awayScore);
		
		JLabel lblNewLabel = new JLabel("PERIOD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		lblNewLabel.setBounds(257, 133, 162, 63);
		contentPane.add(lblNewLabel);
		
		Button button = new Button("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (per < 3) {
					per++;
					button.setLabel("" + per);
				} else {
					button.setLabel("OT");
					per = 0;
				}
			}
		});
		button.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(0, 0, 0));
		button.setBounds(436, 133, 41, 63);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 3));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(275, 21, 322, 106);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(":");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 51));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(155, 11, 19, 84);
		panel.add(lblNewLabel_1);
		
		time = new JLabel("--");
		time.setHorizontalAlignment(SwingConstants.TRAILING);
		time.setForeground(Color.WHITE);
		time.setFont(new Font("Bahnschrift", Font.BOLD, 50));
		time.setBounds(10, 11, 135, 84);
		panel.add(time);
		
		timeSecs = new JLabel("--");
		timeSecs.setForeground(Color.WHITE);
		timeSecs.setFont(new Font("Bahnschrift", Font.BOLD, 50));
		timeSecs.setBounds(171, 11, 141, 84);
		panel.add(timeSecs);
		
		JLabel homeShotsLabel = new JLabel("Shots");
		homeShotsLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		homeShotsLabel.setForeground(Color.WHITE);
		homeShotsLabel.setBounds(74, 161, 90, 35);
		contentPane.add(homeShotsLabel);
		
		JLabel awayShotsLabel = new JLabel("Shots");
		awayShotsLabel.setForeground(Color.WHITE);
		awayShotsLabel.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		awayShotsLabel.setBounds(596, 161, 90, 35);
		contentPane.add(awayShotsLabel);
		
		Button homeShotsButton = new Button("0");
		homeShotsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeSOG = homeSOG + 1;
				homeShotsButton.setLabel("" + homeSOG);
				SimpleAudioPlayer.main("src/audio/SLAPSHOT.wav");
			}
		});
		homeShotsButton.setForeground(Color.WHITE);
		homeShotsButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		homeShotsButton.setBackground(Color.BLACK);
		homeShotsButton.setBounds(170, 161, 54, 35);
		contentPane.add(homeShotsButton);
		
		Button awayShotsButton = new Button("0");
		awayShotsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				awaySOG = awaySOG + 1;
				awayShotsButton.setLabel("" + awaySOG);
				SimpleAudioPlayer.main("src/audio/SLAPSHOT.wav");
			}
		});
		awayShotsButton.setForeground(Color.WHITE);
		awayShotsButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		awayShotsButton.setBackground(Color.BLACK);
		awayShotsButton.setBounds(692, 161, 54, 35);
		contentPane.add(awayShotsButton);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setForeground(Color.WHITE);
		controlPanel.setBorder(new LineBorder(null, 2));
		controlPanel.setBackground(Color.BLACK);
		controlPanel.setBounds(10, 220, 874, 63);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);
		
		Button button_1 = new Button("BOS");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAudioPlayer.main("src/audio/BOSHORN.wav");
			}
		});
		button_1.setBackground(Color.BLACK);
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		button_1.setBounds(10, 10, 61, 43);
		controlPanel.add(button_1);
		
		Button button_1_1 = new Button("CBJ");
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAudioPlayer.main("src/audio/CBJHORN.wav");
			}
		});
		button_1_1.setForeground(Color.WHITE);
		button_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		button_1_1.setBackground(Color.BLACK);
		button_1_1.setBounds(77, 10, 61, 43);
		controlPanel.add(button_1_1);
		
		Button button_1_1_1 = new Button("CHI");
		button_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAudioPlayer.main("src/audio/CHIHORN.wav");
			}
		});
		button_1_1_1.setForeground(Color.WHITE);
		button_1_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		button_1_1_1.setBackground(Color.BLACK);
		button_1_1_1.setBounds(144, 10, 61, 43);
		controlPanel.add(button_1_1_1);
		
		Button button_1_1_2 = new Button("PHI");
		button_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleAudioPlayer.main("src/audio/PHIHORN.wav");
			}
		});
		button_1_1_2.setForeground(Color.WHITE);
		button_1_1_2.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		button_1_1_2.setBackground(Color.BLACK);
		button_1_1_2.setBounds(211, 10, 61, 43);
		controlPanel.add(button_1_1_2);
		
		Button startStop = new Button("Start Time");
		startStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clockRunning) {
					clockRunning = false;
					startStop.setLabel("Start Time");
					
				} else { 
					clockRunning = true;
					startStop.setLabel("Stop Time");
				}
			}
		});
		startStop.setActionCommand("");
		startStop.setForeground(Color.WHITE);
		startStop.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		startStop.setBackground(Color.BLACK);
		startStop.setBounds(552, 10, 100, 43);
		controlPanel.add(startStop);
		
		Button addMin = new Button("Add Minute");
		addMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interval = interval + 60;
				if(interval > 60 && interval < 600) {
        			time.setText("0" + interval/60);
        		} else {
        		    time.setText(" " + interval/60);
        		}
			}
		});
		addMin.setForeground(Color.WHITE);
		addMin.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		addMin.setBackground(Color.BLACK);
		addMin.setActionCommand("");
		addMin.setBounds(658, 10, 100, 43);
		controlPanel.add(addMin);
		
		Button addSec = new Button("Add Second");
		addSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interval++;
				if(setSecInterval()%60 < 10) {
        			timeSecs.setText("0" + interval%60);
        		} else {
        			timeSecs.setText("" + interval%60);
        		}
			}
		});
		addSec.setForeground(Color.WHITE);
		addSec.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		addSec.setBackground(Color.BLACK);
		addSec.setActionCommand("");
		addSec.setBounds(764, 10, 100, 43);
		controlPanel.add(addSec);
		
		Button reset = new Button("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interval = 0;
				clockRunning = false;
				startStop.setLabel("Start Time");
				time.setText("0" + interval/60);
				timeSecs.setText("0" + interval%60);
				homeSOG = 0;
				homeShotsButton.setLabel("" + homeSOG);
				awaySOG = 0;
				awayShotsButton.setLabel("" + awaySOG);
				
				home = 0;
				homeScore.setLabel(Integer.toString(home));
				away = 0;
				awayScore.setLabel(Integer.toString(away));
			}
		});
		reset.setForeground(Color.WHITE);
		reset.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		reset.setBackground(Color.BLACK);
		reset.setActionCommand("");
		reset.setBounds(368, 10, 100, 43);
		controlPanel.add(reset);
	}
	
	private static final int setInterval() {
	    if (interval == 0) {
	    	return 0;
	    }
	    return --interval;
	}
	
	private static final int setSecInterval() {
	    return interval;
	}
}


