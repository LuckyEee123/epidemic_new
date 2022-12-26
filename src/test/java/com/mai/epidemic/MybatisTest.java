package com.mai.epidemic;

import com.mai.epidemic.mapper.MenuMapper;
import com.mai.epidemic.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    @Test
    public void SelectTest() {
        System.out.println(userMapper.selectList(null));
    }

    @Test
    public void selectPermsByUserId() {
        System.out.println(menuMapper.selectPermsByUserId(2));
    }

}
