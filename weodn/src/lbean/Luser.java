package lbean;

import java.util.Date;

public class Luser {

		private String userid;

		private String password;

		private String email;

		private Date createtime;

		private Date updatetime;

		private String headpic;

		private String nickname;

		public Luser() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Luser(String userid, String password, String email, Date createtime, Date updatetime, String headpic,
				String nickname) {
			super();
			this.userid = userid;
			this.password = password;
			this.email = email;
			this.createtime = createtime;
			this.updatetime = updatetime;
			this.headpic = headpic;
			this.nickname = nickname;
		}

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public Date getUpdatetime() {
			return updatetime;
		}

		public void setUpdatetime(Date updatetime) {
			this.updatetime = updatetime;
		}

		public String getHeadpic() {
			return headpic;
		}

		public void setHeadpic(String headpic) {
			this.headpic = headpic;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		@Override
		public String toString() {
			return "Luser [userid=" + userid + ", password=" + password + ", email=" + email + ", createtime="
					+ createtime + ", updatetime=" + updatetime + ", headpic=" + headpic + ", nickname=" + nickname
					+ "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((headpic == null) ? 0 : headpic.hashCode());
			result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((updatetime == null) ? 0 : updatetime.hashCode());
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
			Luser other = (Luser) obj;
			if (createtime == null) {
				if (other.createtime != null)
					return false;
			} else if (!createtime.equals(other.createtime))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (headpic == null) {
				if (other.headpic != null)
					return false;
			} else if (!headpic.equals(other.headpic))
				return false;
			if (nickname == null) {
				if (other.nickname != null)
					return false;
			} else if (!nickname.equals(other.nickname))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (updatetime == null) {
				if (other.updatetime != null)
					return false;
			} else if (!updatetime.equals(other.updatetime))
				return false;
			if (userid == null) {
				if (other.userid != null)
					return false;
			} else if (!userid.equals(other.userid))
				return false;
			return true;
		}
		 
		
}
