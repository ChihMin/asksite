package netdb.courses.softwarestudio.asksite.mvc.view.json;

import netdb.courses.softwarestudio.asksite.mvc.model.domain.Definition;

public class DefinitionJson {
	
	private String title;
	private String description;
	
	public DefinitionJson(){	
	}
	
	public DefinitionJson( Definition def ){
		title = def.getTitle();
		description = def.getDescription();
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title ){
		this.title = title;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescroption( String description ){
		this.description = description;
	}
}
