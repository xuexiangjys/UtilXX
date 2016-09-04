package bean;

public class Student {
		private String sno;  //学号
		private String sname;  //姓名
		private String ssex;  //性别
		private int sage;  //年龄
		private String dno;  //所在学院
		private String contact; //联系电话
		private String homeaddr; //家庭住址
		public String getSno() {
			return sno;
		}
		public void setSno(String sno) {
			this.sno = sno;
		}
		public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
		}
		public String getSsex() {
			return ssex;
		}
		public void setSsex(String ssex) {
			this.ssex = ssex;
		}
		public int getSage() {
			return sage;
		}
		public void setSage(int sage) {
			this.sage = sage;
		}
		public String getDno() {
			return dno;
		}
		public void setDno(String dno) {
			this.dno = dno;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public String getHomeaddr() {
			return homeaddr;
		}
		public void setHomeaddr(String homeaddr) {
			this.homeaddr = homeaddr;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "学号:"+sno+"，年龄:"+sage+"，性别:"+ssex+"，所在学院:"+dno+"，联系电话:"+contact+"，家庭住址:"+homeaddr;
		}
    }


