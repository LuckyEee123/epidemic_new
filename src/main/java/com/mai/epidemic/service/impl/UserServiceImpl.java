package com.mai.epidemic.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.commons.constants.RedisConstants;
import com.mai.epidemic.commons.constants.SysConstants;
import com.mai.epidemic.commons.utils.BeanCopyUtils;
import com.mai.epidemic.commons.utils.IDUtil;
import com.mai.epidemic.commons.utils.RedisCache;
import com.mai.epidemic.pojo.User;
import com.mai.epidemic.pojo.dto.UserDto;
import com.mai.epidemic.pojo.vo.PageVo;
import com.mai.epidemic.pojo.vo.UsersNum;
import com.mai.epidemic.service.UserService;
import com.mai.epidemic.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2022-12-20 21:50:37
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisCache redisCache;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public Result getUsersList(Integer pageNum, Integer pageSize, String nickname) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getIsDelete, 0)
                .like(User::getNickname, nickname);

        Page<User> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<UserDto> userDtos = BeanCopyUtils.copyBeanList(page.getRecords(), UserDto.class);
        PageVo pageVo = new PageVo(userDtos, page.getTotal());

        return Result.success(pageVo);
    }

    @Override
    public Result getUsersNum() {
        String usersNum = null;
        String cache = redisCache.getCacheObject(RedisConstants.USERS_NUM);
        if (StrUtil.isBlank(cache)) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getIsDelete, 0);
            List<User> userList = list(wrapper);
            usersNum = String.valueOf(userList.size());
            redisCache.setCacheObject(RedisConstants.USERS_NUM, usersNum, RedisConstants.CACHE_USERS_NUM_TIMEOUT, TimeUnit.SECONDS);
        } else {
            usersNum = cache;
        }

        return Result.success("获取成功！", usersNum);
    }

    @Override
    public Result addUser(User user) {

        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getIsDelete, 0)
                .eq(User::getUsername, user.getUsername());

        User dbUser = userMapper.selectOne(userWrapper);
        if (ObjectUtil.isNotNull(dbUser)) {
            return Result.error("该用户已存在");
        }

        user.setUid(IDUtil.FastUid());
        user.setCreateTime(DateUtil.date());
        user.setUpdateTime(DateUtil.date());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        return userMapper.insert(user) == 1 ? Result.success("添加成功", user) : Result.error("添加失败！");

    }

    @Override
    public Result deleteUserById(Integer uid) {

        return userMapper.deleteById(uid) == 1 ? Result.success("删除成功") : Result.error("删除失败");
    }

    @Override
    public Result updateUser(User user) {
        LambdaUpdateWrapper<User> userWrapper = new LambdaUpdateWrapper<>();
        userWrapper.eq(User::getIsDelete, SysConstants.IS_NOT_DELETE)
                .set(User::getUpdateTime, DateUtil.date());

        return update(user, userWrapper) ? Result.success("更新成功！", user) : Result.error("更新失败！");
    }
}




