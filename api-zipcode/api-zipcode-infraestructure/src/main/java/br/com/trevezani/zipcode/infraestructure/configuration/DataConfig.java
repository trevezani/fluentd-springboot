package br.com.trevezani.zipcode.infraestructure.configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Bean(name = "connection")
    public Connection getConnection() {
		var classLoader = getClass().getClassLoader();
		var inputStream = classLoader.getResourceAsStream("data.zip");

		try {
			Class.forName("org.relique.jdbc.csv.CsvDriver");

			final String url = "jdbc:relique:csv:zip:" + dataFile(inputStream);

			return DriverManager.getConnection(url);
		} catch (Exception e) {
			log.error("Error: {}", e.getMessage());
		}

        return null;
	}

	private String dataFile(final InputStream inputStream) throws IOException {
		File fstream = File.createTempFile("data", ".zip");
		fstream.deleteOnExit();

		try (OutputStream outputStream = new FileOutputStream(fstream)) {
			inputStream.transferTo(outputStream);
		}

		Path data = fstream.toPath();

		return data.toString();
	}
}
