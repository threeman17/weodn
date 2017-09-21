package lbean;

public class UserWallpaper {
		private String wallpaper;
		private int wallpaperid;
		private String id;
		
		public UserWallpaper() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserWallpaper(String wallpaper, int wallpaperid, String id) {
			super();
			this.wallpaper = wallpaper;
			this.wallpaperid = wallpaperid;
			this.id = id;
		}
		public String getWallpaper() {
			return wallpaper;
		}
		public void setWallpaper(String wallpaper) {
			this.wallpaper = wallpaper;
		}
		public int getWallpaperid() {
			return wallpaperid;
		}
		public void setWallpaperid(int wallpaperid) {
			this.wallpaperid = wallpaperid;
		}
		public String getid() {
			return id;
		}
		public void setid(String id) {
			this.id = id;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((wallpaper == null) ? 0 : wallpaper.hashCode());
			result = prime * result + wallpaperid;
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
			UserWallpaper other = (UserWallpaper) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (wallpaper == null) {
				if (other.wallpaper != null)
					return false;
			} else if (!wallpaper.equals(other.wallpaper))
				return false;
			if (wallpaperid != other.wallpaperid)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "UserWallpaper [wallpaper=" + wallpaper + ", wallpaperid=" + wallpaperid + ", id=" + id + "]";
		}
		
		
		
		
}
