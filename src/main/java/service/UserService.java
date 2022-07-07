package service;


import common.User;

/**
 * author:ZhangYu
 * date:2022/6/2915:37
 * description:
 **/
public interface UserService {
    User getUserById(Integer id);
    Integer insertUserId(User user);
}
