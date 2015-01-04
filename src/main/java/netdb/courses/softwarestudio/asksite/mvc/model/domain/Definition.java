package netdb.courses.softwarestudio.asksite.mvc.model.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


/**
 * Defines definition domain objects.
 */
@Entity
public class Definition {
	
	@Id
	private Long id;
	private String title;
	private String description;

	public Definition() {
		super();
	}

	public Definition(String title, String description) {
		this.title = title;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
