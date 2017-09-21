package com.m2i.formation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.m2i.formation.entity.Employe;

public class SpringJDBC {

	private static final Logger l = Logger.getLogger(SpringJDBC.class);

	public Employe getEmployebyId(int id) {
		java.sql.Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Employe employe = new Employe();
		try {
			l.info("Debut de la méthode getEmployebyId");
			final String SELECT_QUERY = "SELECT ID, login, email, password, prenom, nom, role FROM employe WHERE ID = ?";
			ApplicationContext appContext = (ApplicationContext) new ClassPathXmlApplicationContext("spring-data.xml");
			DataSource datasource = (DataSource) appContext.getBean("datasource2");
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(SELECT_QUERY);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			if (rs.next()) {
				employe.setId(rs.getInt("ID"));
				employe.setLogin(rs.getString("login"));
				employe.setEmail(rs.getString("email"));
				employe.setPrenom(rs.getString("prenom"));
				employe.setNom(rs.getString("nom"));
				employe.setPassword(rs.getString("password"));
				employe.setRole(rs.getString("role"));

				l.info("Nom : " + employe.getNom());
			}
			l.info("Fin de la méthode getEmployebyId");
		} catch (SQLException e) {
			l.error("Error : " + e);
		}
		return employe;
	}

	public void addEmploye(Employe emp) {

		java.sql.Connection conn = null;
		PreparedStatement stmt = null;
		int i = 0;
		try {
			l.info("Debut de la méthode addEmploye");
			final String INSERT_QUERY = "INSERT INTO employe (login, email, password, prenom, nom, role) VALUES (?,?,?,?,?,?)";
			ApplicationContext appContext = (ApplicationContext) new ClassPathXmlApplicationContext("spring-data.xml");
			DataSource datasource = (DataSource) appContext.getBean("datasource2");
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(INSERT_QUERY);
			stmt.setString(1, emp.getLogin());
			stmt.setString(2, emp.getEmail());
			stmt.setString(3, emp.getPassword());
			stmt.setString(4, emp.getPrenom());
			stmt.setString(5, emp.getNom());
			stmt.setString(6, emp.getRole());
			i = stmt.executeUpdate();
			l.info("i : " + i);
			l.info("Fin de la méthode addEmploye");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	// à faire save delete getAll Employe à l'ancienne

	public Employe getEmployebyIdJdbcTemplate(int id) {

		l.info("Debut de la méthode getEmployebyIdJdbcTemplate");

		final String SELECT_QUERY = "SELECT ID, login, email, password, prenom, nom, role FROM employe WHERE ID = ?";
		ApplicationContext appContext = (ApplicationContext) new ClassPathXmlApplicationContext("spring-data.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) appContext.getBean("jdbcTemplate");

		Employe emp = jdbcTemplate.queryForObject(SELECT_QUERY, BeanPropertyRowMapper.newInstance(Employe.class), id);
		l.info("Nom : " + emp.getNom());
		l.info("Fin de la méthode addEmploye");

		return emp;

	}

	// avec JDBCTEMPLATE ADD
	public void addEmployeJdbcTemplate(Employe emp) {

		l.info("Debut de la méthode addEmployeJdbcTemplate");

		final String INSERT_QUERY = "INSERT INTO employe (login, email, password, prenom, nom, role) VALUES (?,?,?,?,?,?)";
		ApplicationContext appContext = (ApplicationContext) new ClassPathXmlApplicationContext("spring-data.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) appContext.getBean("jdbcTemplate");

		jdbcTemplate.update(INSERT_QUERY, new Object[] { emp.getLogin(), emp.getEmail(), emp.getPassword(),
				emp.getPrenom(), emp.getNom(), emp.getRole() });

		l.info("Fin de la méthode addEmploye");
	}

	// avec JDBCTEMPLATE UPDATE
	public void saveEmployeJdbcTemplate(Employe emp) {

		l.info("Debut de la méthode saveEmployeJdbcTemplate");

		final String UPDATE_QUERY = "UPDATE employe set login = ?, email = ?, password = ?, prenom = ?, nom = ?, role = ?";
		ApplicationContext appContext = (ApplicationContext) new ClassPathXmlApplicationContext("spring-data.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) appContext.getBean("jdbcTemplate");

		jdbcTemplate.update(UPDATE_QUERY, new Object[] { emp.getLogin(), emp.getEmail(), emp.getPassword(),
				emp.getPrenom(), emp.getNom(), emp.getRole() });

		l.info("Fin de la méthode saveEmployeJdbcTemplate");
	}

}
