package exersize.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zhaixiaotong on 2016-5-26.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf clientMessage;
    private BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

    public ClientHandler() {

        byte [] req = "Call-User-Service".getBytes();
        clientMessage = Unpooled.buffer(req.length);
        clientMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(clientMessage);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte [] req = new byte[buf.readableBytes()];

        buf.readBytes(req);

        String message = new String(req,"UTF-8");

        System.out.println("Netty-Client:Receive Message,"+ message);
        System.out.println("user input：");
        ctx.channel().writeAndFlush("write code");
//        ctx.writeAndFlush(sin.readLine());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
    }
}
