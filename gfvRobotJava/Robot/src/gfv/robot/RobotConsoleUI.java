package gfv.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RobotConsoleUI {
	Robot robotClient=new Robot();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RobotConsoleUI console=new RobotConsoleUI();
		console.execute();

	}

	private void execute() {
		System.out.println("Welcome my master. You can write any of the following commands(or 'END'):");
		System.out.println("\tPLACE X,Y,F");
		System.out.println("\tMOVE");
		System.out.println("\tLEFT");
		System.out.println("\tRIGHT");
		System.out.println("\tREPORT");

		//  open up standard input
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    try {
	    	for(;;){
	    		String line = br.readLine();
	    		this.parseLine(line);
	    	}
	    } catch (IOException ioe) {
	        System.out.println("Problem reading!");
	    }
	}

	private void parseLine(String line) {
		if (line.startsWith("PLACE ")){
			robotClient.place(parsePosition(line.substring(6)));
			return;
		}
		if (line.equals("MOVE")){
			robotClient.move();
			return;
		}
		if (line.equals("LEFT")){
			robotClient.left();
			return;
		}
		if (line.equals("RIGHT")){
			robotClient.right();
			return;
		}
		if (line.equals("REPORT")){
			Position result=robotClient.report();
			if (result!=null)
				System.out.println(result.toString());
			return;
		}
		if (line.equals("END")){
			System.exit(0);
		}
	}

	private Position parsePosition(String strPosition){
		if (strPosition==null) return null;
		String[] params;
		params=strPosition.split(",");
		if (params.length!=3) return null;
		Position Position=null;
		try{
			int c=Integer.parseInt(params[0].trim());
			int r=Integer.parseInt(params[1].trim());
			Facing f=Facing.valueOf(params[2].trim());
			Position=new Position(c,r,f);
		}catch(Exception ex){}
		return Position;
	}
}
