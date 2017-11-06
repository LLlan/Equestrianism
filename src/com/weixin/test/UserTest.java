package com.weixin.test;

//import xyg.ysq.model.User;
//import xyg.ysq.page.Page;
//import xyg.ysq.service.UserService;



public class UserTest {
	/*private ApplicationContext ac=null;
	private UserService entityService=null;
	
	/*public static void main(String[] args){
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		//UserService userService=(UserService) ac.getBean("userService");
	}*/
	
	
	//@Before
	/*public void SetValue(){
		System.out.println("======================初始化开始===========================");
		ac=new ClassPathXmlApplicationContext("beans.xml");
		entityService=(UserService) ac.getBean("userService");
	}
	
	
	@Test
	public void AddEntity(){
		System.out.println("======================增加信息===========================");
		User entity=new User();
		entity.setId("123456");
		entity.setName("昵称");
		entity.setPassword("123456");
		entity.setCreateDate(new Date());
		String resultId=entityService.AddEntity(entity);
		System.out.println(resultId);
	}
	@Test
	public void SelectList(){
		System.out.println("======================获取所有信息===========================");
		User entity=new User();
		List<User> list=entityService.SelectAllByHQL(entity);
		System.out.println("用户数量:"+list.size());
		for (User entity2 : list) {
			System.out.println(entity2.toString());
		}
	}
	@Test
	public void SelectEntity(){
		System.out.println("======================单个查询信息===========================");
		User entity=entityService.SelectEntityGet("123456");	
		System.out.println(entity);
	}
	@Test
	public void DeleteEntity(){
		System.out.println("======================删除信息===========================");
		boolean falge=entityService.DeleteEntityById("123456");
		System.out.println(falge);
	}
	@Test
	public void SelectListByPage(){
		System.out.println("======================分页获取所有信息===========================");
		User entity=new User();
		Page<User> page=new Page<User>();
		page=entityService.SelectAllByPage(page, entity);
		System.out.println(page);
	}*/
}
