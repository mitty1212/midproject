package cn.edu.zucc.domain.dao;

//import com.coding.studiodemo.User.User;
import cn.edu.zucc.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);

    User findByNameAndPassword(String name,String password);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

    @Query("from User u where u.name like %:pattern%")
    List<User> findByNameLike(@Param("pattern") String pattern);


}
