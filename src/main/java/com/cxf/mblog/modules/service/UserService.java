package com.cxf.mblog.modules.service;

import com.cxf.mblog.base.lang.Consts;
import com.cxf.mblog.modules.data.AccountProfile;
import com.cxf.mblog.modules.data.UserVO;
import com.cxf.mblog.modules.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

/**
 * @author xfchai
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2020/11/17 11:11:00
 */
@CacheConfig(cacheNames = Consts.CACHE_USER)
public interface UserService {
    /**
     * 分页查询
     *
     * @param pageable
     * @param name
     */
    Page<UserVO> paging(Pageable pageable, String name);

    Map<Long, UserVO> findMapByIds(Set<Long> ids);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    AccountProfile login(String username, String password);

    /**
     * 登录,用于记住登录时获取用户信息
     *
     * @param id
     * @return
     */
    AccountProfile findProfile(Long id);

    /**
     * 注册
     *
     * @param user
     */
    UserVO register(UserVO user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @CacheEvict(key = "#user.getId()")
    AccountProfile update(UserVO user);

    /**
     * 修改用户信息
     *
     * @param email
     * @return
     */
    @CacheEvict(key = "#id")
    AccountProfile updateEmail(long id, String email);

    /**
     * 查询单个用户
     *
     * @param userId
     * @return
     */
    @Cacheable(key = "#userId")
    UserVO get(long userId);

    UserVO getByUsername(String username);

    UserVO getByEmail(String email);

    /**
     * 修改头像
     *
     * @param id
     * @param path
     * @return
     */
    @CacheEvict(key = "#id")
    AccountProfile updateAvatar(long id, String path);

    /**
     * 修改密码
     *
     * @param id
     * @param newPassword
     */
    void updatePassword(long id, String newPassword);

    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(long id, String oldPassword, String newPassword);

    /**
     * 修改用户状态
     *
     * @param id
     * @param status
     */
    void updateStatus(long id, int status);

    long count();

    Page<User> findAll(Pageable pageable);

    /**
     * 校验密码正确性
     *
     * @param user
     * @param password
     * @return
     */
    boolean matches(AccountProfile user, String password);
}
