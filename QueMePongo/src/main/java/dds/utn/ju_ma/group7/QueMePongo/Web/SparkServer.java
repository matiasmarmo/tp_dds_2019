package dds.utn.ju_ma.group7.QueMePongo.Web;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Main.QueMePongoConfiguration;
import spark.Spark;
import spark.debug.DebugScreen;

public class SparkServer implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps{

    public static void main(String[] args) {
    	new SparkServer().run();
    }

	public void run() {
		QueMePongoConfiguration.inicializar(100, "", "");
		
		String portEnvVariable = System.getenv("PORT");
		int port = portEnvVariable != null ? Integer.parseInt(portEnvVariable) : 9000;
		
        Spark.port(port);
        Spark.staticFiles.location("/images");
        DebugScreen.enableDebugScreen();
        Router.instance().configurar();
	}

}