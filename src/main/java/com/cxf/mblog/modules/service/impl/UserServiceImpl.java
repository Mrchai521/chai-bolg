package com.cxf.mblog.modules.service.impl;

import com.cxf.mblog.base.lang.EntityStatus;
import com.cxf.mblog.base.lang.MtonsException;
import com.cxf.mblog.base.utils.BeanMapUtils;
import com.cxf.mblog.base.utils.MD5;
import com.cxf.mblog.modules.data.AccountProfile;
import com.cxf.mblog.modules.data.BadgesCount;
import com.cxf.mblog.modules.data.UserVO;
import com.cxf.mblog.modules.entity.User;
import com.cxf.mblog.modules.repository.RoleRepository;
import com.cxf.mblog.modules.repository.UserRepository;
import com.cxf.mblog.modules.service.MessageService;
import com.cxf.mblog.modules.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @author xfchai
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2020/11/17 11:26:00
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 分页
     *
     * @param pageable
     * @param name
     * @return
     */
    @Override
    public Page<UserVO> paging(Pageable pageable, String name) {
        Page<User> page = userRepository.findAll((root, query, builder) -> {
            Predicate predicate = builder.conjunction();
            if (StringUtils.isAllBlank(name)) {
                predicate.getExpressions().add(builder.like(root.get("name"), "%" + name + "%"));
            }
            query.orderBy(builder.desc(root.get("id")));
            return predicate;
        }, pageable);
        List<UserVO> list = new ArrayList<>();
        page.getContent().forEach(n -> list.add(BeanMapUtils.copy(n)));
        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    @Override
    public Map<Long, UserVO> findMapByIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }
        List<User> list = userRepository.findAllById(ids);
        Map<Long, UserVO> map = new HashMap<>();
        list.forEach(po -> map.put(po.getId(), BeanMapUtils.copy(po)));
        return map;
    }

    @Override
    @Transactional
    public AccountProfile login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        Assert.state(StringUtils.equals(user.getPassword(), password), "密码错误！");
        user.setLastLogin(Calendar.getInstance().getTime());
        userRepository.save(user);
        AccountProfile accountProfile = BeanMapUtils.copyPassport(user);
        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setMessages(messageService.unread4Me(accountProfile.getId()));

        accountProfile.setBadgesCount(badgesCount);
        return accountProfile;
    }

    @Override
    public AccountProfile findProfile(Long id) {
        User po = userRepository.findById(id).orElse(null);

        Assert.notNull(po, "账户不存在");

//		Assert.state(po.getStatus() != Const.STATUS_CLOSED, "您的账户已被封禁");
        po.setLastLogin(Calendar.getInstance().getTime());

        AccountProfile u = BeanMapUtils.copyPassport(po);

        BadgesCount badgesCount = new BadgesCount();
        badgesCount.setMessages(messageService.unread4Me(u.getId()));

        u.setBadgesCount(badgesCount);

        return u;
    }

    @Override
    @Transactional
    public UserVO register(UserVO user) {
        Assert.notNull(user, "Parameter user can not be null!");

        Assert.hasLength(user.getUsername(), "用户名不能为空!");
        Assert.hasLength(user.getPassword(), "密码不能为空!");

        User check = userRepository.findByUsername(user.getUsername());

        Assert.isNull(check, "用户名已经存在!");

        if (StringUtils.isNotBlank(user.getEmail())) {
            User emailCheck = userRepository.findByEmail(user.getEmail());
            org.springframework.util.Assert.isNull(emailCheck, "邮箱已经存在!");
        }

        User po = new User();

        BeanUtils.copyProperties(user, po);

        if (StringUtils.isBlank(po.getName())) {
            po.setName(user.getUsername());
        }

        Date now = Calendar.getInstance().getTime();
        po.setPassword(MD5.md5(user.getPassword()));
        po.setStatus(EntityStatus.ENABLED);
        po.setCreated(now);

        userRepository.save(po);

        return BeanMapUtils.copy(po);
    }

    @Override
    @Transactional
    public AccountProfile update(UserVO user) {
        User po = userRepository.findById(user.getId()).get();
        po.setName(user.getName());
        po.setSignature(user.getSignature());
        userRepository.save(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    @Transactional
    public AccountProfile updateEmail(long id, String email) {
        User po = userRepository.findById(id).get();

        if (email.equals(po.getEmail())) {
            throw new MtonsException("邮箱地址没做更改");
        }

        User check = userRepository.findByEmail(email);

        if (check != null && check.getId() != po.getId()) {
            throw new MtonsException("该邮箱地址已经被使用了");
        }
        po.setEmail(email);
        userRepository.save(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    public UserVO get(long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            return BeanMapUtils.copy(optional.get());
        }
        return null;
    }

    @Override
    public UserVO getByUsername(String username) {
        return BeanMapUtils.copy(userRepository.findByUsername(username));
    }

    @Override
    public UserVO getByEmail(String email) {
        return BeanMapUtils.copy(userRepository.findByEmail(email));
    }

    @Override
    @Transactional
    public AccountProfile updateAvatar(long id, String path) {
        User po = userRepository.findById(id).get();
        po.setAvatar(path);
        userRepository.save(po);
        return BeanMapUtils.copyPassport(po);
    }

    @Override
    @Transactional
    public void updatePassword(long id, String newPassword) {
        User po = userRepository.findById(id).get();
        Assert.hasLength(newPassword, "密码不能为空!");
        po.setPassword(MD5.md5(newPassword));
        userRepository.save(po);
    }

    @Override
    @Transactional
    public void updatePassword(long id, String oldPassword, String newPassword) {
        User po = userRepository.findById(id).get();
        Assert.hasLength(newPassword, "密码不能为空!");
        Assert.isTrue(MD5.md5(oldPassword).equals(po.getPassword()), "当前密码不正确");
        po.setPassword(MD5.md5(newPassword));
        userRepository.save(po);
    }

    @Override
    public void updateStatus(long id, int status) {
        User po = userRepository.findById(id).get();
        po.setStatus(status);
        userRepository.save(po);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
