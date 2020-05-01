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
	 * This test checks for the correct format of the pattern.
	 */

    @Test
    public void checkLineCHecker()
    {
       	//checking the line by parsing the true statement.
    	assertTrue(pattern.checkingLine("1A DF 94 28 53"));
    	
    	//checking the validity by passing the false statement
    	assertFalse(pattern.checkingLine("2897TZB323"));
    	assertFalse(pattern.checkingLine("79 1A DF 42 8B52"));
	}
	

    /**
     * This method checks for the validator method of hex checker
     */
    @Test
    public void checkhexvalidator() {

    	//inititalizing true hex number and false hex number pattern to a string
     	String HexNumberPattern="1A DF 94 28 53";

    	//The result of the parsed algorithm must come true
		 assertTrue(pattern.checkPairedHex(HexNumberPattern));
		 
		 HexNumberPattern= "2897TZB323";
     	
     	//The result of the parsed algorithm must come False
     	assertFalse(pattern.checkPairedHex(HexNumberPattern));
     	
	 }

}
