package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> list(PagingVO pgvo);

	BoardVO detail(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	int getTotalCount(PagingVO pgvo);

	long selectOneBno();
 
	int deletefile(String uuid);

	void readcount(@Param("bno")int bno,@Param("cnt")int cnt);

	void updatecommentcount();

	void updatefilecount();

}
