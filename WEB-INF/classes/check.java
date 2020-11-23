import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class check extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)
    throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        HttpSession session=req.getSession(false);
        if(session!=null){
            out.println(session.getAttribute("username"));
        }else{
            Cookie ck[]=req.getCookies();
            if(ck!=null){
                if(ck.length>1&&!(ck[1].getValue().equals(""))){
                    out.println(ck[1].getValue());
                    session=req.getSession();
                    session.setAttribute("username",ck[1].getValue());
                }else{
                    out.println("0");
                }
            }else{
                out.println("0");
            }
        }
    }
}