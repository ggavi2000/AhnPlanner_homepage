package user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// �����ͺ��̽� ���� ��ü�� ���� (���������� �����ͺ��̽����� ȸ�� ������ �ҷ����ų� �ְ��� �� �� ���)
public class UserDAO {
	// Ctrl+Shift+O ������ �Ʒ� ��ü �߰�
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";  // 3306�� �� ��ǻ�Ϳ� ��ġ�� mySQL �� ��ü�� ����
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;  // �α��� ����
				}
				else
					return 0;  // ��й�ȣ ����ġ
			}
			return -1; // ���̵� ����

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // �����ͺ��̽� ����
	}
}