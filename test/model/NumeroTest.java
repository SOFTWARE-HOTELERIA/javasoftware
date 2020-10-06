/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.NumeroDao;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author josel
 */
public class NumeroTest{
    private Numero num;
    private NumeroDao user = new NumeroDao();
    public NumeroTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of Suma method, of class Numero.
     */
    public void testSuma(){
        num = new Numero(20,10);
       assertEquals(true,num.create());
    }


    /**
     * Test of findId method, of class Numero.
     */
//    @Test
//    public void testFindId() {
//        System.out.println("findId");
//        Numero instance = null;
//        instance.findId();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of CRUD method, of class Numero.
//     */
//    @Test
//    public void testCRUD() {
//        System.out.println("CRUD");
//        Numero instance = null;
//        UserDao expResult = null;
//        UserDao result = instance.CRUD();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
