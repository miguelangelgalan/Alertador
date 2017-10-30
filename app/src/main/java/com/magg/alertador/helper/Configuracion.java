package com.magg.alertador.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuracion {

	private static Properties prop = new Properties();
	private static final String propFileName = "alertador.properties";
	private static Logger log = Logger.getLogger("CONFIGURACION");

	public static long TIEMPO_EJECUCION_SERVICIO = 60;  // En minutos
	
	public static String getProperty (String name) {
		
		if (prop.isEmpty()) { // Cargamos
			InputStream inputStream =   Configuracion.class.getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				try {
					prop.load(inputStream);
				} catch (IOException e) {
					log.severe("CONFIGURACION: Cannot load file: " + propFileName);
					log.severe( e.getMessage());
				}
			}
		}	
		return prop.getProperty(name,"");
	}
	
}