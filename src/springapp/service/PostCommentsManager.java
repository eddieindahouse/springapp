package springapp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import springapp.dbcon.DbCon;
import springapp.domain.Post;
import springapp.domain.User;

@Component
public class PostCommentsManager {
	
	@Autowired
	private DbCon dbCon;
	
	@Autowired
	private UserManager userManager;
	
	public ArrayList<Post> getPostComments(String masterPostId) throws NumberFormatException, SQLException, ParseException{
		
		String query = "select * from post_comments where masterPost_id = " + masterPostId + " order by postIndex;";
	
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
	
		return getPostsFromResultSet(rs);
	}
	
	public void submitPostComment(String text, String userId, String masterPostId, String postIndex) throws NumberFormatException, SQLException, ParseException{
		
		String query = "insert into post_comments (text, user_id, masterPost_id, postIndex) values ('" + text + "', '" + userId + "', '" + masterPostId + "', '" + postIndex + "');";
	
		dbCon.makeConnectionAndExecuteQuery(query);
	}
	
	public String getIncrementedPostIndex(String masterPostId) throws NumberFormatException, SQLException, ParseException{
		
		String query = "select MAX(postIndex) + 1 AS incPostIndex from post_comments where masterpost_id = " + masterPostId + ";";
	
		ResultSet rs = dbCon.makeConnectionAndRunQuery(query);
		
		rs.next();
		
		String postIndex = rs.getString("incPostIndex");
		
		// no comments exist
		if(postIndex == null)
			postIndex = "0";
		
		return postIndex;
	}
	
	private ArrayList<Post> getPostsFromResultSet(ResultSet rs) throws NumberFormatException, SQLException, ParseException{
		
		ArrayList<Post> posts = new ArrayList<Post>();
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User loggedInUser =  (User) attr.getRequest().getSession().getAttribute("user");
		String loggedInId = loggedInUser.getId();
		
		while (rs.next()) {
			
			String id = rs.getString("id");
			String text = rs.getString("text");
			int likes = Integer.parseInt(rs.getString("likes"));
			int dislikes = Integer.parseInt(rs.getString("dislikes"));
			int total = Integer.parseInt(rs.getString("total"));
			
			Timestamp timestamp = Utils.getTimestamp(rs.getString("timestamp"));
			String timeString = Utils.getTimeString(timestamp);
			String dateString = Utils.getDateString(timestamp);
			
			String user_id = rs.getString("user_id");
			
			////////////////////////////////////////
			// Can logged in user rate this post? //
			boolean canRate;
			
			if(loggedInId.equals(user_id))
				canRate = false;
			else
				canRate = true;
			////////////////////////////////////////
			
			// User object for post's user                           
			User postUser = userManager.getUserById(user_id);
			
			posts.add(new Post(id, text, likes, dislikes, total, timestamp, timeString, dateString, canRate, postUser));
		}
		
		return posts;
	}
}
