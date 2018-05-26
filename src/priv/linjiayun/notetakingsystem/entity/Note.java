package priv.linjiayun.notetakingsystem.entity;

public class Note {
	private int id;
	private int user_id;
	private String title;
	private String body;
	private String create_time;
	private String last_modify_time;
	private int purview;
	
	
	
	
	
	
	
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Note(int user_id, String title, String body, String create_time, int purview) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.body = body;
		this.create_time = create_time;
		this.purview = purview;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCreate_time() {
		if(create_time.isEmpty()||create_time==null) {
			return " ";
		}
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_modify_time() {
		if(last_modify_time==null||last_modify_time.isEmpty()) {
			return " ";
		}
		return last_modify_time;
	}
	public void setLast_modify_time(String last_modify_time) {
		this.last_modify_time = last_modify_time;
	}
	public int getPurview() {
		return purview;
	}
	public void setPurview(int purview) {
		this.purview = purview;
	}
	
	

}
