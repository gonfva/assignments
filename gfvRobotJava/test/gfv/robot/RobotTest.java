package gfv.robot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RobotTest {
    private Robot robot;
    /*
     * Reuse Robot object
     */
	@Before 
    public void initialize() {
        robot= new Robot();
     }

	/*	
	 * PLACE 0,0,NORTH
	 * MOVE
	 * REPORT
	 * Output: 0,1,NORTH
	 */
	@Test
	public void exampleA() {
		robot.place(new Position(0,0,Facing.NORTH));
		robot.move();
		assertEquals(robot.report(), new Position(0,1,Facing.NORTH));
	}

	/*	
	 * PLACE 0,0,NORTH
	 * LEFT
	 * REPORT
	 * Output: 0,0,WEST
	 */
	@Test
	public void exampleB() {
		robot.place(new Position(0,0,Facing.NORTH));
		robot.left();
		assertEquals(robot.report(), new Position(0,0,Facing.WEST));
	}

	/* 
	 * PLACE 1,2,EAST
	 * MOVE
	 * MOVE
	 * LEFT
	 * MOVE
	 * REPORT
	 * Output: 3,3,NORTH
	 */
	@Test
	public void exampleC() {
		robot.place(new Position(1,2,Facing.EAST));
		robot.move();
		robot.move();		
		robot.left();
		robot.move();
		assertEquals(robot.report(), new Position(3,3,Facing.NORTH));
	}
	/*
	 * I understand left as counterclockwise. So left when south is east
	 * I understand right as clockwise. So right when south is west
	 */
	@Test
	public void testRotations(){
		robot.place(new Position(0,0,Facing.SOUTH));
		robot.left();
		assertEquals(robot.report(), new Position(0,0,Facing.EAST));
		robot.place(new Position(0,0,Facing.SOUTH));
		robot.right();
		assertEquals(robot.report(), new Position(0,0,Facing.WEST));
	}
	/*
	 * PLACE 0,0.SOUTH
	 * Try to move and position is the same
	 * Rotate right, move and position is the same
	 * PLACE 4,4,NORTH
	 * Try to move NORTH or EAST and position is the same
	 */
	@Test
	public void testBordersMovements(){
		//South border
		robot.place(new Position(0,0,Facing.SOUTH));
		robot.move();
		assertEquals(robot.report(),new Position(0,0,Facing.SOUTH));
		//West border;
		robot.right();
		assertEquals(robot.report(),new Position(0,0,Facing.WEST));
		robot.move();
		assertEquals(robot.report(),new Position(0,0,Facing.WEST));
		//North border
		robot.place(new Position(4,4,Facing.NORTH));
		robot.move();
		assertEquals(robot.report(), new Position(4,4,Facing.NORTH));
		//East border
		robot.right();
		assertEquals(robot.report(), new Position(4,4,Facing.EAST));
		robot.move();
		assertEquals(robot.report(), new Position(4,4,Facing.EAST));
	}
	/*
	 * PLACE 0, 0, NORTH to know the position
	 * PLACE -1, -1, NORTH is ignored
	 * PLACE 5, 5, NORHT is ignored
	 * 
	 */
	@Test
	public void testBordersPlacement(){
		robot.place(new Position(0,0,Facing.NORTH));
		robot.place(new Position(-1,-1,Facing.NORTH));
		assertEquals(robot.report(), new Position(0,0,Facing.NORTH));
		robot.place(new Position(5,5,Facing.NORTH));
		assertEquals(robot.report(), new Position(0,0,Facing.NORTH));
	}
	/*
	 * New Robot
	 * MOVE
	 * REPORT expected null 
	 * PLACE -1, -1, NORTH 
	 * REPORT expected null
	 * PLACE 0, 0, NORTH
	 * REPORT expected 0, 0, NORTH
	 * 
	 */
	@Test
	public void testInitalPlacement(){
		Robot newRobot=new Robot();
		newRobot.move();
		assertNull(newRobot.report());
		robot.place(new Position(-1,-1,Facing.NORTH));
		assertNull(newRobot.report());
		robot.place(new Position(0,0,Facing.NORTH));
		assertEquals(robot.report(), new Position(0,0,Facing.NORTH));
	}	
}
