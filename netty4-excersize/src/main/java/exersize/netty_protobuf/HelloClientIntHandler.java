package exersize.netty_protobuf;

import exersize.netty_protobuf.ProtoBuf.PacketProtoBuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaixiaotong on 2016-8-11.
 */
public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(HelloClientIntHandler.class);

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HelloClientInHandler.channelRead:"+ctx.toString());
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("Server said:" + new String(result1));
        result.release();
    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloClientInHandler.channelActive");
//        String msg = "Are you ok?";
//        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
//        encoded.writeBytes(msg.getBytes());
//        ctx.write(encoded);

//        for (int i = 0; i < 2; i++) {
            ctx.write(reqPacket(1));
//        }


        ctx.flush();
    }

    private PacketProtoBuf.PacketProto reqPacket(int id) {
        PacketProtoBuf.PacketProto.Builder builder = PacketProtoBuf.PacketProto.newBuilder();


        PacketProtoBuf.RedisRequestProto.Builder requestBuilder =  PacketProtoBuf.RedisRequestProto.newBuilder();
        requestBuilder.setCommand("get");
        requestBuilder.setKey("puttest");
        requestBuilder.setNamespace(1);

        PacketProtoBuf.RedisResponseProto.Builder responseBuilder = PacketProtoBuf.RedisResponseProto.newBuilder();


        builder.setIsFinished(false);
        builder.setMsgId(id);
        builder.setRedisRequestProto(requestBuilder);
        builder.setRedisResponseProto(responseBuilder);

//        byte[] array = new byte[100];
//        ByteString dataBytes = ByteString.copyFrom(array);

        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
