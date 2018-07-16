package cn.smbms.service.user;

import java.util.List;
import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.tools.Constants;
import cn.smbms.tools.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * service层捕获异常，进行事务处理
 * 事务处理：调用不同dao的多个方法，必须使用同一个connection（connection作为参数传递）
 * 事务完成之后，需要在service层进行connection的关闭，在dao层关闭（PreparedStatement和ResultSet对象）
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService{
	
	private UserMapper userDao;
	private SqlSession sqlSession = MybatisUtil.getSession();
	public UserServiceImpl(){
		userDao = sqlSession.getMapper(UserMapper.class);
	}

    /**
     * 添加新用户
     * @param user
     * @return
     */
	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub
        try {
            int row = userDao.add(user);
            sqlSession.commit();
            if(row == 1)
                return true;
        } catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            return false;
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return false;
	}

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @return
     */
	@Override
	public User login(String userCode, String userPassword) {
		User user = null;
		try {
			user=userDao.queryUserByUserNameAndPassword(userCode,userPassword);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(sqlSession != null)
				sqlSession.close();
		}
		return user;
	}

    /**
     *获取所有用户列表并分页显示
     * @param queryUserName
     * @param queryUserRole
     * @param currentPageNo
     * @param pageSize
     * @return
     */
	@Override
	public List<User> getUserList(String queryUserName,int queryUserRole,int currentPageNo, int pageSize) {
        List<User> userList = null;
        try {
            userList = userDao.getUserList( queryUserName,queryUserRole,(currentPageNo-1)*Constants.pageSize,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
	}

	@Override
	public User selectUserCodeExist(String userCode) {
		// TODO Auto-generated method stub
//		Connection connection = null;
//		User user = null;
//		try {
//			connection = BaseDao.getConnection();
//			user = userDao.getLoginUser(connection, userCode);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			BaseDao.closeResource(connection, null, null);
//		}
		return null;
	}

    /**
     * 删除用户
     * @param delId
     * @return
     */
	@Override
	public boolean deleteUserById(Integer delId) {
		// TODO Auto-generated method stub
		try {
			if(userDao.deleteUserById(delId) > 0)
			    sqlSession.commit();
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
		}finally{
		    if(sqlSession != null)
		        sqlSession.close();
		}
		return false;
	}

	/**
	 * 根据ID获取User信息
	 * @param id
	 * @return
	 */
	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		User user = null;
		try{
			user = userDao.getUserById(id);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			user = null;
		}
		return user;
	}

    /**
     * 修改用户信息
     * @param user
     * @return
     */
	@Override
	public boolean modify(User user) {
		// TODO Auto-generated method stub
		try {
			if(userDao.modify(user) > 0)
				sqlSession.commit();
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			if(sqlSession != null)
				sqlSession.close();
		}
		return false;
	}

    /**
     * 修改密码
     * @param id
     * @param pwd
     * @return
     */
	@Override
	public boolean updatePwd(int id, String pwd) {
		// TODO Auto-generated method stub
//		boolean flag = false;
//		Connection connection = null;
//		try{
//			connection = BaseDao.getConnection();
//			if(userDao.updatePwd(connection,id,pwd) > 0)
//				flag = true;
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally{
//			BaseDao.closeResource(connection, null, null);
//		}
		return false;
	}

    /**
     * 获取用户表和角色表的-记录数
     * @param queryUserName
     * @param queryUserRole
     * @return
     */
	@Override
	public int getUserCount(String queryUserName, int queryUserRole) {
        // TODO Auto-generated method stub
        int count = 0;
        System.out.println("queryUserName ---- > " + queryUserName);
        System.out.println("queryUserRole ---- > " + queryUserRole);
        try {
            count = userDao.getUserCount( queryUserName,queryUserRole);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return count;
	}
	
}
