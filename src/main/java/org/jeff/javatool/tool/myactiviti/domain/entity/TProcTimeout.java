package org.jeff.javatool.tool.myactiviti.domain.entity;

public class TProcTimeout extends TProcTimeoutKey {
    private Integer time;

    private Short state;

    private Integer timeBeyond;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Integer getTimeBeyond() {
        return timeBeyond;
    }

    public void setTimeBeyond(Integer timeBeyond) {
        this.timeBeyond = timeBeyond;
    }
}