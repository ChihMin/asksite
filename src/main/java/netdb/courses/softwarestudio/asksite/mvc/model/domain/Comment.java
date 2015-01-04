package netdb.courses.softwarestudio.asksite.mvc.model.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


/**
 * Defines definition domain objects.
 */
@Entity
public class Comment {
	
	@Id
	private Long id;
	
	private String title;
	private String content;

	public Comment() {
		super();
	}

	public Comment(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
