package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;
import utility.UrizaHelpers;

/**
 * main page object
 * @author Philip Lipman
 *
 */
@Entity
public class Page extends Model 
{
	private static final long serialVersionUID = 9065865930662266632L;

	@Id
	public Integer id;
	
	public String name;
	public String title;
	public String description;
	
	@ManyToOne
	User user;
	
	public Timestamp dateCreated;
	public Timestamp dateModified;

	public Page(String name, String title, String description)
	{
		this.name = name;
		this.title = title;
		this.description = description;
	}
	
	public static Finder<Integer, Page> find 
	= new Finder<Integer, Page>(Integer.class, Page.class);
	
	/**
	 * returns page
	 * @param name
	 * @return
	 */
	public static Page getPage(String name)
	{		
		/**/
		return find.where()
				.eq("name", name)
				.findUnique();
		/**/
	}
	
	/**
	 * create page
	 * @param name
	 * @param title
	 * @param description
	 * @return
	 */
	public static Page create(String name, String title, String description)
	{
		Page page = new Page(name, title, description);
		page.dateCreated = UrizaHelpers.getTime();
		page.save();
		
		Component main = Component.create(name + "Component", 
				"", 
				"page", 
				"custom-page");
		
		PageComponent.create(page.id, main.id);
		
		return page;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Page> pages()
	{
		return find.findList();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Component> components()
	{			
		List<Component> components = new ArrayList<Component>();
		
		List<PageComponent> componentIds = 
				PageComponent.find.where().eq("page_id", this.id).findList();
				
		for (PageComponent c: componentIds)
		{
			components.add(Component.find.byId(c.componentId));
		}
		return components;	
	}
}
