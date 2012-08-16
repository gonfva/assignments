package gfv.robot;

public class Position {

	private static final String SEPARATOR=",";
	final int col;
	final int row;
	final Facing facing;


	
	public Position(int i, int j, Facing f) {
		col=i;
		row=j;
		facing=f;
	}
	/**
	 * Getter for the column
	 * @return x-value or column-value of the position
	 */
	public int getColumn() {
		return col;
	}
	/**
	 * Getter for the row
	 * @return y-value or row-value of the position
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Getter for the facing
	 * @return a value indicating the facing of type Enum
	 */
	public Facing getFacing() {
		return facing;
	}
	/**
	 * @return an string value representing this position
	 */
	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append(col);
		sb.append(SEPARATOR);
		sb.append(row);
		sb.append(SEPARATOR);
		sb.append(facing.toString());
		return sb.toString();
	}
	/**
	 * Returns a new position advancing 1 in the current facing
	 * 
	 * @return a new object
	 */
	public Position advance(){
		Position newPosition=null;
		switch(facing){
			case NORTH:
				newPosition=new Position(col, row+1, facing);
				break;
			case SOUTH:
				newPosition=new Position(col, row-1, facing);
				break;
			case EAST:
				newPosition=new Position(col+1, row, facing);
				break;
			case WEST:
				newPosition=new Position(col-1, row, facing);
				break;
		}
		return newPosition;
	}
	/**
	 * Returns a new Position turning left
	 */
	public Position turnLeft(){
		Position newPosition=null;
		switch(facing){
			case NORTH:
				newPosition=new Position(col, row, Facing.WEST);
				break;
			case SOUTH:
				newPosition=new Position(col, row, Facing.EAST);
				break;
			case EAST:
				newPosition=new Position(col, row, Facing.NORTH);
				break;
			case WEST:
				newPosition=new Position(col, row, Facing.SOUTH);
				break;
		}
		return newPosition;
		
	}
	/**
	 * Returns a new position turning Right
	 */	
	public Position turnRight(){
		Position newPosition=null;
		switch(facing){
			case NORTH:
				newPosition=new Position(col, row, Facing.EAST);
				break;
			case SOUTH:
				newPosition=new Position(col, row, Facing.WEST);
				break;
			case EAST:
				newPosition=new Position(col, row, Facing.SOUTH);
				break;
			case WEST:
				newPosition=new Position(col, row, Facing.NORTH);
				break;
		}
		return newPosition;
		
	}
	@Override
	public boolean equals(Object obj) {
	    boolean result = false;
	    if (obj instanceof Position) {
	    	Position that = (Position) obj;
	        result = (this.getColumn() == that.getColumn() && 
	        		this.getRow() == that.getRow() &&
	        		this.getFacing()==that.getFacing());
	    }
	    return result;
	}
}
enum Facing {
	NORTH,
	SOUTH,
	EAST,
	WEST
}

