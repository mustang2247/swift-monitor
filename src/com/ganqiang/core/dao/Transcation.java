package com.ganqiang.core.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Transcation {

	private static final Logger logger = Logger.getLogger(Transcation.class);
	
	public static void openTransaction(Connection conn) {
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (SQLException e) {
			logger.error("Cannot to execute openTransaction method ",e.getCause());
		}
	}

	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			logger.error("Cannot to execute comit method ",e.getCause());
		}
	}

	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			logger.error("Cannot to execute rollback method ",e.getCause());
		}
	}

}
