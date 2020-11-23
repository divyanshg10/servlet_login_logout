import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class signupservlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        if ((name == null || name.equals("")) || (password == null || password.equals(""))) {
            res.sendRedirect("signup.html");
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/webtechproject", "root", "password");
            PreparedStatement stmt = con.prepareStatement("select * from students where username=?;");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                out.println("username exists,<a href='signup.html'>click here</a> to go to signup page");
            } else {
                try {
                    con.setAutoCommit(false);
                    PreparedStatement stmt1 = con.prepareStatement("insert into students(username,password) values(?,?);");
                    stmt1.setString(1, name);
                    stmt1.setString(2, password);
                    stmt1.executeUpdate();
                    PreparedStatement stmt2 = con.prepareStatement("insert into profile values(?,'','','',-1,'')");
                    stmt2.setString(1, name);
                    stmt2.executeUpdate();
                    con.commit();
                    out.print("account created successfully,<a href='login.html'>click here</a> to login");
                } catch (SQLException e) {
                    con.rollback();
                    out.println("Some error occurred,<a href='signup.html'>click here</a> to go to signup page");
                }
            }
        } catch (Exception e) {
            out.println("Some error occurred,<a href='signup.html'>click here</a> to go to signup page");
        }
    }
}