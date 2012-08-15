This is a project trying to solve in Ruby a job assignment. The assignment asks to design a software managing a robot inside a board of 5x5 cells. The typical movements area

PLACE X,Y,F
MOVE
LEFT
RIGHT
REPORT

and the main requirement is that the robot desn't fall outside the board. F is the facing and can be either NORTH, SOUTH, EAST or WEST.

I didn't asked for permission to publish the assignment so I cannot reproduce it.

I didn't want to include RSPEC/CUCUMBER or make a Rails project to focus on the requirements.

Apart from source code, there is a file testing the console (called tc_robot_console.rb). It tests via stdin and stdout, not the engine. The test cases can be launched with something like "ruby tc_robot_console.rb"

The program should be easy to launch (something like "ruby robot_ui.rb").

Main assumption is that turn left must be counterclockwise. So left when south is east.

2012-08-Gonzalo Fernandez-Victorio
