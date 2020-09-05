import java.util.Random;

public class Fruits {

	// Declare variables called fruitPic and starPic of type EZImage
	private EZImage fruitPic, starPic;

	private int x, y; // Location of the fruits
	private int destx, desty; // Destination of the fruits
	private int rangex, rangey; // Range the fruits can move around
	private boolean collect; // Checks if the fruits have been colected

	// Constructor for Fruits
	public Fruits(String filename, String filenameStar, int posx, int posy, int rx, int ry) {
		x = posx; // Assigns the value for the (x,y) and (rangex,rangey)
		y = posy;
		rangex = rx;
		rangey = ry;

		setRandomDirection();
		fruitPic = EZ.addImage(filename, posx, posy);
		starPic = EZ.addImage(filenameStar, rx, ry);
		starPic.pushToBack();
		starPic.hide();

		setFruitPicPos(x, y);
		collect = true;

	}

	public int getX() {
		return x;
	}

	public void setDest(int posx, int posy) {
		destx = posx;
		desty = posy;
	}

	public void setRandomDirection() {

		// Make a random number generator
		Random randomGenerator = new Random();

		// Get a random number from 0 to rangex and use that as the destination of the
		// fruit in X
		int ranx = randomGenerator.nextInt(rangex);

		// Same as above but for Y
		int rany = randomGenerator.nextInt(rangey);

		// Sets the actual destination
		setDest(ranx, rany);
	}

	public void go() {

		if (x > destx)
			goLeft(1); // If the x position of fruit is greater than destx then move left or right
		if (x < destx)
			goRight(1);
		if (y > desty)
			goUp(1); // If the y position of fruit is greater than desty then move up or down
		if (y < desty)
			goDown(1);

		// If the position of the fruit is exactly the same as the position of the
		// destination, then set a new random direction
		if ((x == destx) && (y == desty)) {
			setRandomDirection();
		}
	}

	public void setPos(int posx, int posy) { // method for setPos
		x = posx;
		y = posy;
		setFruitPicPos(x, y);
	}

	private void setFruitPicPos(int posx, int posy) { // method for setFruitPicPos()
		if (collect) {
			fruitPic.translateTo(posx, posy); // moves the fruitPic to here
			starPic.translateTo(posx, posy); // moves the starPic to here
		}
	}

	public void goLeft(int step) { // method for goLeft()
		x = x - step;
		setFruitPicPos(x, y);
	}

	public void goRight(int step) { // method for goRight()
		x = x + step;
		setFruitPicPos(x, y);
	}

	public void goUp(int step) { // method for goUp()
		y = y - step;
		setFruitPicPos(x, y);
	}

	public void goDown(int step) { // method for goDown()
		y = y + step;
		setFruitPicPos(x, y);
	}

	public boolean isInside(int posx, int posy) {

		// If you already collected the fruit, you can't collect it again!
		if (collect == false)
			return false;
		else
			return fruitPic.isPointInElement(posx, posy);
	}

	public void got() { // method for got()
		fruitPic.hide();
		starPic.show();
		collect = false;
	}
}
