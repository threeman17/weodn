package lbean;

public class UserApp {
		private String userid;
		private int keyid;
		private String id;
		public UserApp() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserApp(String userid, int keyid, String id) {
			super();
			this.userid = userid;
			this.keyid = keyid;
			this.id = id;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public int getKeyid() {
			return keyid;
		}
		public void setKeyid(int keyid) {
			this.keyid = keyid;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + keyid;
			result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
			UserApp other = (UserApp) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (keyid != other.keyid)
				return false;
			if (userid == null) {
				if (other.userid != null)
					return false;
			} else if (!userid.equals(other.userid))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "UserApp [userid=" + userid + ", keyid=" + keyid + ", id=" + id + "]";
		}
		
		
		
}
