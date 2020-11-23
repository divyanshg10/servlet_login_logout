import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class update extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException {
        String username = req.getParameter("username");
        String fieldName = req.getParameter("field");
        String value = req.getParameter("value");
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/webtechproject", "root", "passwd");
            Statement st = con.createStatement();
            st.executeUpdate("update profile set " + fieldName + " = '" + value + "' where username = '" + username + "'");
            out.println("done");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}