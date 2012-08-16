package gfv.robot;

public class Board {
	private static final int NUM_COLS=5;
	private static final int NUM_ROWS=5;
	
	/**
	 * Checks if a position is valid in the current board definition
	 * @param newPosition to check
	 * @return true if valid position
	 */
	public static boolean isValidPosition(Position newPosition) {
		if (newPosition.getColumn()<0 ||newPosition.getColumn()>NUM_COLS-1)
			return false;
		if (newPosition.getRow()<0 ||newPosition.getRow()>NUM_ROWS-1)
			return false;
		return true;
	}
}
