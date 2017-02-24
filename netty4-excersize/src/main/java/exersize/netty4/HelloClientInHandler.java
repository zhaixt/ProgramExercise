package exersize.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zhaixiaotong on 2016-8-11.
 */
public class HelloClientInHandler extends ChannelInboundHandlerAdapter {

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client rece msg");
        
        ByteBuf byteBuf = (ByteBuf) msg;
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		
		String reponseText = new String(bytes,"UTF-8");
		System.out.println("client received data :  "+reponseText);
		System.out.println("Client ## channelRead -->>-- ");
    }
    // 连接成功后，向server发消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] bytes = "Query Time Order ".getBytes();
    	ByteBuf byteBuf = Unpooled.buffer(bytes.length);
    	byteBuf.writeBytes(bytes);
        ctx.writeAndFlush(byteBuf);
        
        System.out.println("client active ");
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {


//        HelloClient helloClient = new HelloClient();
//        helloClient.doConnect();
    }
}
