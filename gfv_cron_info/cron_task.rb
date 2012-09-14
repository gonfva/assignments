class CronTask
  def initialize(cron_hour, cron_minute, command)
    @command=command
    @cron_hour=cron_hour
    @cron_min=cron_minute
  end
  
  def next_fire(current_hour, current_minute)
    next_hour, next_minute, next_day=calc_schedule(current_hour, current_minute)
    "%02d:%02d %s - %s"%[next_hour,next_minute,next_day,@command]
  end
  
  def to_s
    "#{@cron_min} #{@cron_hour} #{@command}"
  end
private
  def valid?(next_hour,next_minute)
    if (@cron_hour=='*' || next_hour==@cron_hour.to_i)
      if (@cron_min=='*' || next_minute==@cron_min.to_i)
        return true 
      end
    end
    false
  end
  def calc_schedule(curr_hour, curr_min)
    next_hour=curr_hour.to_i
    next_minute=curr_min.to_i
    next_day=:today
    until valid?(next_hour,next_minute)
      next_minute +=1
      if next_minute>=60
        next_minute=0
        next_hour +=1
      end
      if next_hour>=24
        return [25,61,:never] unless next_day==:today
        next_hour=0
        next_day=:tomorrow
      end
    end
    [next_hour,next_minute,next_day]
  end
end
