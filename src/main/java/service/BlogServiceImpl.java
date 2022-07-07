package service;


import common.Blog;

/**
 * author:ZhangYu
 * date:2022/6/2917:06
 * description:
 **/
public class BlogServiceImpl implements BlogService{
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("我的博客").useId(22).build();
        System.out.println("客户端查询到了" + id + "博客");
        return blog;
    }
}
