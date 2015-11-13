package managers;

public class Position {
	
	// **** POSITION DATA ****
	private int x;
	private int y;
	private int velX = 0;
	private int velY = 0;
	
	
	// --------------------------------------------------------------------------------
	
	
	// **** CONSTRUCTORS ****
	/**
	 * Constructor. Sets the int x/y-position to the local data. 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		setPosX(x);
		setPosY(y);
	}
	
	/**
	 * Constructor. Sets the double x/y-position to the local data. 
	 * @param x
	 * @param y
	 */
	public Position(double x, double y) {
		setPosX((int) x);
		setPosY((int) y);
	}

	
	
	// ----------------- **** METHODS **** ----------------- 
	
	/**
	 * Sets x-position to local x.
	 * @param x
	 */
	public void setPosX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets y-position to local y.
	 * @param y
	 */
	public void setPosY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets double x-position to local x.
	 * @param x
	 */
	public void setPosX(double x) {
		this.x = (int) x;
	}
	
	/**
	 * Sets double y-position to local y.
	 * @param y
	 */
	public void setPosY(double y) {
		this.y = (int) y;
	}
	
	/**
	 *  Returns the local x-position.
	 * @return x-position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 *  Returns the local y-position.
	 * @return y-position
	 */
	public int getY() {
		return y;
	}

	/**
	 *  Increases the x-position on dx units.
	 * @param dx
	 */
	public void increasePosX(int dx) {
		x += dx;
	}
	
	/**
	 *  Increases the y-position on dy units.
	 * @param dy
	 */
	public void increasePosY(int dy) {
		y += dy;
	}
	
	/**
	 *  Sets the local x-velocity.
	 * @param velX
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	/**
	 *  Sets the local y-velocity.
	 * @param vely
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}

	/**
	 *  Returns the local x-velocity.
	 * @return dx
	 */
	public int getVelX() {
		return velX;
	}
	
	/**
	 *  Returns the local y-velocity.
	 * @return dy
	 */
	public int getVelY() {
		return velY;
	}

	/**
	 *  Duplicates the actual position to a new one.
	 *  @return Position with (x, y) coordinates.
	 */
	public Position clone() {
		return new Position(x, y);
	}
	
	/**
	 *  Overrides the Object method. Only checks the coordinates, not the velocities.
	 * @param p
	 * @return
	 */
	public boolean equals(Position p) {
		if(this.getX() != p.getX()) return false;
		if(this.getY() != p.getY()) return false;
		return true;
	}
	
	public String toString() {
		return "Position: (" + x + ", " + y + ")";
	}
}
