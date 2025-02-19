package com.stard.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Entity
// 기본적으로 hibernate는 엔티티를 수정(insert, update)할 때 모든 필드를 수정 -> 모든 필드 사용 시 INSERT, UPDATE 쿼리가 항상 같기 때문에 쿼리를 미리 생성해두고 재사용 가능
// @DynamicInsert, @DynamicUpdate는 null값이 등록되면 쿼리가 생성되는 것을 막아줌
// 하지만 @DynamicInsert, @DynamicUpdate 사용 시 쿼리가 실행될 때마다 변경된 필드를 동적으로 결정하기 때문에 쿼리 실행 속도를 저하 시킬 수 있음
// @DynamicInsert, @DynamicUpdate는 몇 개의 필드만 자주 수정하는 경우 혹은 필드가 많은 경우에 사용
@DynamicInsert
@DynamicUpdate
@Data
public class Member {
	
	@Id
	private String userId; // userId == email
	private String password;
	
	@Column(columnDefinition = "varchar(30) default 'ROLE_MEMBER'")
	protected String grade;
	protected String tel;
	
	@Column(columnDefinition = "date default current_timestamp")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	
	@Column(columnDefinition = "varchar(5) default 'false'")
	private boolean isLeave;
	
	public List<SimpleGrantedAuthority> getAuthority() {
		List<SimpleGrantedAuthority> auth = new ArrayList<SimpleGrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(grade));
		return auth;
	}

}
