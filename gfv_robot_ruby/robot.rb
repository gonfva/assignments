class Robot
  VALID_X = 0..4
  VALID_Y = 0..4
  VALID_F = ["NORTH","SOUTH","EAST","WEST"]
  
  def initialize
    @accepting=false
  end
  
  def place(cols,rows,facing)
    x=cols.to_i
    y=rows.to_i
    f=facing
    if is_valid?(x,y,f)
      @x=x
      @y=y
      @f=f
      @accepting=true
    end
  end
  
  def left
    case @f
      when "SOUTH"
        @f="EAST"
      when "NORTH"
        @f="WEST"
      when "EAST"
        @f="NORTH"
      when "WEST"
        @f="SOUTH"
    end
  end
  
  def right
    case @f
      when "SOUTH"
        @f="WEST"
      when "NORTH"
        @f="EAST"
      when "EAST"
        @f="SOUTH"
      when "WEST"
        @f="NORTH"
    end  
  end
  
  def move
    case @f
      when "SOUTH"
        @y=@y-1 if VALID_Y === @y-1 
      when "NORTH"
        @y=@y+1 if VALID_Y === @y+1
      when "EAST"
        @x=@x+1 if VALID_X === @x+1
      when "WEST"
        @x=@x-1 if VALID_X === @x-1
    end  
  end
  
  def report
    return nil unless @accepting
    "#{@x.to_s},#{@y.to_s},#{@f}"
  end

protected
  def is_valid?(x,y,f)
    VALID_X.include?(x) && VALID_Y.include?(y) && VALID_F.include?(f)
  end 
end
