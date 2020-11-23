import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class logout extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)
    throws IOException,ServletException{
        HttpSession session=req.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        Cookie ck=new Cookie("username","");
        ck.setMaxAge(0);
        res.addCookie(ck);
    }
}