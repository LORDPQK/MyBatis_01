package M_01.dao.dao;


import M_01.dao.entity.User;

public interface UserDao {
    User queryUserById(Integer id);
}
