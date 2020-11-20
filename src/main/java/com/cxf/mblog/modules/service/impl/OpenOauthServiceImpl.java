/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 cxf. All Rights Reserved
|   http://www.cxf.com
|
+---------------------------------------------------------------------------
*/
package com.cxf.mblog.modules.service.impl;

import com.cxf.mblog.base.utils.BeanMapUtils;
import com.cxf.mblog.base.utils.MD5;
import com.cxf.mblog.modules.data.OpenOauthVO;
import com.cxf.mblog.modules.data.UserVO;
import com.cxf.mblog.modules.entity.User;
import com.cxf.mblog.modules.entity.UserOauth;
import com.cxf.mblog.modules.repository.UserOauthRepository;
import com.cxf.mblog.modules.repository.UserRepository;
import com.cxf.mblog.modules.service.OpenOauthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 第三方登录授权管理
 * @author langhsu on 2015/8/12.
 */
@Service
@Transactional
public class OpenOauthServiceImpl implements OpenOauthService {
    @Autowired
    private UserOauthRepository userOauthRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVO getUserByOauthToken(String oauth_token) {
        UserOauth thirdToken = userOauthRepository.findByAccessToken(oauth_token);
        Optional<User> po = userRepository.findById(thirdToken.getId());
        return BeanMapUtils.copy(po.get());
    }

    @Override
    public OpenOauthVO getOauthByToken(String oauth_token) {
        UserOauth po = userOauthRepository.findByAccessToken(oauth_token);
        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    @Override
    public OpenOauthVO getOauthByUid(long userId) {
        UserOauth po = userOauthRepository.findByUserId(userId);
        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    @Override
    public boolean checkIsOriginalPassword(long userId) {
        UserOauth po = userOauthRepository.findByUserId(userId);
        if (po != null) {
            Optional<User> optional = userRepository.findById(userId);

            String pwd = MD5.md5(po.getAccessToken());
            // 判断用户密码 和 登录状态
            if (optional.isPresent() && pwd.equals(optional.get().getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveOauthToken(OpenOauthVO oauth) {
        UserOauth po = new UserOauth();
        BeanUtils.copyProperties(oauth, po);
        userOauthRepository.save(po);
    }

	@Override
	public OpenOauthVO getOauthByOauthUserId(String oauthUserId) {
		UserOauth po = userOauthRepository.findByOauthUserId(oauthUserId);
        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
	}

}
