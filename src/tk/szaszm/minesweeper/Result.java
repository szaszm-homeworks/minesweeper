package tk.szaszm.minesweeper;

import java.io.Serializable;

/**
 * Created by marci on 2016.11.30..
 */
public class Result implements Comparable<Result>, Serializable {
    static final long serialVersionUID = 7618276187671219726L;
    public String name;
    public int points;

    public Result() {
        this("", 0);
    }

    public Result(String name, int points) {
        this.name = name;
        this.points = points;
    }

    @Override
    public int compareTo(Result result) {
        return points - result.points;
    }
}
