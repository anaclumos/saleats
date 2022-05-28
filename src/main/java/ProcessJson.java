
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import controller.DataParser;
import controller.JdbcDriver;
import model.Constant;

@WebListener
public class ProcessJson
    implements ServletContextListener {

  @Override
  public void contextDestroyed(ServletContextEvent contextEvent) {
    System.out.println("ServletContextListener destroyed");
  }

  // Run this before web application is started
  @Override
  public void contextInitialized(ServletContextEvent contextEvent) {
    System.out.println("ServletContextListener started");
    ServletContext sc = contextEvent.getServletContext();
    // if something is inside sql database, do nothing
    // query sql
    JdbcDriver jdbc = JdbcDriver.getInstance();
    if (jdbc.dataExists()) {
      System.out.println("Data exists. Skipping parsing.");
    } else {
      System.out.println("Data does not exist in database. Parsing data.");
      DataParser parser = new DataParser(sc.getRealPath("WEB-INF/" + Constant.restaurantJsonFile));
      parser.parse();
    }
  }
}