import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class loginservlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        if (name == null || name.equals("") || password == null || password.equals("")) {
            res.sendRedirect("login.html");
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/webtechproject", "root", "password");
            PreparedStatement stmt = con.prepareStatement("select * from students where username=?;");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                out.println("account does not exist,<a href='login.html'>click here</a> to go to login page");
            } else {
                if (!rs.getString("password").equals(password)) {
                    out.println("invalid account or invalid password,<a href='login.html'>click here</a> to go to login page");
                } else {
                    HttpSession session = req.getSession();
                    session.setAttribute("username", name);
                    if (remember != null && remember.equals("1")) {
                        Cookie ck = new Cookie("username", name);
                        ck.setMaxAge(365 * 24 * 60 * 60);
                        res.addCookie(ck);
                    }
                    res.sendRedirect("profile.html");
                }
            }
        } catch (Exception e) {
            out.print("some error occurred,<a href='login.html'>click here</a> to go login page");
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException {
        try {
            res.setContentType("text/html");
            res.getWriter().println("Eureka!");
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}