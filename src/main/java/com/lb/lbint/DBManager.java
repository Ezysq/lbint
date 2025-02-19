package com.lb.lbint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Properties;

public class DBManager {
	private String url;
	private String user;
	private String pass;
	private Connection con;
	
	public DBManager() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("DBConfig.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		url = properties.getProperty("jdbc.url");
		user = properties.getProperty("jdbc.username");
		pass = properties.getProperty("jdbc.password");
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void logConversion(String curName, String conName, float curValue, float conRate, float conValue) {
		String insert = "INSERT INTO rate_exchange_hist_tab "
							+ "(cur_currency, cur_value, con_currency, con_rate, con_value)"+
							" values (?, ?, ?, ?, ?)";
		try (PreparedStatement insertTest = con.prepareStatement(insert)) {
			insertTest.setString(1, curName.toUpperCase());
			insertTest.setFloat(2, curValue);
			insertTest.setString(3, conName.toUpperCase());
			insertTest.setFloat(4, conRate);
			insertTest.setFloat(5, conValue);
			insertTest.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void update(float conValue) {
		String update = "UPDATE rate_exchange_hist_tab SET con_value = ?";
		try(PreparedStatement updateStatement = con.prepareStatement(update)){
			updateStatement.setFloat(1, conValue);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getRecent() {
		String query = "SELECT * FROM rate_exchange_hist_tab ORDER BY creation_date DESC LIMIT 5";
		DecimalFormat df = new DecimalFormat("0.00#");
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
			while (rs.next()) {
				System.out.print(rs.getString("cur_currency") + " - ");
				System.out.print(df.format(rs.getFloat("cur_value")) + " - ");
				System.out.print(rs.getString("con_currency") + " - ");
				System.out.print(df.format(rs.getFloat("con_rate")) + " - ");
				System.out.print(df.format(rs.getFloat("con_value")) + " - ");
				System.out.println(rs.getTimestamp("creation_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
