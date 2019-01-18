package com.jiu;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiu.mapper.TUserMapper;
import com.jiu.model.TUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JiuUserApplicationTests {


    @Autowired
    private TUserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void selectAll() {
        log.info("selectAll...");
        List<TUser> list = userMapper.selectList(null);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void addUser() {
        log.info("addUser...");
        TUser user = new TUser();
        user.setUserName("lukai111111");
        user.setPassWord("12345634234");
        user.setPhone("19908623115");
        int i = userMapper.insert(user);
        log.info("插入" + i + "条");
    }

    @Test
    public void selectPage() {
        IPage page = new Page(2, 1);
        IPage<TUser> iPage = userMapper.selectPage(page, null);
        log.info("total==>" + iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void find() {
        Map queryMap = new HashMap();
        queryMap.put("user_id", 1007);
        queryMap.put("phone", "19908623115");
        AbstractWrapper wrapper = new QueryWrapper<TUser>();
//		wrapper.allEq(queryMap);
//		wrapper.eq("user_id",1007);
        wrapper.in("user_name", "lukai");
        TUser user = userMapper.selectOne(wrapper);
        System.out.println(user.toString());
    }


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        TUser user = new TUser();
        user.setUserName("test");
        user.setPassWord("123456");
        user.setPhone("199");
        ValueOperations<String, TUser> operations = redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user, 1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists = redisTemplate.hasKey("com.neo.f");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}
