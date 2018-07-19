package cn.smbms.service.role;

import java.util.List;

import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Role;
import cn.smbms.tools.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class RoleServiceImpl implements RoleService{
	
	private RoleMapper roleDao;
	private SqlSession sqlSession = MybatisUtil.getSession();
	public RoleServiceImpl(){
		roleDao = sqlSession.getMapper(RoleMapper.class);
	}

	@Override
	public List<Role> getRoleList() {
		List<Role> roleList = null;
		try {
			roleList = roleDao.getRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return roleList;
	}
	
}
