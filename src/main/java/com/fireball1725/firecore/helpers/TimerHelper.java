package com.fireball1725.firecore.helpers;

import com.fireball1725.firecore.event.EventTimerClock;
import com.fireball1725.firecore.util.ITimerCallback;
import org.apache.commons.lang3.time.StopWatch;

public class TimerHelper {
    private StopWatch stopWatch;
    private EventTimerClock timer;
    private ITimerCallback timerCallback;
    private long timeMaxTime;
    private long timeOffset = 0;

    public TimerHelper instance;

    public TimerHelper(ITimerCallback timerCallback, long timeMaxTime) {
        stopWatch = new StopWatch();
        this.timerCallback = timerCallback;
        this.timeMaxTime = timeMaxTime;

        this.timer = new EventTimerClock();
    }

    public void startTimer() {
        timer.setTimerHelper(this);
        new Thread(timer).start();
        stopWatch.start();
    }

    public void restartTimer(long timeOffset) {
        this.timeOffset = timeOffset;
        startTimer();
    }

    public void stopTimer() {
        if (!stopWatch.isStopped())
            stopWatch.stop();
    }

    public void pauseTimer() {
        if (stopWatch.isStarted() && !stopWatch.isSuspended())
            stopWatch.suspend();
    }

    public void resumeTimer() {
        if (stopWatch.isSuspended())
            stopWatch.resume();
    }

    public void killTimer() {
        stopTimer();
        timer.kill();
    }

    public boolean checkTimer() {
        if (stopWatch.isStarted() && !stopWatch.isSuspended()) {
            if (stopWatch.getTime() + this.timeOffset >= this.timeMaxTime) {
                stopTimer();
                timerCallback.timerDone();
                return true;
            }
        }
        return false;
    }

    public boolean isTimerStarted() {
        return stopWatch.isStarted();
    }

    public boolean isTimerSuspended() {
        return stopWatch.isSuspended();
    }

    public long getCurrentTime() {
        return stopWatch.getTime() + this.timeOffset;
    }

    public long getMaxTime() {
        return this.timeMaxTime;
    }
}
