package exersize.redis;

import java.io.Serializable;

/**
 * Created by zhaixiaotong on 2016-5-26.
 */
public class RedisParams implements Serializable {
    private String command;
    private String[] args;

    public RedisParams(String command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
