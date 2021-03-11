package com.epsilon.training.dao;

import java.util.ResourceBundle;

public final class DaoFactory {
	private static final String discriminator ;

	static {
		ResourceBundle rb= ResourceBundle.getBundle("dao");
		discriminator= rb.getString("dao.impl");
		
	}
	public static ProductDao getProductDao() {
		switch (discriminator.toUpperCase()) {
		case "DUMMY":
			throw new RuntimeException("Invalid discriminator");
		case "ARRAY":
			throw new RuntimeException("Invalid discriminator");
		case "JDBC":
			return new JDBCProductDao();
		case "MONGODB":
		case "CSV":
			throw new RuntimeException("Invalid discriminator");
		default:
			throw new RuntimeException("Invalid discriminator");
		}
	}

}
