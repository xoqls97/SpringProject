package com.myweb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.www.repository.MemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthMemberService implements UserDetailsService {
	
	@Inject
	private MemberDAO mdao;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username DB에 설정되어있는 email이 맞는지 체크
		//인증하여 해당 객체를 AuthMember로 리턴
		MemberVO mvo = mdao.selectEmail(username);
		if(mvo ==null) { //해당 유저가 등록되지 않은 유저
			throw new UsernameNotFoundException(username); //username이 없으면 익셉션 발생
		}
		mvo.setAuthList(mdao.selectAuths(username));
		log.info(">>>> userDetails >> "+ mvo);
		return new AuthMember(mvo);
	}

}
