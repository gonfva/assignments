
require "./cron_task"
require "./cron_info_cli"
require "test/unit"

class TestCronTask < Test::Unit::TestCase
  def test_both_wildcard
    ct=CronTask.new('*','*','/bin/command')
    assert_equal('12:00 today - /bin/command',ct.next_fire('12','00'))
  end
  def test_minute_wildcard
    ct1=CronTask.new('11','*','/bin/command')
    assert_equal('11:00 tomorrow - /bin/command',ct1.next_fire('12','00'))
    ct2=CronTask.new('13','*','/bin/command')
    assert_equal('13:00 today - /bin/command',ct2.next_fire('12','00'))
  end
  def test_hour_wildcard
    ct1=CronTask.new('*','59','/bin/command')
    assert_equal('12:59 today - /bin/command',ct1.next_fire('12','00'))
    ct2=CronTask.new('*','01','/bin/command')
    assert_equal('12:01 today - /bin/command',ct2.next_fire('12','00'))
  end
  def test_hour_minute
    ct1=CronTask.new('11','59','/bin/command')
    assert_equal('11:59 tomorrow - /bin/command',ct1.next_fire('12','00'))
    ct2=CronTask.new('12','01','/bin/command')
    assert_equal('12:01 today - /bin/command',ct2.next_fire('12','00'))
    ct2=CronTask.new('12','12','/bin/command')
    assert_equal('12:12 tomorrow - /bin/command',ct2.next_fire('12','13'))
    ct2=CronTask.new('*','*','/bin/command')
    assert_equal('12:13 today - /bin/command',ct2.next_fire('12','13'))
    ct2=CronTask.new('*','*','/bin/command')
    assert_equal('13:55 today - /bin/command',ct2.next_fire('13','55'))

  end

end
class TestCronInfoCli< Test::Unit::TestCase
  def test_parsing
    cli=CronInfoCli.new
    line="30 15 /bin/run_me_daily"
    task=cli.parse(line)
    assert_not_nil(task)
    assert_equal(task.to_s, line)
    line="* * /bin/run_me_every_minute"
    task=cli.parse(line)
    assert_not_nil(task)
    assert_equal(task.to_s, line)
  end
  def test_acceptance
    input=["30 15 /bin/run_me_daily",
           "45 * /bin/run_me_hourly",
           "* * /bin/run_me_every_minute",
           "* 19 /bin/run_me_sixty_times"]
    output=["15:30 tomorrow - /bin/run_me_daily",
           "16:45 today - /bin/run_me_hourly",
           "16:10 today - /bin/run_me_every_minute",
           "19:00 today - /bin/run_me_sixty_times"]
    cli=CronInfoCli.new      
    input.each {|input_line| cli.parse input_line}
    next_fires=cli.next_fire(16,10)
    output.each do |line|
      assert(next_fires.include?(line), 
            "Line '#{line}' does not exist in the output: #{next_fires.join("\n")}")
    end
  end
end
