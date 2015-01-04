package netdb.courses.softwarestudio.asksite.mvc.view.json;


import netdb.courses.softwarestudio.asksite.mvc.model.domain.Comment;

public class CommentJson {
	
	private Long id;
	private String title;
	private String content;
	
	public CommentJson(){	
	}
	
	public CommentJson( Comment def ){
		title = def.getTitle();
		content = def.getContent();
		
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
	
	public String getContent(){
		return content;
	}
	
	public void setContent( String content ){
		this.content = content;
	}
}
