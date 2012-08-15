This is a project trying to solve the assignment a job assignment. The assignment asks to design a software managing a robot inside a board of 5x5 cells. The typical movements area

PLACE X,Y,F
MOVE
LEFT
RIGHT
REPORT

and the main requirement is that the robot desn't fall outside the board. F is the facing and can be either NORTH, SOUTH, EAST or WEST.

I didn't asked for permission to publish the assignment so I cannot reproduce it.

I haven't made a maven project to minimize requirements. 

Apart from source code, there is a file testing the engine (called RobotTest), and 
a jar that should be easy to test (something like "java -jar robot.jar" should be enough)

Main assumption is that turn left must be counterclockwise. So left when south is east.

2012-08-Gonzalo Fernandez-Victorio
