package exersize.netty_protobuf;

import exersize.netty_protobuf.ProtoBuf.PacketProtoBuf;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by zhaixiaotong on 2016-8-10.
 */
public class HelloServer {
    public void start(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 注册handler

                    // protobufDecoder仅仅负责编码，并不支持读半包，所以在之前，一定要有读半包的处理器。
                    // 有三种方式可以选择：
                    // 使用netty提供ProtobufVarint32FrameDecoder
                    // 继承netty提供的通用半包处理器 LengthFieldBasedFrameDecoder
                    // 继承ByteToMessageDecoder类，自己处理半包
                    // 半包的处理
                    ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    // 需要解码的目标类
                    ch.pipeline().addLast(new ProtobufDecoder(PacketProtoBuf.PacketProto.getDefaultInstance()));

                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());

                    ch.pipeline().addLast(new ProtobufEncoder());

                    ch.pipeline().addLast(new HelloServerInHandler());
                }
            });

            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        HelloServer server = new HelloServer();
        server.start(8001);
    }
}
