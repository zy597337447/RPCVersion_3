package common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * author:ZhangYu
 * date:2022/6/2915:27
 * description:上个例子中只传递了一个id过去，显然不合理，
 *              因为服务端不会只有一个服务一个方法，只传参数不会知道要调用哪个方法
 *              因此一个RPC请求中，client发送应该需要调用的service接口名、方法名、参数、参数类型
 *              这样服务端能根据这些信息用反射调用相应的方法
 *              依然使用java自带的序列化方法
 **/
@Data
@Builder
public class RPCRequest implements Serializable {
    //服务类名
    private String interfaceName;
    //方法名
    private String methodName;
    //参数列表
    private Object[] params;
    //参数类型
    private Class<?>[] paramsTypes;

}
