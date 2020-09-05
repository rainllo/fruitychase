import java.awt.Color; // Allows to colors to be used from RGB
import java.awt.event.KeyEvent; // An event which indicates a keystroke has occurred in a component
import java.io.*; // Need to import java io and java util libraries to support file reading and scanning
import java.util.*;

public class MainProject2T5 {

	public static void main(String[] args) throws java.io.IOException { // Pass off responsibility for error checking
		// TODO Auto-generated method stub

		// Setup EZ graphics system creating a window of size (1920,1080)
		EZ.initialize(1920, 1080);

		// Set the background color to light gray
		EZ.setBackgroundColor(new Color(220, 220, 220));

		// Creates a text at the top saying "SCORE: 0" with with an orange color,
		// Lobster font, and size 150
		EZText text = EZ.addText(960, 70, "SCORE: 0", new Color(255, 165, 0), 150);
		text.setFont("Lobster-Regular.ttf");

		// Loads the police car and assigns it to a variable carPolice and places it at
		// (1720, 780)
		EZImage carPolice = EZ.addImage("police1.png", 1720, 780);

		// Keep track of the score. Set it to zero in the beginning.
		int score = 0;

		// Assigns a value 0 to a variable x of type integer
		int x = 0;

		// Assigns a value 0 to a variable a of type integer
		int a = 0;

		// Declare an array called fruity of type Fruits that can hold onto 5 fruity.
		Fruits fruity[] = new Fruits[5];
		String[] fruitPics = { "fruit1.png", "fruit2.png", "fruit3.png", "fruit4.png", "fruit5.png" };

		// Make 5 fruity.
		for (int i = 0; i < 5; i++) {
			// Make a fruity and store it in location i of the fruity array
			fruity[i] = new Fruits(fruitPics[i], "star.png", 200, 200, 1920, 1080);
		}

		int posX = 0; // Assigns a value 0 to a variable posX of type integer

		int posY = 0; // Assigns a value 0 to a variable posY of type integer

		boolean collected = false; // Set starting value of collected to false since no fruits were collected at
									// the beginning

		// Loads the meowing sound into your program
		EZSound catSound = EZ.addSound("animal1.wav");

		// Loads the explosion sound and assigns it to a variable called boomSound
		EZSound boomSound = EZ.addSound("boom1.wav");

		// Loads the juicy music
		EZSound juicy = EZ.addSound("juicy.wav");

		FileReader fr = new FileReader("positions.txt"); // Make a file reader object
		Scanner sc = new Scanner(fr); // Make a file scanner object
		int[] xPosBuilding = new int[5]; // Declare an array called xPosBuilding of type integer that can hold onto 5
											// xPosBuildings.
		int[] yPosBuilding = new int[5]; // Declare an array called yPosBuilding of type integer that can hold onto 5
											// yPosBuildings.
		for (int i = 0; i < 5; i++) {
			xPosBuilding[i] = sc.nextInt(); // Read an integer
			yPosBuilding[i] = sc.nextInt(); // Read another integer
			System.out.println(xPosBuilding[i]); // Prints out the number at that position in the array
			System.out.println(yPosBuilding[i]); // Prints out the number at that position in the array on the next line
		}
		sc.close(); // Close the file when done reading

		Buildings[] builds = new Buildings[5]; // Declare an array called builds of type Buildings that can hold onto 5
												// builds.
		Buildings build1 = new Buildings("building1.png", xPosBuilding[0], yPosBuilding[0]); // Creates 5 different
																								// buildings assigns
																								// them an (x,y)
																								// position from the
																								// text file
		Buildings build2 = new Buildings("building2.png", xPosBuilding[1], yPosBuilding[1]);
		Buildings build3 = new Buildings("building3.png", xPosBuilding[2], yPosBuilding[2]);
		Buildings build4 = new Buildings("building4.png", xPosBuilding[3], yPosBuilding[3]);
		Buildings build5 = new Buildings("building5.png", xPosBuilding[4], yPosBuilding[4]);

		builds[0] = build1; // Places the buildings into each array
		builds[1] = build2;
		builds[2] = build3;
		builds[3] = build4;
		builds[4] = build5;

		// The while loop of the program, it will run as long as the value of x stays at
		// 0
		while (x == 0) {

			// Get the position of the (x,y) position of the police car
			posX = carPolice.getXCenter();
			posY = carPolice.getYCenter();

			// Set collected to true when the fruits have been collected
			collected = true;

			// If the D key is pressed...
			if (EZInteraction.isKeyDown(KeyEvent.VK_D)) {
				// Then the police car turns right 2 degrees
				carPolice.turnRight(2);
				// If the A key is pressed...
			} else if (EZInteraction.isKeyDown(KeyEvent.VK_A)) {
				// Then it turns left 2 degrees
				carPolice.turnLeft(2);
				// If the W key is pressed...
			} else if (EZInteraction.isKeyDown(KeyEvent.VK_W)) {
				// Then it moves forward 5 pixels
				carPolice.moveForward(5);
				// If the W key is pressed...
			} else if (EZInteraction.isKeyDown(KeyEvent.VK_S)) {
				// Then it moves backward 5 pixels
				carPolice.moveForward(-5);
			}
			for (int i = 0; i < 5; i++) {
				// Make each of the fruits move.
				fruity[i].go();

				// If the police car is inside a fruit, then...
				if (collected && fruity[i].isInside(posX, posY)) {

					fruity[i].got(); // Collect the fruit and place a star
					catSound.play(); // Play the meowing sound
					score++; // Increase the score by 1
					a++; // Increase the subscore
					text.setMsg("SCORE: " + score); // Adds a score to the scoreboard
				}
			}
			if (carPolice.isPointInElement(xPosBuilding[0], yPosBuilding[0])) {
				// Adds an explosion sound and fire at building1 if the police collides with it
				EZ.addImage("explode1.png", xPosBuilding[0], yPosBuilding[0]);
				boomSound.play();
				text.setMsg("You crashed!"); // Displays "You crashed!" at the top
				x++; // Adds 1 to the value of x, the statement is now false and the while loop
						// stops, game over
			}
			if (carPolice.isPointInElement(xPosBuilding[1], yPosBuilding[1])) {
				// Adds an explosion sound and fire at building2 if the police collides with it
				EZ.addImage("explode1.png", xPosBuilding[1], yPosBuilding[1]);
				boomSound.play();
				text.setMsg("You died!"); // Displays "You died!" at the top
				x++; // Adds 1 to the value of x, the statement is now false and the while loop
						// stops, game over
			}
			if (carPolice.isPointInElement(xPosBuilding[2], yPosBuilding[2])) {
				// Adds an explosion sound and fire at building3 if the police collides with it
				EZ.addImage("explode1.png", xPosBuilding[2], yPosBuilding[2]);
				boomSound.play();
				text.setMsg("You exploded!"); // Displays "You exploded!" at the top
				x++; // Adds 1 to the value of x, the statement is now false and the while loop
						// stops, game over
			}
			if (carPolice.isPointInElement(xPosBuilding[3], yPosBuilding[3])) {
				// Adds an explosion sound and fire at building4 if the police collides with it
				EZ.addImage("explode1.png", xPosBuilding[3], yPosBuilding[3]);
				boomSound.play();
				text.setMsg("You failed!"); // Displays "You failed!" at the top
				x++; // Adds 1 to the value of x, the statement is now false and the while loop
						// stops, game over
			}
			if (carPolice.isPointInElement(xPosBuilding[4], yPosBuilding[4])) {
				// Adds an explosion sound and fire at building5 if the police collides with it
				EZ.addImage("explode1.png", xPosBuilding[4], yPosBuilding[4]);
				boomSound.play();
				text.setMsg("Game over!"); // Displays "Game over!" at the top
				x++; // Adds 1 to the value of x, the statement is now false and the while loop
						// stops, game over
			}
			// Keeps track of the subscore a, if a reaches a value of 5, then...
			if (a == 5 && (!juicy.isPlaying())) {
				juicy.play(); // Plays juicy music
				EZ.addImage("juicypic.png", 960, 540); // Adds a picture of juicy
			}

			// Refreshes the screen so it animates
			EZ.refreshScreen();

		}

	}

}
