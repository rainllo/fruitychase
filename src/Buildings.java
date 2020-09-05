
public class Buildings {
	
	// Declare a variable called buildPic of type EZImage
	private EZImage buildPic;
	
	// Constructor for Buildings
	public Buildings(String filename, int x, int y) {
		buildPic = EZ.addImage(filename, x, y);
	}
}
