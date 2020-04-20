package com.lml.part.spring.mapper;

import com.lml.part.spring.domain.MysqlLock;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shuishan
 * @date 2020/4/20
 */
@Repository
public interface MysqlLockMapper {

    @Insert("insert into mysql_lock (`key`) values (#{key}) on duplicate key update total = total + 1")
    int insert(MysqlLock lock);

    @Insert({"insert into mysql_lock (`key`) values ",
            "<foreach collection='locks' item='lock' separator=','> ",
            "( #{lock.key} )",
            "</foreach> "})
    int batchInsert(List<MysqlLock> locks);

    @Delete("delete from mysql_lock where `key` = #{key}")
    int delete(MysqlLock lock);

}
