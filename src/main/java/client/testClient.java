package client;

import common.Blog;
import common.User;
import service.BlogService;
import service.UserService;

public class testClient {
    public static void main(String[] args) {
//        SimpleRPCClient simpleRPCClient = new SimpleRPCClient("127.0.0.1",8899);
        RPCClient rpcClient = new NettyRPCClient("127.0.0.1",8899);
        ClientProxy clientProxy = new ClientProxy(rpcClient);
        System.out.println("客户端启动");


        UserService userService = clientProxy.getProxy(UserService.class);
        User user = userService.getUserById(10);
        System.out.println("查询得到user："+ user);
        User userInsert = User.builder()
                .userName("zhangsan")
                .id(10)
                .sex(true)
                .build();
        Integer integer = userService.insertUserId(userInsert);
        System.out.println("插入user："+integer);



        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blog = blogService.getBlogById(100);
    }

}
