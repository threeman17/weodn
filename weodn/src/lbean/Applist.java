package lbean;

public class Applist {
	private int id;
	private String name;
	private String intro;
	private String imgsrc;
	
	public Applist() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Applist(int id, String name, String intro, String imgsrc) {
		super();
		this.id = id;
		this.name = name;
		this.intro = intro;
		this.imgsrc = imgsrc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((imgsrc == null) ? 0 : imgsrc.hashCode());
		result = prime * result + ((intro == null) ? 0 : intro.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applist other = (Applist) obj;
		if (id != other.id)
			return false;
		if (imgsrc == null) {
			if (other.imgsrc != null)
				return false;
		} else if (!imgsrc.equals(other.imgsrc))
			return false;
		if (intro == null) {
			if (other.intro != null)
				return false;
		} else if (!intro.equals(other.intro))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "applist [id=" + id + ", name=" + name + ", intro=" + intro + ", imgsrc=" + imgsrc + "]";
	}
	
	
}
