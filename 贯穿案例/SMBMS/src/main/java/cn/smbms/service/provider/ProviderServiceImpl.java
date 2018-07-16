package cn.smbms.service.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.bill.BillMapper;
import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.tools.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ProviderServiceImpl implements ProviderService {

	private ProviderMapper providerDao;
	private BillMapper  billDao;
	private SqlSession sqlSession = MybatisUtil.getSession();
	public ProviderServiceImpl(){
		providerDao = sqlSession.getMapper(ProviderMapper.class);
		billDao = sqlSession.getMapper(BillMapper.class);
	}

	/**
	 * 新增供应商信息
	 * @param provider
	 * @return
	 */
	@Override
	public boolean add(Provider provider) {
		// TODO Auto-generated method stub
		try {
			if(providerDao.add(provider) > 0)
				sqlSession.commit();
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				sqlSession.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			if(sqlSession != null)
				sqlSession.close();
		}
		return false;
	}

	/**
	 * 获取所有供应商信息列表
	 * @param proName
	 * @param proCode
	 * @return
	 */
	@Override
	public List<Provider> getProviderList(String proName,String proCode) {
		// TODO Auto-generated method stub
		List<Provider> providerList = null;
		try {
			providerList = providerDao.getProviderList(proName,proCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return providerList;
	}

	/**
	 * 业务：根据ID删除供应商表的数据之前，需要先去订单表里进行查询操作
	 * 若订单表中无该供应商的订单数据，则可以删除
	 * 若有该供应商的订单数据，则不可以删除
	 * 返回值billCount
	 * 1> billCount == 0  删除---1 成功 （0） 2 不成功 （-1）
	 * 2> billCount > 0    不能删除 查询成功（0）查询不成功（-1）
	 * 
	 * ---判断
	 * 如果billCount = -1 失败
	 * 若billCount >= 0 成功
	 */
	@Override
	public int deleteProviderById(String delId) {
		// TODO Auto-generated method stub
		int billCount = -1;
		try {
			billCount = billDao.getBillCountByProviderId(delId);
			if(billCount == 0){
				providerDao.deleteProviderById(delId);
				sqlSession.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			billCount = -1;
			try {
				sqlSession.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			if(sqlSession != null)
				sqlSession.close();
		}
		return billCount;
	}

	/**
	 * 根据ID获取Provider
	 * @param id
	 * @return
	 */
	@Override
	public Provider getProviderById(String id) {
		// TODO Auto-generated method stub
		Provider provider = null;
		try{
			provider = providerDao.getProviderById(id);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			provider = null;
		}
		return provider;
	}

	/**
	 * 修改供应商信息
	 * @param provider
	 * @return
	 */
	@Override
	public boolean modify(Provider provider) {
		// TODO Auto-generated method stub
		try {
			if(providerDao.modify(provider) > 0)
				sqlSession.commit();
				return true;
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return false;
	}

}
