package client;

import common.RPCRequest;
import common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@AllArgsConstructor
public class SimpleRPCClient implements RPCClient{

    private String host;
    private int port;

    //客户端发起一次请求调用，Socket建立连接，发起请求Request，得到响应Response
    //这里的reuqest是封装好的，不同的service需要不同封装，客户端只知道service接口
    // 需要一层动态代理根据反射封装不同的service
    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        try {
            Socket socket = new Socket(host,port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);
            oos.writeObject(request);
            oos.flush();

            RPCResponse response = (RPCResponse) ois.readObject();

            return response;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
