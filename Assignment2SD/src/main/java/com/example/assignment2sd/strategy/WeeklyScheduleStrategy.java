package com.example.assignment2sd.strategy;

public class WeeklyScheduleStrategy implements IStrategy{

    @Override
    public int addWeekFlag(int weekFlag) {
        return weekFlag+1;
    }
}
