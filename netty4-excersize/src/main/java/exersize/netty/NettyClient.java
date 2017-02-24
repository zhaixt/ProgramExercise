package exersize.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * Created by zhaixiaotong on 2016-5-24.
 */
public class NettyClient {
    private Channel channel;
    public void connect(int port, String host) {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new StringDecoder());
//                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            //发起异步链接操作
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channel = channelFuture.channel();

            channelFuture.addListener(new ChannelFutureListener() {

                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("client connected");
                    } else {
                        System.out.println("server attemp failed");
                        future.cause().printStackTrace();
                    }

                }
            });

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭，释放线程资源
            group.shutdownGracefully();
        }
    }

//    public static final Channel getChannel(String host, int port) {
//        Channel channel = null;
//        try {
//            channel = bootstrap.connect(host, port).sync().channel();
//            } catch (Exception e) {
//            logger.error(
//                    String.format("连接Server(IP[%s],PORT[%s])失败", host, port), e);
//            return null;
//            }
//        return channel;
//        }
//
//    public static void sendMsg(String msg) throws Exception {
//        if (channel != null) {
//            channel.writeAndFlush(msg).sync();
//            } else {
//            logger.warn("消息发送失败,连接尚未建立!");
//           }
//        }
    public static void main(String[] args) {
        new NettyClient().connect(9080, "localhost");
    }



}