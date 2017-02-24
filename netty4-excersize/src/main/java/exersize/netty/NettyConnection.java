package exersize.netty;

import io.netty.channel.Channel;

import java.util.concurrent.BlockingQueue;

/**
 * Created by zhaixiaotong on 2016-5-27.
 */
public class NettyConnection {
    private BlockingQueue<String> nettyResponse;
    private Channel channel;
}
