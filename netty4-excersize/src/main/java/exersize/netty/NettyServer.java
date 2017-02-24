package exersize.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;

/**
 * Created by zhaixiaotong on 2016-4-18.
 */
public class NettyServer {
    private static int port= 9080;

    /**用于分配处理业务线程的线程组个数 */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2; //默认
    /** 业务出现线程大小*/
    protected static final int BIZTHREADSIZE = 4;
    public static void main(String[] args){

        /*
     * NioEventLoopGroup实际上就是个线程池,
     * NioEventLoopGroup在后台启动了n个NioEventLoop来处理Channel事件,
     * 每一个NioEventLoop负责处理m个Channel,
     * NioEventLoopGroup从NioEventLoop数组里挨个取出NioEventLoop来处理Channel
     */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        ServerBootstrap bootstrap = null;
        ChannelFuture future = null;
        try {
//            bootstrap = new ServerBootstrap();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
    //                        final BoltSession boltSession = new BoltSession();
//                            ch.pipeline().addLast(new StringDecoder());
//                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new ServerHandler());
                    }});

            //绑定端口、同步等待
            future = serverBootstrap.bind(port).sync();
            System.out.println("Server已经启动，监听端口: " + port+ "， 等待客户端注册。。。");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
