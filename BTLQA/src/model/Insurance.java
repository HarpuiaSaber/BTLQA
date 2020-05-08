package model;

import java.io.Serializable;
import java.util.Date;

public class Insurance implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private User user;
	private Date regDate;
	private Method method;
	private Integer status;

	public Insurance() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}