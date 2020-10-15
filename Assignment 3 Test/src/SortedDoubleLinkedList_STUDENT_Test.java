import static org.junit.Assert.*;

	import java.util.ArrayList;
	import java.util.Comparator;
	import java.util.GregorianCalendar;
	import java.util.Iterator;
	import java.util.ListIterator;
	import java.util.NoSuchElementException;

	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;


/**
 * @author David Dayton
 */
public class SortedDoubleLinkedList_STUDENT_Test {
	
		SortedDoubleLinkedList<String> sortedLinkedString;
		SortedDoubleLinkedList<Double> sortedLinkedDouble;
		StringComparator comparator;
		DoubleComparator comparatorD;

		
		@Before
		public void setUp() throws Exception {
			comparator = new StringComparator();
			sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
			
			comparatorD = new DoubleComparator();
			sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
		}

		@After
		public void tearDown() throws Exception {
			comparator = null;
			comparatorD = null;
			sortedLinkedString = null;
			sortedLinkedDouble = null;
		}

		@Test
		public void testAddToEnd() {
			try {
				sortedLinkedString.addToEnd("Dr");
				assertTrue("Did not throw an UnsupportedOperationException", false);
			}
			catch (UnsupportedOperationException e)
			{
				assertTrue("Successfully threw an UnsupportedOperationException", true);
			}
			catch (Exception e)
			{
				assertTrue("Threw an exception other than the UnsupportedOperationException", false);
			}
		}

		@Test
		public void testAddToFront() {
			try {
				sortedLinkedString.addToFront("Doofensmurz");
				assertTrue("Did not throw an UnsupportedOperationException", false);
			}
			catch (UnsupportedOperationException e)
			{
				assertTrue("Successfully threw an UnsupportedOperationException", true);
			}
			catch (Exception e)
			{
				assertTrue("Threw an exception other than the UnsupportedOperationException", false);
			}
		}

		@Test
		public void testIteratorSuccessfulNext() {
			sortedLinkedString.add("D");
			sortedLinkedString.add("H");
			sortedLinkedString.add("A");
			ListIterator<String> iterator = sortedLinkedString.iterator();
			assertEquals(true, iterator.hasNext());
			assertEquals("A", iterator.next());
			assertEquals("D", iterator.next());
			assertEquals("H", iterator.next());
			assertEquals(false, iterator.hasNext());
		}

		@Test
		public void testIteratorSuccessfulStringPrevious() {
			sortedLinkedString.add("E");
			sortedLinkedString.add("D");
			sortedLinkedString.add("H");
			sortedLinkedString.add("O");
			ListIterator<String> iterator = sortedLinkedString.iterator();
			assertEquals(true, iterator.hasNext());
			assertEquals("D", iterator.next());
			assertEquals("E", iterator.next());
			assertEquals("H", iterator.next());
			assertEquals("O", iterator.next());
			assertEquals(true, iterator.hasPrevious());
			assertEquals("O", iterator.previous());
			assertEquals("H", iterator.previous());
			assertEquals("E", iterator.previous());
		}

		@Test
		public void testIteratorSuccessfulDoubleNext() {
			sortedLinkedDouble.add(new Double(9));
			sortedLinkedDouble.add(new Double(22));
			sortedLinkedDouble.add(new Double(1));
			sortedLinkedDouble.add(new Double(5));
			ListIterator<Double> iterator = sortedLinkedDouble.iterator();
			assertEquals(true, iterator.hasNext());
			assertEquals(new Double(1), iterator.next());
			assertEquals(new Double(5), iterator.next());
			assertEquals(new Double(9), iterator.next());
			assertEquals(true, iterator.hasNext());	}
		
		@Test
		public void testIteratorSuccessfulDoublePrevious() {
			sortedLinkedDouble.add(new Double(85));
			sortedLinkedDouble.add(new Double(10));
			sortedLinkedDouble.add(new Double(8));
			sortedLinkedDouble.add(new Double(2));
			ListIterator<Double> iterator = sortedLinkedDouble.iterator();
			assertEquals(new Double(2), iterator.next());
			assertEquals(new Double(8), iterator.next());
			assertEquals(new Double(10), iterator.next());
			assertEquals(true, iterator.hasPrevious());
			assertEquals(new Double(10), iterator.previous());
			assertEquals(true, iterator.hasPrevious());
		}
		
		
		@Test
		public void testAddDouble() {
			sortedLinkedDouble.add(new Double(85.0));
			sortedLinkedDouble.add(new Double(10.0));
			sortedLinkedDouble.add(new Double(2.22));
			sortedLinkedDouble.add(new Double(2.0));
			assertEquals((Double) 2.0, sortedLinkedDouble.getFirst());
			assertEquals((Double) 85.0, sortedLinkedDouble.getLast());
		}

		private class StringComparator implements Comparator<String>
		{

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return arg0.compareTo(arg1);
			}
			
		}
		
		private class DoubleComparator implements Comparator<Double>
		{

			@Override
			public int compare(Double arg0, Double arg1) {
				// TODO Auto-generated method stub
				return arg0.compareTo(arg1);
			}
			
		}
		
}
