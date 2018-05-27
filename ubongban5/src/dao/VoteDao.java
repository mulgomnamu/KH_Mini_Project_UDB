package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.DBClose;
import db.DBConnection;
import dto.VoteDto;

public class VoteDao implements VoteDaoImpl {
	
	//voteview의 투표 list를 가져옴
	@Override
	public List<VoteDto> getVoteList() {
		List<VoteDto> list = new ArrayList<VoteDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT SEQ, VOTECONTENT, VOTESTART, VOTEEND, VOTECOUNT, AGREEMENT, DISAGREEMENT, ABSTAIN"
				+ " FROM VOTEBOARD "
				+ " ORDER BY VOTESTART DESC ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			while(rs.next()){

				int i = 1;
				
				VoteDto dto = new VoteDto(rs.getInt(i++), 	// SEQ
										rs.getString(i++), 	// VOTECONTENT
										rs.getString(i++), 	// VOTESTART
										rs.getString(i++), 	// VOTEEND
										rs.getInt(i++), 	// VOTECOUNT
										rs.getInt(i++), 	// AGREEMENT
										rs.getInt(i++),	// DISAGREEMENT
										rs.getInt(i++));	// ABSTAIN
				list.add(dto);
			}		
			System.out.println("5/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
		} finally{
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsList Success");
		}
		
		return list;
	}

	//투표 찬성, 반대, 기권에 대한 카운팅을 하는 함수
	@Override
	public void voteChoice(int seq, int voteResult) {
		
		if(voteResult == 0) {
			String sql=" UPDATE VOTEBOARD SET  "
					+" AGREEMENT = AGREEMENT + 1 , VOTECOUNT = VOTECOUNT + 1 "
					+ " WHERE SEQ = ? ";
			
			Connection conn=null;
			PreparedStatement psmt=null;
			ResultSet rs=null;
			
			try {
				conn = DBConnection.getConnection();	
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				
				psmt.executeUpdate();			
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBClose.close(psmt, conn, rs);	
			}
		}else if(voteResult == 1) {
			String sql=" UPDATE VOTEBOARD SET  "
					+" DISAGREEMENT = DISAGREEMENT + 1 , VOTECOUNT = VOTECOUNT + 1 "
					+ " WHERE SEQ = ? ";
			
			Connection conn=null;
			PreparedStatement psmt=null;
			ResultSet rs=null;
			
			try {
				conn = DBConnection.getConnection();	
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				
				psmt.executeUpdate();			
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBClose.close(psmt, conn, rs);	
			}
		}else if(voteResult == 2) {
			String sql=" UPDATE VOTEBOARD SET  "
					+" ABSTAIN = ABSTAIN + 1 , VOTECOUNT = VOTECOUNT + 1 "
					+ " WHERE SEQ = ? ";
			
			Connection conn=null;
			PreparedStatement psmt=null;
			ResultSet rs=null;
			
			try {
				conn = DBConnection.getConnection();	
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				
				psmt.executeUpdate();			
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBClose.close(psmt, conn, rs);	
			}
		}else if(voteResult == -1) {
			//JOptionPane.showMessageDialog(null, "");
		}
		
				
	}

	//관리자가 투표안건을 추가하기 위한 부분
	@Override
	public boolean addVote(VoteDto dto) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//("admin", 0, content, startdate, enddate, 0, 0, 0, 0)
		
		String sql = " INSERT INTO VOTEBOARD(SEQ, VOTECONTENT, VOTESTART, VOTEEND, VOTECOUNT, AGREEMENT, DISAGREEMENT, ABSTAIN, VOTEMEMBERLIST, DEL) "
				+ " VALUES(SEQ_VOTEBOARD.NEXTVAL, ?, ?, ?, 0, 0, 0, 0, ',' ,0) ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 writeBBS Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writeBBS Success");
			System.out.println(dto.getVotestart());
			System.out.println(dto.getVoteend());
			
			psmt.setString(1,dto.getVotecontent());
			psmt.setString( 2, dto.getVotestart());
			psmt.setString(3, dto.getVoteend());

			count = psmt.executeUpdate();
			System.out.println("4/6 writeBBS Success");
			
		} catch (SQLException e) {			
			System.out.println("writeBBS fail");
		} finally{
			DBClose.close(psmt, conn, rs);			
		}
		
		return count>0?true:false;
	}
	
	//투표 중복체크를 위해 필요한 부분
	@Override
	public boolean voteMemberListCheckUpdate(int seq, String id) {
		
		String sql = " SELECT VOTEMEMBERLIST "
				+ " FROM VOTEBOARD "
				+ " WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		String plusId = null;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("3/6 S updateBbs");
			
			//count = psmt.executeUpdate();
			rs = psmt.executeQuery();
			System.out.println("4/6 S updateBbs");
			
			while(rs.next()){				
				plusId = rs.getString("votememberlist");
				
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
			System.out.println("5/6 S updateBbs");
		}		
		
		
		sql = " UPDATE VOTEBOARD SET "
				+ " VOTEMEMBERLIST = ? "
				+ " WHERE SEQ = ? ";
		conn = null;
		psmt = null;
		count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id+", "+plusId );
			psmt.setInt(2, seq);
			
			System.out.println("3/6 S updateBbs");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S updateBbs");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S updateBbs");
		}		
		
		return count>0?true:false;
	}
	
	//투표중복체크를 위해 필요한 부분
	@Override
	public boolean voteMemberListFind(String id, int seq) {
		//List<VoteDto> list = new ArrayList<VoteDto>();
		
		String sql = " SELECT SEQ "
				+ " FROM VOTEBOARD "
				+ " WHERE VOTEMEMBERLIST LIKE ? and SEQ = ?";
		boolean findId = false;
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getTitleFindList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getTitleFindList Success");
			
			psmt.setString(1, "%" + id + "%");
			psmt.setInt(2, seq);
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getTitleFindList Success");
			
			while(rs.next()){
				findId = true;
			}		
			System.out.println("5/6 getTitleFindList Success");
			
		} catch (SQLException e) {
			System.out.println("getTitleFindList fail");
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
			System.out.println("6/6 getTitleFindList Success");
		}
		System.out.println(" 확인 "+findId);
		return findId;
	}

	//찬반, 기권표의 카운팅숫자를 가져옴
	@Override
	public VoteDto getAgreeDisargeeAbstain(int seq) {
		VoteDto dto = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT VOTECONTENT, AGREEMENT, DISAGREEMENT, ABSTAIN "
				+ " FROM VOTEBOARD "
				+ " WHERE SEQ=? ";
		
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);	
			
			psmt.setInt(1, seq);			
			rs = psmt.executeQuery();
			
			while(rs.next()){				
				String content = rs.getString("votecontent");
				int agreement = rs.getInt("agreement");
				int disagreement = rs.getInt("disagreement");
				int abstain = rs.getInt("abstain");
				
				dto = new VoteDto(content, agreement, disagreement, abstain);	
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}
		return dto;
	}

	//투표가 종료된 안건에 대해 그 정보를 가져와 resultview db에 따로 저장하는 부분
	@Override
	public boolean VoteResultInsert(List<VoteDto> seqdto) {
		int count = 0;
		List<VoteDto> resultlist = new ArrayList<VoteDto>();
		VoteDto dto = null;
		for (int i = 0; i < seqdto.size(); i++) {
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT VOTECONTENT, AGREEMENT, DISAGREEMENT, ABSTAIN "
					+ " FROM VOTEBOARD "
					+ " WHERE SEQ=? ";
			
			try {			
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);	
				
				psmt.setInt(1, seqdto.get(i).getSeq());			
				rs = psmt.executeQuery();
				
				while(rs.next()){				
					String content = rs.getString("votecontent");
					int agreement = rs.getInt("agreement");
					int disagreement = rs.getInt("disagreement");
					int abstain = rs.getInt("abstain");
					
					dto = new VoteDto(content, agreement, disagreement, abstain);	
					resultlist.add(dto);
					
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
			}
		}
		
		for (int i = 0; i < resultlist.size(); i++) {
			
			String sql = " INSERT INTO VOTERESULTBOARD(VOTECONTENT, VOTECOUNT, AGREEMENT, DISAGREEMENT, ABSTAIN) "
					+ " VALUES(?, ?, ?, ?, ?) ";
			
			
			Connection conn=null;
			PreparedStatement psmt=null;
			
			try {
				
				conn = DBConnection.getConnection();
				System.out.println("2/6 writeBBS Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 writeBBS Success");
				System.out.println(dto.getVotestart());
				System.out.println(dto.getVoteend());
				
				psmt.setString(1,resultlist.get(i).getVotecontent());
				psmt.setInt(2, resultlist.get(i).getVotecount());
				psmt.setInt(3, resultlist.get(i).getAgreement());
				psmt.setInt(4, resultlist.get(i).getDisagreement());
				psmt.setInt(5, resultlist.get(i).getAbstain());

				count = psmt.executeUpdate();
				System.out.println("4/6 writeBBS Success");
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);			
			}
		}
		return count>0?true:false;
	}
	
	//투표 결과값을 가져오는 부분
	@Override
	public List<VoteDto> getVoteResult() {
		List<VoteDto> list = new ArrayList<VoteDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT VOTECONTENT, VOTECOUNT, AGREEMENT, DISAGREEMENT, ABSTAIN"
				+ " FROM VOTERESULTBOARD ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			while(rs.next()){

				int i = 1;
				
				VoteDto dto = new VoteDto(rs.getString(i++), 	// VOTECONTENT
										rs.getInt(i++), 	// VOTECOUNT
										rs.getInt(i++), 	// AGREEMENT
										rs.getInt(i++),	// DISAGREEMENT
										rs.getInt(i++));	// ABSTAIN
				list.add(dto);
			}		
			System.out.println("5/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
		} finally{
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsList Success");
		}
		
		return list;
	}
	
	
	
	//기간이 경과된 투표안건을 삭제
	@Override
	public boolean VoteDelete(List<VoteDto> seqdto) {
		int count = 0;
		
//		for (int i = 0; i < seqdto.size(); i++) {
//			
//			String sql = " INSERT INTO VOTEBOARD(SEQ, VOTECONTENT, VOTESTART, VOTEEND, VOTECOUNT, AGREEMENT, DISAGREEMENT, ABSTAIN, VOTEMEMBERLIST, DEL) "
//					+ " VALUES(SEQ_VOTEBOARD.NEXTVAL, ?, ?, ?, 0, 0, 0, 0, 'value' ,0) ";
//			
//			
//			Connection conn=null;
//			PreparedStatement psmt=null;
//			
//			try {
//				conn = DBConnection.getConnection();			
//				psmt=conn.prepareStatement(sql);
//				psmt.setInt(1, seqdto.get(i).getSeq());			
//				count = psmt.executeUpdate();
//				
//			} catch (SQLException e) {			
//				e.printStackTrace();
//			} finally{
//				DBClose.close(psmt, conn, null);			
//			}
//		}
		
		for (int i = 0; i < seqdto.size(); i++) {
			String sql=" DELETE FROM VOTEBOARD  "
					+ " WHERE SEQ = ? ";
			
			
			Connection conn=null;
			PreparedStatement psmt=null;
			
			try {
				conn = DBConnection.getConnection();			
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, seqdto.get(i).getSeq());			
				count = psmt.executeUpdate();
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);			
			}
		}
		return count>0?true:false;
	}

	//안쓰는 부분
	@Override
	public List<VoteDto> getFindList(String fStr) {
		// TODO Auto-generated method stub
		return null;
	}

	//투표안건이 끝나는 기간을 가져옴
	@Override
	public List<VoteDto> getVoteEndDate() {
		List<VoteDto> list = new ArrayList<VoteDto>();
		VoteDto dto = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT VOTEEND, SEQ"
				+ " FROM VOTEBOARD ";
		
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);	
			
			//psmt.setInt(1, seq);			
			rs = psmt.executeQuery();
			
			while(rs.next()){				
				String voteend = rs.getString("voteend");
				int seq = rs.getInt("seq");
				dto = new VoteDto(voteend, seq);	
				list.add(dto);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}
		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("dao측 list : " +list.get(i).getVoteend());
//		}
		
		return list;
	}

//	@Override
//	public VoteDto getVoteEndDate(int seq) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
