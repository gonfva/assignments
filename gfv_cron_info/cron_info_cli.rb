require "./cron_task"

class CronInfoCli
  
  def initialize()
    @tasks=[]
  end

  def parse(task_line=$stdin.gets)
    matches=/([0-5][0-9]|\*) ([0-1][0-9]|2[0-3]|\*) (.*)/.match(task_line)
    if matches
      ct=CronTask.new(matches[2],matches[1],matches[3])
      @tasks<<ct
      return ct
    end
    nil
  end

  def next_fire(curr_hour, curr_min)
    next_fires=[]
    @tasks.each do |task|
      next_fires<<task.next_fire(curr_hour, curr_min)
    end
    next_fires
  end
end

if __FILE__ == $0
  croninfocli=CronInfoCli.new()
  while croninfocli.parse
  end
  param=/([0-1][0-9]|2[0-3]):([0-5][0-9])/.match(ARGV[0])
  exit(-1) unless param
  curr_hour=param[1].to_i
  curr_min=param[2].to_i
  #puts "#{curr_hour}:#{curr_min}"
  croninfocli.next_fire(curr_hour,curr_min).each {|next_fire| puts next_fire}
end
