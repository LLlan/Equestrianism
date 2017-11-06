package com.weixin.servlet;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.weixin.dao.WeiXinDao;
import com.weixin.dao.WeiXinDaoImpl;
import com.weixin.model.OAuthAccessToken;
import com.weixin.model.UserEntity;
import com.weixin.staticOrFinal.WeiXinStaticFinalValue;
import com.weixin.util.DateUtil;

public class OAuthUserNewServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(OAuthUserNewServlet.class.getName());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
		String code = request.getParameter("code");
	    log.info("code:"+code);
	    WeiXinDao dao=new WeiXinDaoImpl();
	    UserEntity entity=null;
		try {
           /* OAuthAccessToken oac =new OAuthAccessToken();
			
			oac.setOpenid(code);
			
			entity=new UserEntity();
			entity.setNickname("张三");
			entity.setHeadimgurl("image/2.png");*/
			
			OAuthAccessToken oac = dao.getOAuthAccessToken(WeiXinStaticFinalValue.AppId, WeiXinStaticFinalValue.AppSecret,code);//通过code换取网页授权access_token
			log.info("--------------------"+oac.getAccessToken()+";"+oac.getRefreshToken()+";"+oac.getScope()+";"+oac.getOpenid());
			entity=dao.acceptOAuthUserNews(oac.getAccessToken(),oac.getOpenid());//获取用户信息
			log.info("--------------------"+entity.getNickname()+";"+entity.getCountry());
			request.getSession().removeAttribute("order");
			request.getSession().removeAttribute("name");
			request.getSession().removeAttribute("logo");
			request.getSession().removeAttribute("sex");
			request.getSession().removeAttribute("address");
			request.getSession().removeAttribute("openid");
			request.getSession().setAttribute("order","E"+new Date().getTime()+DateUtil.getRandom(6));
			request.getSession().setAttribute("name",entity.getNickname());
			request.getSession().setAttribute("logo",entity.getHeadimgurl());
			request.getSession().setAttribute("sex",entity.getSex());
			request.getSession().setAttribute("address",entity.getCountry()+entity.getProvince()+entity.getCity());
			request.getSession().setAttribute("openid",entity.getOpenid());
				
		  response.sendRedirect("PhoneIndex.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
