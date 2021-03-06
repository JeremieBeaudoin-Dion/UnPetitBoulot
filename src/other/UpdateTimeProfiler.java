package other;

import java.util.EnumMap;

/**
 * @author Mia Beaudoin-Dion
 */
class UpdateTimeProfiler {

    enum Timers {
        images, objects, between
    }

    private EnumMap<Timers, Long> timeMap;

    private long timeAll;

    private long lastTime;

    UpdateTimeProfiler() {
        timeMap = new EnumMap<>(Timers.class);

        for (Timers timerID : Timers.values()) {
            timeMap.put(timerID, 0L);
        }

        timeAll = 0;
    }

    public void start() {
        lastTime = System.nanoTime();
    }

    public void ping(Timers timerID) {
        long updateTime = System.nanoTime() - lastTime;

        timeMap.put(timerID, timeMap.get(timerID) + updateTime);

        timeAll += updateTime;

        lastTime = System.nanoTime();
    }

    public void print() {
        System.out.println("----------------- Profiler ------------------");

        for (Timers timerID : Timers.values()) {
            System.out.println(" % for " + timerID + " : " + (timeMap.get(timerID) / timeAll) * 100L);
            System.out.println(timeMap.get(timerID));
        }
        System.out.println(timeAll);
        System.out.println("-------------------- End --------------------");
    }


}
