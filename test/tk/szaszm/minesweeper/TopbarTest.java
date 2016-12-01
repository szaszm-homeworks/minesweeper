package tk.szaszm.minesweeper;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by marci on 2016.12.01..
 */
public class TopbarTest {
    @Test
    public void testTimer() {
        Topbar topbar = new Topbar();
        Assert.assertEquals(0, topbar.getCounter());
        topbar.startTimer();
        topbar.incrementCounter();
        Assert.assertEquals(1, topbar.getCounter());
        topbar.stopTimer();
        Assert.assertEquals(1, topbar.getCounter());
        topbar.resetTimer();
        Assert.assertEquals(0, topbar.getCounter());
    }
}
