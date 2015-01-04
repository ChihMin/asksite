package netdb.courses.softwarestudio.asksite.mvc.view.json;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import netdb.courses.softwarestudio.asksite.mvc.model.business.persistence.DefinitionDao;
import netdb.courses.softwarestudio.asksite.mvc.model.domain.Definition;

public class DefinitionJson {
	
	private Long id;
	private String title;
	private String description;
	
	public DefinitionJson(){	
	}
	
	public DefinitionJson( Definition def ){
		title = def.getTitle();
		description = def.getDescription();
		
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title ){
		this.title = title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription( String description ){
		this.description = description;
	}
}
