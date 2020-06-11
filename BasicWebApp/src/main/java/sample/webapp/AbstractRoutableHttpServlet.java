package sample.webapp;

import sample.webapp.mysql.DatabaseConnector;
import sample.webapp.security.SecurityService;

import javax.servlet.http.HttpServlet;

public abstract class AbstractRoutableHttpServlet extends HttpServlet implements Routable {

    protected SecurityService securityService;
    protected DatabaseConnector databaseConnector;
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setDatabaseConnector(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }
}
