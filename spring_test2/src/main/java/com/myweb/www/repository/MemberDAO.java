package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	int register(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	MemberVO modify(String email);

	void noupdate(MemberVO mvo);

	void update(MemberVO mvo);

	void delete(String email);

	List<MemberVO> list();




}
