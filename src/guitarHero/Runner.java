package guitarHero;

import java.awt.Robot;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Runner extends Application {

	private Scene title;
	private Scene game;
	private Scene credits;
	private Stage background;
	private NoteQueue songNotes;
	private ArrayList<Note> notesOnScreen;
	private VBox v1, v2;
	private GraphicsContext g2d;
	private Canvas canvas;
	private Canvas creditsCanvas;
	private final int SLEEP = 20;
	private MediaPlayer cultOfPersonality;
	private MediaPlayer wantedDeadOrAlive;
	private MediaPlayer futuro;
	private MediaPlayer throughTheFireAndFlames;
	private MediaPlayer schoolsOut;
	private MediaPlayer takeMeOut;
//	private MediaPlayer theMetal;
//	private MediaPlayer paintItBlack;
//	private MediaPlayer hotelCalifornia;
	private MediaPlayer currentSong;
	private MediaPlayer youFailed;
	private int[] NumberPress = new int[5];
	private int streak;
	private int Score;
	private int fail;
	private Robot AI;
	private boolean noFail;
	private boolean AIOn;
	private Timer timer;
	private SyncEventTimer syncTimer;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		fail = 75;
		streak = 0;
		Score = 0;
		noFail = false;
		AIOn = false;
		AI = new Robot();
		background = primaryStage;
		background.setTitle("Guitar Hero: Not Live");
		background.setResizable(false);
		background.setOnCloseRequest(e ->{
			e.consume();
			Platform.exit();
			System.exit(0);
		});
		
		songNotes = new NoteQueue();
		notesOnScreen = new ArrayList<Note>();
		
		syncTimer = new SyncEventTimer();
		timer = new Timer(SLEEP, new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				try {
					update();
					if (AIOn == true)
					{
						AIPlayGame();
					}
				} catch (Exception e1) {
				}
			}
		});

		Media song1 = new Media(getClass().getResource("/Song.mp3").toURI().toString());
		cultOfPersonality = new MediaPlayer(song1);
		cultOfPersonality.setStartTime(Duration.ZERO);

		Media song2 = new Media(getClass().getResource("/Bon Jovi - Wanted Dead Or Alive.mp3").toURI().toString());
		wantedDeadOrAlive = new MediaPlayer(song2);
		wantedDeadOrAlive.setStartTime(Duration.ZERO);

		Media song3 = new Media(getClass().getResource("/raiderfuturoedit.mp3").toURI().toString());
		futuro = new MediaPlayer(song3);
		futuro.setStartTime(Duration.ZERO);

		Media song4 = new Media(getClass().getResource("/ThroughTheFireAndFlamesEdited.mp3").toURI().toString());
		throughTheFireAndFlames = new MediaPlayer(song4);
		throughTheFireAndFlames.setStartTime(Duration.ZERO);
		
		Media song5 = new Media(getClass().getResource("/schoolsoutedited.mp3").toURI().toString());
		schoolsOut = new MediaPlayer(song5);
		schoolsOut.setStartTime(Duration.ZERO);
		
		Media song6 = new Media(getClass().getResource("/Franz Ferdinand - Take Me Out.mp3").toURI().toString());
		takeMeOut = new MediaPlayer(song6);
		takeMeOut.setStartTime(Duration.ZERO);
		
//		Media song7 = new Media(getClass().getResource("/Sounds/themetaledit.mp3").toURI().toString());
//		theMetal = new MediaPlayer(song7);
//		theMetal.setStartTime(Duration.ZERO);
		
		/*Media song8 = new Media(getClass().getResource("/res/paintitblack.wav").toURI().toString());
		paintItBlack = new MediaPlayer(song8);
		paintItBlack.setStartTime(Duration.ZERO);
		
		Media song9 = new Media(getClass().getResource("/res/hotelcalifornia.wav").toURI().toString());
		hotelCalifornia = new MediaPlayer(song9);
		hotelCalifornia.setStartTime(Duration.ZERO);
		*/
		Media Fail = new Media(
				getClass().getResource("/Power Failure-SoundBible.com-1821346166.wav").toURI().toString());
		youFailed = new MediaPlayer(Fail);
		// Setting up media and various misc variables
		// ===========================================
		// Title Screen
		Label label = new Label("GUITAR HERO: NOT LIVE");
		label.setFont(new Font("times new roman", 60));
		
		Button button1 = new Button("Cult of Personality - Living Colour");
		button1.setPrefSize(400, 50);

		button1.setOnAction(e -> {
			currentSong = cultOfPersonality;
			currentSong.seek(Duration.ZERO);
			currentSong.setVolume(1);
			startGame("/CultOfPersonality.txt");
			currentSong.play();
		});

		Button button2 = new Button("Wanted Dead or Alive - Bon Jovi");
		button2.setPrefSize(400, 50);

		button2.setOnAction(e -> {
			currentSong = wantedDeadOrAlive;
			currentSong.seek(Duration.ZERO);
			currentSong.setVolume(1);
			startGame("/WantedDeadOrAlive.txt");
			currentSong.play();
		});

		Button button3 = new Button("Futuro - Raider");
		button3.setPrefSize(400, 50);

		button3.setOnAction(e -> {
			currentSong = futuro;
			currentSong.seek(Duration.ZERO);
			currentSong.setVolume(1);
			startGame("/futuroexpert.gth");
			currentSong.play();
		});

		Button button4 = new Button("Through the Fire and the Flames - Dragonforce");
		button4.setPrefSize(400, 50);

		button4.setOnAction(e -> {
			currentSong = throughTheFireAndFlames;
			currentSong.seek(Duration.ZERO);
			currentSong.setVolume(1);
			startGame("/ThroughTheFireAndFlames.txt");
			currentSong.play();
		});
		
		Button button5 = new Button("Schools Out - Alice Cooper");
		button5.setPrefSize(400, 50);

		button5.setOnAction(e -> {
			currentSong = schoolsOut;
			currentSong.seek(Duration.ZERO);
			currentSong.setVolume(1);
			startGame("/schoolsoutexpert.gth");
			currentSong.play();
		});
		
		Button button6 = new Button("Take Me Out - Franz Ferdinand");
		button6.setPrefSize(400, 50);

		button6.setOnAction(e -> {
			currentSong = takeMeOut;
			currentSong.seek(Duration.ZERO);
			currentSong.setVolume(1);
			startGame("/takemeout.txt");
			currentSong.play();
		});
		
//		Button button7 = new Button("The Metal - Tenacious D");
//		button7.setPrefSize(400, 50);
//
//		button7.setOnAction(e -> {
//			currentSong = theMetal;
//			currentSong.seek(Duration.ZERO);
//			currentSong.setVolume(1);
//			startGame("/noteSheets/themetalexpert.gth");
//			currentSong.play();
//		});
//		
//		Button button8 = new Button("Paint It Black - The Rolling Stones");
//		button8.setPrefSize(400, 50);
//
//		button8.setOnAction(e -> {
//			currentSong = paintItBlack;
//			currentSong.seek(Duration.ZERO);
//			currentSong.setVolume(1);
//			startGame("/noteSheets/paintitblack.gth");
//			currentSong.play();
//		});
//		
//		Button button9 = new Button("Hotel California - The Eagles");
//		button9.setPrefSize(400, 50);
//
//		button9.setOnAction(e -> {
//			currentSong = hotelCalifornia;
//			currentSong.seek(Duration.ZERO);
//			currentSong.setVolume(1);
//			startGame("/noteSheets/hotelcaliforniaexpert.gth");
//			currentSong.play();
//		});

		Button qualityButton = new Button("No Fail: false");
		qualityButton.setPrefSize(400, 25);

		qualityButton.setOnAction(e -> {
			if (noFail == false) {
				noFail = true;
				qualityButton.setText("No Fail: true");
			} else {
				noFail = false;
				qualityButton.setText("No Fail: false");
			}
		});
		
		Button creditsButton = new Button("Credits");
		creditsButton.setPrefSize(400, 25);
		
		creditsButton.setOnAction(e -> {
			background.setScene(credits);
			drawCredits();
		});

		v1 = new VBox(30);
		v1.getChildren().addAll(label, button1, button2, button3, button4, button5, button6,
				/*button7, button8, button9,*/ creditsButton, qualityButton);
		v1.setAlignment(Pos.CENTER);
		title = new Scene(v1, 800, 700);
		// Title Screen Above
		// =========================
		// Actual Game Below
		v2 = new VBox();
		canvas = new Canvas(800, 700);
		g2d = canvas.getGraphicsContext2D();
		v2.setAlignment(Pos.CENTER);
		v2.getChildren().add(canvas);
		game = new Scene(v2, 800, 700);
		game.setOnKeyPressed(new EventHandler<KeyEvent>() {
			//What causes the circles to fill when you press a key along with calling hit detection
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case DIGIT1:
					NumberPress[0] += 5;
					detectHit(1);
					break;
				case DIGIT2:
					NumberPress[1] += 5;
					detectHit(2);
					break;
				case DIGIT3:
					NumberPress[2] += 5;
					detectHit(3);
					break;
				case DIGIT4:
					NumberPress[3] += 5;
					detectHit(4);
					break;
				case DIGIT5:
					NumberPress[4] += 5;
					detectHit(5);
					break;
				case P:
					quitSong();
					break;
				case B:
					if (AIOn)
					{
						AIOn = false;
					}
					else
					{
						AIOn = true;
					}
					break;
				default:
					break;
				}
			}
		});
		//Game above
		//=========================
		//Credits Below
		VBox v3 = new VBox();
		creditsCanvas = new Canvas(800, 700);
		v3.setAlignment(Pos.CENTER);
		v3.getChildren().add(creditsCanvas);
		credits = new Scene(v3, 800, 700);
		credits.setOnKeyPressed(e -> {
			switch (e.getCode())
			{
			case ESCAPE:
				g2d = canvas.getGraphicsContext2D();
				background.setScene(title);
			default:
				break;
			}
		});
		//Credits above
		//========================
		//Starting the game
		background.setScene(title);
		background.show();
		if (AIOn)
		{
			AISelectSong();
		}
	}
	
	//Launches the game
	public static void main(String[] args) {
		launch(args);
	}
	
	//This loads the notes from whatever song you're playing based on a file location
	private void loadNotes(String file) {
		Scanner scan;
		try {
			scan = new Scanner(getClass().getResourceAsStream(file));
			while (scan.hasNextLine()) {
				songNotes.add(scan.nextInt(), scan.nextDouble());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Repaints the screen and removes any off screen notes
	private void update() {
		g2d.clearRect(0, 0, 800, 700);
		g2d.setFill(Color.GAINSBORO);
		g2d.fillRect(660, 350 - fail, 20, 5);
		g2d.fillRect(250, 0, 300, 700);
		g2d.setFill(Color.BLACK);
		g2d.fillRect(250, 470, 300, 10);
		g2d.fillRect(250, 530, 300, 10);
		g2d.fillText("Streak: " + streak, 600, 500);
		g2d.fillText("Score: " + Score, 600, 550);

		g2d.setFill(Color.GREEN);
		g2d.fillOval(275, 480, 50, 50);
		g2d.fillRect(600, 200, 50, 50);
		g2d.setFill(Color.RED);
		g2d.fillOval(325, 480, 50, 50);
		g2d.fillRect(600, 300, 50, 50);
		g2d.setFill(Color.YELLOW);
		g2d.fillOval(375, 480, 50, 50);
		g2d.fillRect(600, 250, 50, 50);
		g2d.setFill(Color.BLUE);
		g2d.fillOval(425, 480, 50, 50);
		g2d.setFill(Color.ORANGE);
		g2d.fillOval(475, 480, 50, 50);
		
		g2d.setFill(Color.BLACK);
		g2d.fillText("GUITAR HERO: NOT LIVE", 100, 100);
		if (noFail == false && fail < 10) {
			try {
				g2d.setFill(Color.RED);
				g2d.fillText("YOU FAILED", 300, 300);
				youFailed.play();
				Thread.sleep(2000);
				Platform.exit();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		g2d.setFill(Color.BLACK);
		for (int i = 0; i < 5; i++) {
			if (NumberPress[i] > 0) {
				g2d.fillOval(285 + (i * 50), 490, 30, 30);
				NumberPress[i]--;
			}
		}

		for (Note a : notesOnScreen) {
			a.fillNote(g2d);
		}
		removeOffScreen();
	}
	
	//Removes all notes that are off the bottom of the screen and updates fail and streak accordingly
	private void removeOffScreen() {
		for (int i = 0; i < notesOnScreen.size(); i++) {
			if (notesOnScreen.get(i).getY() > 700) {
				notesOnScreen.remove(i);
				i--;
				streak = 0;
				currentSong.setVolume(.5);
				fail--;
				if (fail < 0) {
					fail = 0;
				}
			} else {
				return;
			}
		}
	}
	
	//Detects whether or not you hit a note and increases your streak if you did
	//Also calculates how much your score increases based on your note streak
	private void detectHit(int a) {
		for (int i = 0; i < notesOnScreen.size(); i++) {
			Note hold = notesOnScreen.get(i);
			if (hold.getColor() == a) {
				if (hold.getY() >= 400 && hold.getY() <= 550) {
					notesOnScreen.remove(i);
					streak++;
					if (streak < 32)
					{
						Score = Score + ((streak / 8) + 1);
					}
					else {
						Score += 5;
						fail++;
					}
					if (fail < 100)
					{
						fail++;
					}
					else if (fail > 150) {
						fail = 150;
					}
					currentSong.setVolume(1);
					break;
				} else {
					if ((fail > 25 || Math.random() < .5) || Math.random() < .2)
						{
						fail--;
						}
					if (fail < 0) {
						fail = 0;
					}
				}
			}
		}
	}
	
	//Starts the game by switching to the game screen
	private void startGame(String file) {
		loadNotes(file);
		background.setScene(game);
		while (!songNotes.isEmpty())
		{
			syncTimer.addEvent(new ActionListener(){

				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int hold = songNotes.remove();
					switch (hold) {
					case 1:
						notesOnScreen.add(new Note(1));
						break;
					case 2:
						notesOnScreen.add(new Note(2));
						break;
					case 3:
						notesOnScreen.add(new Note(3));
						break;
					case 4:
						notesOnScreen.add(new Note(4));
						break;
					case 5:
						notesOnScreen.add(new Note(5));
						break;
					default:
						notesOnScreen.add(new Note(6));
						break;
					}
				}
			
			}
				, (long) (songNotes.getDelay()*1000000000));
		}
		timer.start();
		syncTimer.start();
	}
	
	//Draws the credits screen
	private void drawCredits()
	{
		g2d = creditsCanvas.getGraphicsContext2D();
		g2d.clearRect(0, 0, 800, 700);
		g2d.setFill(Color.GAINSBORO);
		g2d.fillRect(660, 200, 20, 5);
		g2d.fillRect(250, 0, 300, 700);
		g2d.setFill(Color.BLACK);
		g2d.fillRect(250, 470, 300, 10);
		g2d.fillRect(250, 530, 300, 10);
		g2d.fillText("Streak: Over 9000", 600, 500);
		g2d.fillText("Score: 1 billion fifillion points", 600, 550);

		g2d.setFill(Color.GREEN);
		g2d.fillOval(275, 480, 50, 50);
		g2d.fillRect(600, 200, 50, 50);
		g2d.setFill(Color.RED);
		g2d.fillOval(325, 480, 50, 50);
		g2d.fillRect(600, 300, 50, 50);
		g2d.setFill(Color.YELLOW);
		g2d.fillOval(375, 480, 50, 50);
		g2d.fillRect(600, 250, 50, 50);
		g2d.setFill(Color.BLUE);
		g2d.fillOval(425, 480, 50, 50);
		g2d.setFill(Color.ORANGE);
		g2d.fillOval(475, 480, 50, 50);
		
		g2d.setFill(Color.BLACK);
		g2d.fillText("Lead Programmers\n==========\nCollin Goldsworthy\nMax Johnson\nDominic Voto\n==========\nSong Writer Program From\n==========\nDominic Voto"+
		"\n==========\nBPM Calculator Creator\n==========\nMason Walls\n==========\nPlaytesters\n==========\nLead Playtester: Dominic Voto\nAdithi Anil\nWill Casey\nJosh Kreitz"+
				"\nJake Pietruszewski\nSam Olson\nEric Ostein\nDr. Reinartz\n=========="
				+ "\nMany thanks to everyone from APCS hour 7\nand ACS hours 2 and 6 for all the support!", 250, 50);
	}
	
	//How the bot detects hits and then plays the game accordingly
	private void AIPlayGame()
	{
		for (Note a : notesOnScreen)
		{
			if (a.getY() > 480)
			{
				switch (a.getColor())
				{
				case 1:
					AI.keyPress(java.awt.event.KeyEvent.VK_1);
					AI.keyRelease(java.awt.event.KeyEvent.VK_1);
					break;
				case 2:
					AI.keyPress(java.awt.event.KeyEvent.VK_2);
					AI.keyRelease(java.awt.event.KeyEvent.VK_2);
					break;
				case 3:
					AI.keyPress(java.awt.event.KeyEvent.VK_3);
					AI.keyRelease(java.awt.event.KeyEvent.VK_3);
					break;
				case 4:
					AI.keyPress(java.awt.event.KeyEvent.VK_4);
					AI.keyRelease(java.awt.event.KeyEvent.VK_4);
					break;
				case 5:
					AI.keyPress(java.awt.event.KeyEvent.VK_5);
					AI.keyRelease(java.awt.event.KeyEvent.VK_5);
					break;
				}
			}
			else
			{
				break;
			}
		}
	}
	
	private void AISelectSong()
	{
		int songChoice = (int) (Math.random()*7);
		songChoice*=55;
		songChoice+=60;
		AI.mouseMove((int) background.getX()+ 400, (int) background.getY()+ songChoice);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AI.mousePress(java.awt.event.InputEvent.BUTTON1_MASK);
		AI.mouseRelease(java.awt.event.InputEvent.BUTTON1_MASK);
	}
	
	
	
	private void quitSong()
	{
		syncTimer.stop();
		syncTimer = new SyncEventTimer();
		fail = 75;
		streak = 0;
		Score = 0;
		currentSong.stop();
		timer.stop();
		songNotes.clear();
		notesOnScreen = new ArrayList<Note>();
		background.setScene(title);
	}
	
	

}