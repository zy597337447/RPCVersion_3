package server;


import service.BlogService;
import service.BlogServiceImpl;
import service.UserService;
import service.UserServiceImpl;

/**
 * author:ZhangYu
 * date:2022/6/2917:12
 * description: 封装多个服务类
 **/
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
//        Map<String,Object> serviceProvide = new HashMap<>();
//
//        //暴露两个服务接口，即在RPCServer中加一个HashMap
//        serviceProvide.put("com.zhang.myRPCVersion2.service.UserService",userService);
//        serviceProvide.put("com.zhang.myRPCVersion2.service.BlogService",blogService);

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer rpcServer = new NettyRPCServer(serviceProvider);

        rpcServer.start(8899);

    }


}
