import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class getData extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        if (session != null) {
            String userName = (String)session.getAttribute("username");
            String sql = "select * from profile where username='" + userName + "'";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/webtechproject", "root", "passwd");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                for (int i = 1; i <= 6; i++) {
                    if (i == 5) {
                        out.print(rs.getInt(i) + ",");
                        continue;
                    }
                    out.print(rs.getString(i) + ",");
                }
            } catch (Exception e) {
                out.print(e.getMessage());
            }
        } else {
            out.print("Some error occurred, please try again later");
        }
    }
}