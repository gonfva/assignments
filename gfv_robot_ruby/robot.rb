class Robot
  VALID_X = 0..4
  VALID_Y = 0..4
  VALID_F = [:NORTH,:SOUTH,:EAST,:WEST]
  TURN_RIGHT = {:SOUTH=>:WEST,:NORTH=>:EAST,:EAST=>:SOUTH,:WEST=>:NORTH}
  TURN_LEFT = {:SOUTH=>:EAST,:NORTH=>:WEST,:EAST=>:NORTH,:WEST=>:SOUTH}
  MOVE = {:SOUTH=>[0,-1],:NORTH=>[0,1],:EAST=>[1,0],:WEST=>[-1,0]}

  def initialize
    @accepting=false
  end

  def place(cols,rows,facing)
    x=cols.to_i
    y=rows.to_i
    f=facing.to_sym
    if is_valid?(x,y,f)
      @x=x
      @y=y
      @f=f
      @accepting=true
    end
  end

  def left
      return unless @accepting
      @f=TURN_LEFT[@f]
  end

  def right
      return unless @accepting
      @f=TURN_RIGHT[@f] 
  end

  def move
      return unless @accepting
      delta=MOVE[@f]
      x = @x + delta[0]
      y = @y + delta[1]
      if is_valid?(x,y)
        @x=x
        @y=y
      end
  end

  def report
    return nil unless @accepting
    "#{@x.to_s},#{@y.to_s},#{@f}"
  end

protected
  def is_valid?(x,y,f=@f)
      VALID_X.include?(x) && VALID_Y.include?(y) && VALID_F.include?(f)
  end 
end