package exersize.netty3_multi_handler;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Created by zhaixiaotong on 2016-8-17.
 */
public class InboundHandler2 extends ChannelInboundHandlerAdapter {
    private static Log	logger	= LogFactory.getLog(InboundHandler2.class);

    @Override
    // 读取Client发送的信息，并打印出来
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("InboundHandler2.channelRead: ctx :" + ctx);
        System.out.println("InboundHandler2.channelRead: ctx :" + ctx);
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        String resultStr = new String(result1);
        System.out.println("Client said:" + resultStr);
        result.release();

        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("InboundHandler2.channelReadComplete");
        System.out.println("InboundHandler2.channelReadComplete");
        ctx.flush();
    }
}
