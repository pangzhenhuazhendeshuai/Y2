package cn.smbms.service.bill;
import java.sql.Connection;
import java.util.List;
import cn.smbms.dao.BaseDao;
import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.tools.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class BillServiceImpl implements BillService {
	
	private BillMapper billDao;
	private SqlSession sqlSession = MybatisUtil.getSession();
	public BillServiceImpl(){
		billDao = sqlSession.getMapper(BillMapper.class);
	}

	/**
	 * 新增订单信息
	 * @param bill
	 * @return
	 */
	@Override
	public boolean add(Bill bill) {
		boolean flag = false;
		try {
			if(billDao.add(bill) > 0){
				flag = true;
				sqlSession.commit();
			}
		} catch (Exception e) {
			flag=false;
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return flag;
	}

	/**
	 * 获取所有订单信息
	 * @param bill
	 * @return
	 */
	@Override
	public List<Bill> getBillList(Bill bill) {
		List<Bill> billList = null;
		System.out.println("query productName ---- > " + bill.getProductName());
		System.out.println("query providerId ---- > " + bill.getProviderId());
		System.out.println("query isPayment ---- > " + bill.getIsPayment());
		try {
			billList = billDao.getBillList(bill);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sqlSession != null)
				sqlSession.close();
		}
		return billList;
	}

	/**
	 * 根据ID删除订单信息
	 * @param delId
	 * @return
	 */
	@Override
	public boolean deleteBillById(String delId) {
		boolean flag = false;
		try {
			if(billDao.deleteBillById(delId) > 0){
				flag = true;
				sqlSession.commit();
			}
		} catch (Exception e) {
			flag = false;
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return flag;
	}

	/**
	 * 根据ID获取订单信息
	 * @param id
	 * @return
	 */
	@Override
	public Bill getBillById(String id) {
		Bill bill = null;
		try{
			bill = billDao.getBillById(id);
		}catch (Exception e) {
			e.printStackTrace();
			bill = null;
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return bill;
	}

	/**
	 * 根据ID修改订单信息
	 * @param bill
	 * @return
	 */
	@Override
	public boolean modify(Bill bill) {
		boolean flag = false;
		try {
			if(billDao.modify(bill) > 0){
				flag = true;
				sqlSession.commit();
			}
		} catch (Exception e) {
			flag = false;
			sqlSession.rollback();
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return flag;
	}

}
