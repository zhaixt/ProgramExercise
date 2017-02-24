package exersize.netty_protobuf;

import exersize.netty_protobuf.ProtoBuf.PacketProtoBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaixiaotong on 2016-8-10.
 */
public class HelloServerInHandler extends ChannelInboundHandlerAdapter{
    private static Logger logger = LoggerFactory
            .getLogger(HelloServerInHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("HelloServerInHandler.channelRead:"+ctx.toString());
        PacketProtoBuf.PacketProto packetProto  = (PacketProtoBuf.PacketProto)msg;
        if(1==packetProto.getMsgId()){
            //   if("Orange".equals(people.getName())){
            System.out.println("accept client people:[" + packetProto.getRedisRequestProto().getCommand() + "]");
//            ctx.writeAndFlush(resp(people.getId()));
        }

//        ByteBuf result = (ByteBuf) msg;
//        byte[] result1 = new byte[result.readableBytes()];
//        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
//        result.readBytes(result1);
//        String resultStr = new String(result1);
//        // 接收并打印客户端的信息
//        System.out.println("Client said:" + resultStr);
//        // 释放资源，这行很关键
//        result.release();
//
//        // 向客户端发送消息
//        String response = "I am ok!";
//        // 在当前场景下，发送的数据必须转换成ByteBuf数组
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);
//        ctx.flush();
    }

    private PacketProtoBuf.PacketProto respPacket(int id) {
        PacketProtoBuf.PacketProto.Builder builder = PacketProtoBuf.PacketProto.newBuilder();


        PacketProtoBuf.RedisRequestProto.Builder requestBuilder =  PacketProtoBuf.RedisRequestProto.newBuilder();
        requestBuilder.setCommand("get");
        requestBuilder.setKey("puttest");
        requestBuilder.setNamespace(1);

        PacketProtoBuf.RedisResponseProto.Builder responseBuilder = PacketProtoBuf.RedisResponseProto.newBuilder();
        responseBuilder.setCode(0);
        responseBuilder.setMessage("success");


        builder.setIsFinished(false);
        builder.setMsgId(id);
        builder.setRedisRequestProto(requestBuilder);
        builder.setRedisResponseProto(responseBuilder);

//        byte[] array = new byte[100];
//        ByteString dataBytes = ByteString.copyFrom(array);


        return builder.build();
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
