package exersize.netty4;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by zhaixiaotong on 2017-3-1.
 */
public class Heartbeathandler extends ChannelDuplexHandler {
    @Override

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            String message = " idle";
            if (e.state() == IdleState.READER_IDLE) {
                //logger.info("[发送心跳探测消息READER_IDLE][" + message.toString().trim() + "]");
                ctx.writeAndFlush(message);
            } else if (e.state() == IdleState.WRITER_IDLE) {
                //logger.info("[发送心跳探测消息WRITER_IDLE][" + message.toString().trim() + "]");
                ctx.writeAndFlush(message);
            }
        }
    }
}
