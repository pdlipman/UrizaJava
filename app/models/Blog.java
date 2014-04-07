package models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;
import utility.UrizaHelpers;

@Entity
public class Blog extends Model
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2881184938176757675L;

	@Id
	public Integer id;
	
	public String title;
	public String post;
	public String image;
	
	public List<String> tags;
	
	public Boolean published;
	
	public Timestamp dateCreated;
	public Timestamp dateModified;
	
	public static Finder<Long, Blog> find 
	= new Finder<Long, Blog>(Long.class, Blog.class);
	
	public static Blog getBlog(String title)
	{				
		//title = title.replace("-", " ");
		return find.where()
				.ieq("title", title)
				.findUnique();
	}
	
	public Blog(String title, String post, String image)
	{
		this.title = title;
		this.post = post;
		this.image = image;
		
		this.published = false;
		
		this.dateCreated = UrizaHelpers.getTime();
	}
	
	public static List<Blog> blogRoll(String tag)
	{
		List<String> tags = Arrays.asList(tag.split(","));
		return find.where().in("tag", tags).findList();
	}
	
	public static Blog create(String title, String post, String image, List<String> tags)
	{
		Blog blog = new Blog(title, post, image);
		blog.dateCreated = UrizaHelpers.getTime();
		blog.save();
		blog.tags = tags;		
		
		return blog;
	}

}
