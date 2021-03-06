package priv.linjiayun.notetakingsystem.entity;

public class Comment {
	private int id;
	private int user_id;
	private String body;
	private String create_time;
	private int note_id;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Comment(int user_id, String body, String create_time, int note_id) {
		super();
		this.user_id = user_id;
		this.body = body;
		this.create_time = create_time;
		this.note_id = note_id;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	

}
