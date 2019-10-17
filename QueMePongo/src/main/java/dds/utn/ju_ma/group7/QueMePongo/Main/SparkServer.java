package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import spark.Spark;
import spark.debug.DebugScreen;

public class SparkServer {

    public static void main(String[] args) {
    	new RepositorioUsuariosPersistente().instanciarUsuarioGratis(Arrays.asList(), "mallocos@dds.com", "dds123");
        Spark.port(8078);
        DebugScreen.enableDebugScreen();
        Router.instance().configurar();
    }

}