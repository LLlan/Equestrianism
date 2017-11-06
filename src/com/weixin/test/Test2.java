package com.weixin.test;

import java.util.UUID;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int machineId = 1;//最大支持1-9个集群机器部署
			int hashCodeV = UUID.randomUUID().toString().hashCode();
			if(hashCodeV < 0) {//有可能是负数
			  hashCodeV = - hashCodeV;
			}
			// 0 代表前面补充0     
			// 4 代表长度为4     
			// d 代表参数为正数型
			System.out.println(machineId+String.format("%015d", hashCodeV)); 
	}

}
