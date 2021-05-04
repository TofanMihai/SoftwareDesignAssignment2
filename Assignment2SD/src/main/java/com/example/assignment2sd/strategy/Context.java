package com.example.assignment2sd.strategy;

public class Context {
    private IStrategy strategy;

    public Context(IStrategy chosenStrategy)
    {
        this.strategy = chosenStrategy;
    }

    public IStrategy getStrategy()
    {
        return this.strategy;
    }

    public int executeStrategy(int weekFlag)
    {
        return strategy.addWeekFlag(weekFlag);
    }
}
