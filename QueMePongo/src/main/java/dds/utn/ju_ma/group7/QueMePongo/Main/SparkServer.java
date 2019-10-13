package dds.utn.ju_ma.group7.QueMePongo.Main;

import spark.Spark;
import spark.debug.DebugScreen;

public class SparkServer {

    public static void main(String[] args) {
        Spark.port(8078);
        DebugScreen.enableDebugScreen();
        Router.instance().configurar();
    }

}