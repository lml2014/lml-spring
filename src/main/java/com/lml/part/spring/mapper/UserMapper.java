package com.lml.part.spring.mapper;

import com.lml.part.spring.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CREATE TABLE `user` (
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 * `name` varchar(200) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
 *
 * @author shuishan
 * @date 2020/4/4
 */
@Repository
public interface UserMapper {

    @Results(id = "user", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name")
    })
    @Select({"select * from user where id = #{id}"})
    User selectById(Long id);

    @Insert({"insert into user(name) values (#{name})"})
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(String name);

    @Update({"update user set name = #{name} where id = #{id}"})
    void update(User user);

    @Delete(("delete from user where id = #{id}"))
    int delete(Long id);

    @ResultMap("user")
    @Select("select * from user")
    List<User> selectAll();


}
