package exersize.netty3_multi_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Created by zhaixiaotong on 2016-8-17.
 */
public class OutboundHandler2 extends ChannelOutboundHandlerAdapter {
    private static Log	logger	= LogFactory.getLog(OutboundHandler2.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        logger.info("OutboundHandler2.write");
        System.out.println("OutboundHandler2.write");
        // 执行下一个OutboundHandler
        super.write(ctx, msg, promise);
    }
}
