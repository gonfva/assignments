require "./robot"

class RobotUI
  def initialize()
    @robot=Robot.new
  end

  def parse()
    command=gets
    case command
      when /PLACE ([\-]?\d*),([\-]?\d*),(NORTH|SOUTH|EAST|WEST)/
        matches=/PLACE ([\-]?\d*),([\-]?\d*),(NORTH|SOUTH|EAST|WEST)/.match(command)
        @robot.place matches[1],matches[2],matches[3]
      when /MOVE/
        @robot.move
      when /LEFT/
        @robot.left
      when /RIGHT/
        @robot.right
      when /REPORT/
        report=@robot.report
        puts report if report
    end
  end
 
end

if __FILE__ == $0
  robot=RobotUI.new
  while true
    robot.parse
  end
end