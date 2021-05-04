package com.example.assignment2sd.strategy;

public class MonthlyScheduleStrategy implements IStrategy{

    @Override
    public int addWeekFlag(int weekFlag) {
        return weekFlag+4;
    }
}
