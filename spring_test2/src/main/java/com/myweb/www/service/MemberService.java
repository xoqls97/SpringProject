package com.myweb.www.service;

import java.util.List;

import com.myweb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	MemberVO modify(String email);

	void noupdate(MemberVO mvo);

	void update(MemberVO mvo);

	void delete(String email);

	List<MemberVO> list();



	

}
