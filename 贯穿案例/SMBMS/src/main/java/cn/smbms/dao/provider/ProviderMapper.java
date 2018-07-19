package cn.smbms.dao.provider;

import java.util.List;
import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

public interface ProviderMapper {
	
	/**
	 * 增加供应商
	 * @param provider
	 * @return
	 * @throws Exception
	 */
	int add(Provider provider);

	/**
	 * 通过供应商名称、编码获取供应商列表-模糊查询-providerList
	 * @param proName
	 * @return
	 * @throws Exception
	 */
	List<Provider> getProviderList(@Param("proName") String proName, @Param("proCode") String proCode);
	
	/**
	 * 通过proId删除Provider
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	int deleteProviderById(String delId);

	/**
	 * 通过proId获取Provider
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Provider getProviderById(String id);
	
	/**
	 * 修改供应商信息
	 * @return
	 * @throws Exception
	 */
	int modify(Provider provider);

}
