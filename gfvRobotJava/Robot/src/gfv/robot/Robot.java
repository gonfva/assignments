package gfv.robot;

public class Robot {

	private Position currentPosition=null;


	/**
	 * MOVE will move the toy robot one unit forward 
	 * in the direction it is currently facing.
	 * 
	 */
	public void move() {
		if (currentPosition==null) return;
		Position newPosition = currentPosition.advance();
		place(newPosition);
	}
	/**
	 * REPORT will announce the X,Y and F of the robot. 
	 * This can be in any form, but standard output is sufficient.
	 * 
	 */
	public Position report() {
		if (currentPosition==null) return null;
		return currentPosition;
	}
	/**
	 * - LEFT and RIGHT will rotate the robot 90 degrees in 
	 * 	the specified direction without changing the position of the robot.
	 * 
	 * NOTE: I understand left as counterclockwise. So left when south is east
	 */
	public void left() {
		if (currentPosition==null) return;
		Position newPosition = currentPosition.turnLeft();
		place(newPosition);
	}
	/**
	 * - LEFT and RIGHT will rotate the robot 90 degrees in 
	 * 	the specified direction without changing the position of the robot.
	 * 
	 * 	NOTE: I understand right as clockwise. So right when south is west
	 */
	public void right() {
		if (currentPosition==null) return;
		Position newPosition = currentPosition.turnRight();
		place(newPosition);
		
	}
	/**
	 * - PLACE will put the toy robot on the table in position X,Y 
	 * and facing NORTH,  SOUTH, EAST or WEST.

	 * We want it to be thread safe, so position are never reused.
	 * @param strPosition String containing the Position 
	 */
	/*
	 */
	public synchronized void place(Position newPosition) {
		if (newPosition==null) return;
		if(!Board.isValidPosition(newPosition)) return;
		currentPosition=newPosition;
	}



}
