package com.fireball1725.firecore.event;

import com.fireball1725.firecore.FireCore;
import com.fireball1725.firecore.FireCoreMod;
import com.fireball1725.firecore.helpers.TimerHelper;

public class EventTimerClock implements Runnable {
    public TimerHelper timerHelper;
    private boolean timerDone = false;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                timerDone = timerHelper.checkTimer();
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                //FireCore.logHelper.info(ex);
            }

            if (timerDone) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void kill() {
        timerDone = true;
    }

    public void setTimerHelper(TimerHelper timerHelper) {
        this.timerHelper = timerHelper;
    }
}
