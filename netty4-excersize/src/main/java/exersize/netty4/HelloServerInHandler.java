package exersize.netty4;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
/**
 * Created by zhaixiaotong on 2016-8-10.
 */
public class HelloServerInHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println("HelloServerInHandler receice msg :");
  
        ByteBuf byteBuf = (ByteBuf) msg;
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		
		try {
			String reponseText = new String(bytes,"UTF-8");
			System.out.println("server received data :" + reponseText+"   ####  "+this.hashCode());
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//
//		ByteBuf responseByteBuf = Unpooled.copiedBuffer(new Date().toString().getBytes());
//        ctx.writeAndFlush(responseByteBuf);
        ctx.writeAndFlush("date for client");
        System.out.println("Server send a message-->>-- ");
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}