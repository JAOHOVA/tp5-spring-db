package com.m2i.formation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.m2i.formation.dao.SpringJDBC;

public class SpringJDBCTest {

	SpringJDBC springJDBC = new SpringJDBC();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testGetEmployebyId() {
//		springJDBC.getEmployebyId(4);
//	}
//
//	@Test
//	public void testAddEmploye() {
//		Employe employe = new Employe("DSW", "m2i", "KINO", "JULLES", "kino@yahoo.fr", "PO");
//		springJDBC.addEmploye(employe);
//	}
//
	@Test
	public void testGetEmployebyIdJdbcTemplate() {
		springJDBC.getEmployebyIdJdbcTemplate(3);
	}
	
//	@Test
//	public void testAddEmployeJdbcTemplate() {
//		Employe employe = new Employe("DSW", "m2i", "NOKI", "DURANT", "noki@yahoo.fr", "Scrum Master");
//		springJDBC.addEmployeJdbcTemplate(employe);
//	}
	
//	@Test
//	public void testSaveEmployeJdbcTemplate() {
//		Employe employe = new Employe("FDGFGDSD", "m2i", "HDD", "DURADSGNT", "nokiiot@yahoo.fr", "S M");
//		springJDBC.saveEmployeJdbcTemplate(employe);
//	}
}
