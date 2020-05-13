
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HolidayBonus_GFA_Test {
	
	private double[][] dataSet1 = {{1,2,3},{4,5},{6,7,8}};

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
		
	/**
	 * Test calculateHolidayBonus method with a high of 5000, low of 1000 and 2000 as other		 
	 */

		@Test
		public void testCalculateHolidayBonusA() {
			try{
			double[] result = HolidayBonus.calculateHolidayBonus(dataSet1,5000,1000,2000);
			assertEquals(3000.0,result[0],.001);
			}
			catch (Exception e) {
				fail("This should not have caused an Exception");
			} 
		}
}
