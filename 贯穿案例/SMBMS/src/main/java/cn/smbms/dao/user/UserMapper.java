package cn.smbms.dao.user;

import java.sql.Connection;
import java.util.List;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	/**
	 * 增加用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int add(@Param("user") User user);

	/**
	 * 通过userCode获取User
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	User getLoginUser(String userCode);

	/**
	 * 通过条件查询-userList
	 * @param userName
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	List<User> getUserList(@Param("userName") String userName, @Param("userRole") int userRole,
								  @Param("currentPageNo") int currentPageNo, @Param("pageSize") int pageSize);
	/**
	 * 通过条件查询-用户表记录数
	 * @param userName
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	int getUserCount(@Param("userName") String userName, @Param("userRole") int userRole);
	
	/**
	 * 通过userId删除user
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	int deleteUserById(@Param("delId") Integer delId);
	
	
	/**
	 * 通过userId获取user
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User getUserById(@Param("id") String id);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int modify(User user);
	
	
	/**
	 * 修改当前用户密码
	 * @param id
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	int updatePwd(int id, String pwd);


	User queryUserByUserNameAndPassword(@Param("userCode") String userCode, @Param("userPassword")	String userPassword);


}
