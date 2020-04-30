import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

/**
 * THis is a Junit testing class used to check the logic of the methods usedin the project.
 * 
 * 
 * 
 * @author Yogesh Bhattarai
 *
 */

public class JunitTesterTest {
	
	
	/**
	 * This test checks for the correct format of the pattern.
	 */

    @Test
    public void checkgetspacedstrings()
    {
       	//checking the line by parsing the true statement.
    	assertTrue(pattern.checkingLine("1A DF 94 28 53"));
    	
    	//checking the validity by passing the false statement
    	assertFalse(pattern.checkingLine("2897TZB323"));
    	assertFalse(pattern.checkingLine("79 1A DF 42 8B52"));
    }
    
    /**
     * This method checks for the working mechanism of hexchecker
     */
    
    @Test
   public void checkhexchecker() {
    	
    	//inititalizing true hex number and false hex number pattern to a string
    	String trueHexNumberPattern="1A DF 94 28 53";
    	String falseHexNumberPattern= "2897TZB323";
    	
    	
    	//asserting the method is working properly or not 
    	assertTrue(pattern.checkHex(trueHexNumberPattern));
    	assertFalse(pattern.checkHex(falseHexNumberPattern));
    	
    }
    /**
     * This method checks for the validator method of hex checker
     */
    @Test
    public void checkhexvalidator() {

    	//inititalizing true hex number and false hex number pattern to a string
     	
     	String trueHexNumberPattern="1A DF 94 28 53";
     	String falseHexNumberPattern= "2897TZB323";
     	
     	

    	//checking by passing a right hex value to find error
     	assertTrue(pattern.checkPairedHex(trueHexNumberPattern));
     	
     	//checking by passing a wrong hex value to find error
     	assertFalse(pattern.checkPairedHex(falseHexNumberPattern));
     	
     }

}
