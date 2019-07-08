package git.wangwangyuwan.demo.service;

import git.wangwangyuwan.demo.mapper.UserMapper;
import git.wangwangyuwan.demo.model.User;
import git.wangwangyuwan.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> dbUser = userMapper.selectByExample(userExample);
        if(null != dbUser && dbUser.size() > 0){
            user.setId(dbUser.get(0).getId());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(user.getId());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateByExample(user, example);
        }else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);

        }

    }
}
