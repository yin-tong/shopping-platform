import com.ssm.dao.IRoleDao;
import com.ssm.dao.IUserDao;
import com.ssm.domain.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class test {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IUserDao userDao;

    @Test
    public void find(){
        List<Role> all = roleDao.findAll();
        System.out.println(all.size());
    }


    @Test
    public void findAll(){
        List<Role> roles = roleDao.findRolesLikeRoleDesc("员");
        if (roles.size()==0){
            System.out.println("空");
            return;
        }
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Test
    public void test2(){
        System.out.println(userDao.findById("887880a2439211ecaa6b005056c00001"));
    }
}
