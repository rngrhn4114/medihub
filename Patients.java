package oop_kiosk_medihub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Patients {
	private PreparedStatement psmt;
	private Connection con;
	private ResultSet rs;

	// update 
	public void updatePatients(String id, String name, String number, String medicalreport, String payment, String office, String disease, String medicine, String doses) {
		/*try {
			con = getConnection();
			psmt = con.prepareStatement("UPDATE patients SET name ='" + name + 
					"', number = '" + number +
					"', medical report = '" + medicalreport + 
					"', payment = '" + payment + 
					"', office ='" + office + 
					"', disease ='" + disease +
					"', medicine ='" + medicine +
					"', doses ='" + doses +
					"' WHERE id ='" + id +"' ;");
			psmt.executeUpdate();*/
			
			String updateQuery = "UPDATE patients SET columnName = ?  WHERE id = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                // 수정: 테이블 컬럼에 따라 set 메소드 호출 (예시: medicalreport 컬럼을 수정)
                //preparedStatement.setString(, "수정된 데이터");
                //preparedStatement.setInt(2, (int) tableModel.getValueAt(row, 0)); // 첫 번째 열은 id 컬럼

                // 수정된 데이터베이스 업데이트 쿼리 실행
                preparedStatement.executeUpdate();
			
			
                System.out.println("환자 정보가 변경되었습니다");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} finally {
				close(psmt, con);
			}
		/*} catch (Exception e) {
            e.printStackTrace();
        }*/
	}
	
	// get last ID
	public int getLastId() {
		try {
			con = getConnection();
			psmt = con.prepareStatement("SELECT id FROM patients ORDER BY id DESC LIMIT 1;");
			ResultSet rs = psmt.executeQuery();
			rs.next();        //ResultSet에서 데이터를 읽을 때 cursor point를 첫 번째 로우에 맞추어야 한다.
			return rs.getInt(1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return -1; 
		} finally {
			close(rs, psmt, con);
		}
	}
	
	// delete 
	public void deletePatients(String id) {
		try {
			con = getConnection();
			psmt = con.prepareStatement("DELETE FROM patients WHERE id = '" + id + "'");
			psmt.executeUpdate();
			System.out.println("환자 정보가 삭제되었습니다");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(psmt, con);
		}
	}

	// table 데이터 가져오기. JTable이 문자열 2차원 배열을 입력값으로 받기 때문에  
	public String[][] getPatients(){ 
		try {
			con = getConnection();
			psmt = con.prepareStatement("SELECT id, name, number, medicalreport, payment, office, disease, medicine, doses, fee FROM patients");
			ResultSet rs = psmt.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();   // 여기부터 잘 이해가 안 된다.
			while(rs.next()) {
				list.add(new String[] {
						rs.getString("id"),
						rs.getString("name"),
						rs.getString("number"),
						rs.getString("medicalreport"),
						rs.getString("payment"),
						rs.getString("office"),
						rs.getString("disease"),
						rs.getString("medicine"),
						rs.getString("doses"),
						rs.getString("fee")
				});
			}
			String[][] arr = new String[list.size()][10];
			System.out.println("데이터 불러오기 완료");
			return list.toArray(arr);

		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(psmt, con);
		}
	}

	// database 연결
	public Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://medihub.cfk4qw22eogv.us-east-1.rds.amazonaws.com:3306/medihub";
			String user = "admin";
			String pwd = "12345678";

			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("DB 연결 완료");
			return con;

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
			return null;
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			return null;
		}
	}
	
	
	// db 연결 종료 - 종료하지 않으면 제어권이 돌아가지 않아 앱이 먹통이 될 수 있다.
	public static void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
		
		try {
			if(psmt != null )
				psmt.close();
			if(conn != null)
				conn.close();
			if(rs != null)
				rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement psmt) {
		
		try {
			if(rs != null)
				rs.close();
			if(psmt != null )
				psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement psmt, Connection con) {
		
		try {
			if(psmt != null )
				psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}