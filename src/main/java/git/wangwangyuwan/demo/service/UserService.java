package git.wangwangyuwan.demo.service;

import git.wangwangyuwan.demo.mapper.UserMapper;
import git.wangwangyuwan.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());

        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(null == dbUser){
            userMapper.addUser(user);
        }else {
            user.setId(dbUser.getId());
            userMapper.update(user);
        }
    }
}
