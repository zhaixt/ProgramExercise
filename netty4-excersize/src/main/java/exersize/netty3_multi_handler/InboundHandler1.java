package exersize.netty3_multi_handler;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Created by zhaixiaotong on 2016-8-17.
 */
public class InboundHandler1 extends ChannelInboundHandlerAdapter {
    private static Log logger = LogFactory.getLog(InboundHandler1.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("InboundHandler1.channelRead: ctx :" + ctx);
        System.out.println("InboundHandler1.channelRead: ctx :" + ctx);

        // 通知执行下一个InboundHandler
        //ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("InboundHandler1.channelReadComplete");
        System.out.println("InboundHandler1.channelReadComplete");
        ctx.flush();
    }
}
