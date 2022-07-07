package service;


import common.User;

import java.util.Random;
import java.util.UUID;

/**
 * author:ZhangYu
 * date:2022/6/2915:39
 * description:
 **/
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        System.out.println("客户端查询了" + id + "的用户");
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id).sex(random.nextBoolean()).build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功");
        return 1;
    }
}
