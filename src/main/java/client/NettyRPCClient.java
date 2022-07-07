package client;

import common.RPCRequest;
import common.RPCResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import server.ServiceProvider;

public class NettyRPCClient implements RPCClient{

    private static final Bootstrap bootstrap ;
    private static final EventLoopGroup evenLoopGroup;
    private String host;
    private int port;

    public NettyRPCClient(String host,int port) {
        this.host = host;
        this.port = port;
    }
    //客户端初始化
    static {
        evenLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(evenLoopGroup).channel(NioSocketChannel.class)
                .handler(new NettyRPCClientHandler());
    }


    /**
     *
     * Netty传输都是异步的，，发送Requst，会立刻返回，而不是想要的response，所以需要处理一下
     *
     */

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            Channel channel = channelFuture.channel();

            //发送数据
            channel.writeAndFlush(request);
            channel.closeFuture().sync();

            // 阻塞的获得结果，通过给channel设计别名，获取特定名字下的channel中的内容（这个在handlder中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 实际上不应通过阻塞，可通过回调函数
            AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
            RPCResponse response = channel.attr(key).get();

            System.out.println(response);
            return response;


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
