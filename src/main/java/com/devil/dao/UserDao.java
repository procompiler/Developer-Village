package com.devil.dao;

import java.util.List;
import java.util.Map;
import com.devil.domain.Block;
import com.devil.domain.User;

public interface UserDao {
  int insert(User user) throws Exception;
  int update(User user) throws Exception;
  int delete(int no) throws Exception;
  User findByNo(Map<String, Object> params) throws Exception;
  List<User> findByName(String name) throws Exception;
  List<User> findAll(String keyword) throws Exception;
  User findByEmailPassword(Map<String, Object> map) throws Exception;
  User findId(Map<String, Object> map) throws Exception;
  User findPassword(Map<String, Object> map) throws Exception;
  int inactive(int no) throws Exception;
  List<User> findByFollower(User user) throws Exception;
  int insertBlocked(Block block) throws Exception;
  List<User> findFollower(User user) throws Exception;
  int updateLoginTimeStamp(User user) throws Exception;
}
