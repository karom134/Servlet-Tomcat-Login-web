package sample.webapp;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import sample.webapp.servlet.ServletRouter;


import javax.servlet.ServletException;
import java.io.File;
import java.sql.SQLException;


public class WebApp {

    public static void main(String[] args) {
        Tomcat tomcat=new Tomcat();
        tomcat.setPort(8080);

        File docBase=new File("C:\\Users\\Asus\\Desktop\\OOC\\homework_3\\BasicWebApp\\src\\main\\webapp");
        docBase.mkdirs();
        try {
            Context ctx=tomcat.addWebapp("",docBase.getAbsolutePath());
            Class filterClass = ServletRouter.class;
            FilterDef myFilterDef = new FilterDef();
            myFilterDef.setFilterClass(filterClass.getName());
            myFilterDef.setFilterName(filterClass.getSimpleName());
            ctx.addFilterDef(myFilterDef);

            FilterMap myFilterMap = new FilterMap();
            myFilterMap.setFilterName(filterClass.getSimpleName());
            myFilterMap.addURLPattern("/*");
            ctx.addFilterMap(myFilterMap);
            ServletRouter servletRouter=new ServletRouter();
            servletRouter.init(ctx);

            tomcat.start();
            tomcat.getServer().await();
            servletRouter.getDatabaseConnector().getCon().close();
        } catch (ServletException | LifecycleException | SQLException e) {
            e.printStackTrace();
        }
    }
}
