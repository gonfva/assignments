
require "./robot_ui"
require "test/unit"

#Based on trick from https://gist.github.com/194554
#Should have used blocks to avoid
class InputFaker
  def feedStrings(strings)
    @strings = strings
  end

  def gets
    next_string = @strings.shift
    next_string
  end

end

class OutputFaker
  def initialize
    @result=[]
  end
  def write(str)
    @result << str.chomp
  end
  def clear
    @result=[]
  end
  def result
    @result.join 
  end
end

class TestRobot < Test::Unit::TestCase
  def setup
    $stdin = InputFaker.new
    $stdout = OutputFaker.new
    @robotUI=RobotUI.new unless @robotUI
  end
  def teardown
    $stdin = STDIN
    $stdout = STDOUT   
  end
  def actual_test(array_of_inputs,expected_output)

    $stdin.feedStrings(array_of_inputs)
    array_of_inputs.size.times{@robotUI.parse()}
    assert_equal(expected_output,$stdout.result)
 
  end
  #Example A
	#PLACE 0,0,NORTH
	#MOVE
	#REPORT
	#Output: 0,1,NORTH 
  def test_example_a
    actual_test(["PLACE 0,0,NORTH",
             "MOVE",
             "REPORT"], "0,1,NORTH")
  end
  ###Example B
 	#PLACE 0,0,NORTH
	#LEFT
	#REPORT
	#Output: 0,0,WEST
  def test_example_b
    actual_test(["PLACE 0,0,NORTH",
                 "LEFT",
                 "REPORT"],"0,0,WEST")
  end 
  
  ###Example C
	#PLACE 1,2,EAST
	#MOVE
	#MOVE
	#LEFT
	#MOVE
	#REPORT
	#Output: 3,3,NORTH
  def test_example_c
    actual_test(["PLACE 1,2,EAST",
                 "MOVE",
                 "MOVE",
                 "LEFT",
                 "MOVE",
                 "REPORT"],"3,3,NORTH")
  end 
  
  ###Correct rotation when south
  #PLACE 0,0,SOUTH
  #LEFT
  #REPORT
  #Output: 0,0,EAST
  #PLACE 0,0,SOUTH
  #RIGHT
  #REPORT
  #Output: 0,0,WEST
  def test_correct_rotation
    actual_test(["PLACE 0,0,SOUTH",
                 "LEFT",
                 "REPORT"],"0,0,EAST")
    $stdout.clear
    actual_test(["PLACE 0,0,SOUTH",
                 "RIGHT",
                 "REPORT"],"0,0,WEST")
  end

  ###Out of borders movement
  #PLACE 0,0,SOUTH
  #MOVE
  #REPORT
  #Output: 0,0,SOUTH
  #RIGHT
  #REPORT
  #Output: 0,0, WEST
  #MOVE
  #REPORT
  #Output: 0,0, WEST
  #PLACE 4,4,NORTH
  #MOVE
  #REPORT
  #Output: 4,4,NORTH
  #RIGHT
  #REPORT
  #Output: 4,4,EAST
  #MOVE
  #REPORT
  #Output: 4,4,EAST
  def test_out_of_borders_movement
    actual_test(["PLACE 0,0,SOUTH",
                 "MOVE",
                 "REPORT"],"0,0,SOUTH")
    $stdout.clear
    actual_test(["RIGHT",
                 "REPORT"],"0,0,WEST")
    $stdout.clear
    actual_test(["MOVE",
                 "REPORT"],"0,0,WEST")
    $stdout.clear
    actual_test(["PLACE 4,4,NORTH",
                 "MOVE",
                 "REPORT"],"4,4,NORTH")
    $stdout.clear
    actual_test(["RIGHT",
                 "REPORT"],"4,4,EAST")
    $stdout.clear
    actual_test(["MOVE",
                 "REPORT"],"4,4,EAST")                 
  end
  ###Out of borders placement
	#PLACE 0,0,NORTH
  #PLACE -1,-1,NORTH
  #REPORT
  #Output: 0,0,NORTH
  #PLACE 5,5,NORTH
  #REPORT
  #Output: 0,0,NORTH
  def test_out_of_borders_placement
    actual_test(["PLACE 0,0,NORTH",
                 "PLACE -1,-1,NORTH",
                 "REPORT"],"0,0,NORTH")
    $stdout.clear
    actual_test(["PLACE 5,5,NORTH",
                 "REPORT"],"0,0,NORTH")
  end
  ###Initial placement. Ignore anything until correct inital placement
	###New Robot
  #MOVE
	#REPORT
  #Output:
  #PLACE -1,-1,NORTH 
	#REPORT
  #Output:
  #PLACE 0,0,NORTH
	#REPORT
  #Output: 0,0,NORTH
  def test_ignore_until_initial_placement
    @robotUI=RobotUI.new
    actual_test(["MOVE",
                 "REPORT"],"")
    $stdout.clear
    actual_test(["PLACE -1,-1,NORTH",
                 "REPORT"],"")
    $stdout.clear
    actual_test(["PLACE 0,0,NORTH",
                 "REPORT"],"0,0,NORTH")
  end
end