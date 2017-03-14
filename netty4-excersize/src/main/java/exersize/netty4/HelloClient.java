package exersize.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;


/**
 * Created by zhaixiaotong on 2016-8-11.
 */
public class HelloClient {
    private volatile  Bootstrap b;
    private volatile boolean closed = false;
    private volatile EventLoopGroup workerGroup;
    private  ChannelFutureListener channelFutureListener = null;
    public void close() {
        closed = true;
        workerGroup.shutdownGracefully();
        System.out.println("Stopped Tcp Client: ");
    }
    public void connect(final String host,final int port) throws Exception {
         workerGroup = new NioEventLoopGroup();

        try {
            b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.TCP_NODELAY, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HelloClientInHandler());
                }
            });

            // Start the client.
            ChannelFuture channelFuture = b.connect(host, port).sync();
            // setup channel, variables, connection, etc.
            Channel channel = channelFuture.channel();
            channel.write("=======init gg=====");

//            channelFuture.addListener(new ChannelFutureListener() {
//
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    if (future.isSuccess()) {
//                        System.out.println("client connected");
//                    } else {
//                        System.out.println("server attemp failed");
//                        future.cause().printStackTrace();
//                    }
//
//                }
//            });
            channelFutureListener = new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()) {

                        Thread.sleep(2000);
                        doConnect(host, port);
                    } else {
                        future.channel().closeFuture().addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture future) throws Exception {
                                doConnect(host, port);
                            }
                        });
                    }
                }
            };
            //断线重连机制
            channelFuture.addListener(channelFutureListener);
;            // Wait until the connection is closed.
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }
    public void doConnect(final String host,final int port){
        if (closed) {
            return;
        }

        ChannelFuture future = b.connect(host,port);

        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture f) throws Exception {
                if (f.isSuccess()) {
                    System.out.println("Started Tcp Client: ");
                } else {
                    System.out.println("Started Tcp Client Failed: ");
                    f.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            doConnect(host, port);
                        }
                    }, 3, TimeUnit.SECONDS);
//                    f.channel().eventLoop().schedule(() -> doConnect(host,port), 1, TimeUnit.SECONDS);
                }
            }
        });
    }
    public static void main(String[] args) throws Exception {
        HelloClient client = new HelloClient();
        client.connect("127.0.0.1", 8000);
    }
}
