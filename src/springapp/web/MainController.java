package springapp.web;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Post;
import springapp.domain.User;
import springapp.service.DbService;

@Controller
public class MainController {
	
	@RequestMapping(value="/main.html")
	public ModelAndView enterMain(HttpServletRequest request){
	    
		ModelAndView mav = new ModelAndView("main");
		
	    return mav;
	}
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpServletRequest request){
	    
		request.getSession().removeAttribute("loggedInUser");
		
		return "redirect:main.html";
	}
	
	@RequestMapping(value="/viewPosts.html")
	public ModelAndView viewPosts(HttpServletRequest request) throws SQLException, ParseException{
		
		ArrayList<Post> posts = DbService.getPostsByTimestamp();
		
		ModelAndView mav = new ModelAndView("posts");
		mav.addObject("posts", posts);
		
	    return mav;
	}
	
	@RequestMapping(value="/submitPost.html")
	public String submitPost(HttpServletRequest request) throws SQLException, ParseException{
		
		String postText = request.getParameter("postText");
		String username = (String) request.getSession().getAttribute("loggedInUser");
		
		DbService.submitPost(postText, username);
		
		return "redirect:viewPosts.html";
	}
}
