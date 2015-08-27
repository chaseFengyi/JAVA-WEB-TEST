package com.mymuji.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mymuji.dao.SelectInfo;
import com.mymuji.model.MujiInfoBean;

public class MujiInfoService {
	/**
	 * @param info
	 * @return
	 */
	public static MujiInfoBean checkByAccountNumAndPassword(MujiInfoBean info){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = SelectInfo.selectInfo("accountNum", info.getAccountNum());
		if(list.size() == 0){
			info.setAccountNum(null);
			return info;
		}else if(list.get(0).get("password").toString()
				.equals(info.getPassword())){
			info.setMujiID((int) list.get(0).get("mujiID"));
			info.setAccountNum(list.get(0).get("accountNum").toString());
		}else{
			info.setPassword(null);
		}
		return info;
	}
}
