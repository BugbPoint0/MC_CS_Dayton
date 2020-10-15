import static org.junit.Assert.*;

	import java.util.ArrayList;
	import java.util.Comparator;
	import java.util.Iterator;
	import java.util.ListIterator;
	import java.util.NoSuchElementException;

	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;


/**
 * 
 * @author David Dayton
 *
 */
public class BasicDoubleLinkedList_STUDENT_Test {
	
		BasicDoubleLinkedList<String> linkedString;
		BasicDoubleLinkedList<Double> linkedDouble;
		StringComparator comparator;
		DoubleComparator comparatorD;

		@Before
		public void setUp() throws Exception {
			linkedString = new BasicDoubleLinkedList<String>();
			linkedString.addToEnd("Agent");
			linkedString.addToEnd("P");
			comparator = new StringComparator();
			
			linkedDouble = new BasicDoubleLinkedList<Double>();
			linkedDouble.addToEnd(15.0);
			linkedDouble.addToEnd(100.0);
			comparatorD = new DoubleComparator();
			

		}

		@After
		public void tearDown() throws Exception {
			linkedString = null;
			linkedDouble = null;
			comparatorD = null;
			comparator = null;
		}

		@Test
		public void testGetSize() {
			assertEquals(2,linkedString.getSize());
			assertEquals(2,linkedDouble.getSize());
		}
		
		@Test
		public void testAddToEnd() {
			assertEquals("P", linkedString.getLast());
			linkedString.addToEnd("End");
			assertEquals("End", linkedString.getLast());
		}
		
		@Test
		public void testAddToFront() {
			assertEquals("Agent", linkedString.getFirst());
			linkedString.addToFront("Special");
			assertEquals("Special", linkedString.getFirst());
		}
		
		@Test
		public void testGetFirst() {
			assertEquals("Agent", linkedString.getFirst());
			linkedString.addToFront("Special");
			assertEquals("Special", linkedString.getFirst());
		}

		@Test
		public void testGetLast() {
			assertEquals("P", linkedString.getLast());
			linkedString.addToEnd("LAST");
			assertEquals("LAST", linkedString.getLast());
		}
		
		@Test
		public void testToArrayList()
		{
			ArrayList<String> list;
			linkedString.addToFront("Special");
			linkedString.addToFront("Super");
			list = linkedString.toArrayList();
			assertEquals("Super",list.get(0));
			assertEquals("Special",list.get(1));
			assertEquals("Agent",list.get(2));
			assertEquals("P",list.get(3));
		}
		
		@Test
		public void testIteratorSuccessfulNext() {
			linkedString.addToFront("Special");
			linkedString.addToEnd("!");
			ListIterator<String> iterator = linkedString.iterator();
			assertEquals(true, iterator.hasNext());
			assertEquals("Special", iterator.next());
			assertEquals("Agent", iterator.next());
			assertEquals("P", iterator.next());
			assertEquals(true, iterator.hasNext());
			assertEquals("!", iterator.next());

		}
		
		@Test
		public void testIteratorSuccessfulPrevious() {
			linkedString.addToFront("Special");
			linkedString.addToEnd("!");
			ListIterator<String> iterator = linkedString.iterator();
			assertEquals(true, iterator.hasNext());
			assertEquals("Special", iterator.next());
			assertEquals("Agent", iterator.next());
			assertEquals("P", iterator.next());
			assertEquals("!", iterator.next());
			assertEquals(true, iterator.hasPrevious());
			assertEquals("!", iterator.previous());
			assertEquals("P", iterator.previous());
			assertEquals("Agent", iterator.previous());
			assertEquals("Special", iterator.previous());
		}
		
		
		@Test
		public void testIteratorNoSuchElementExceptionPrevious() {
			linkedString.addToFront("Special");
			linkedString.addToEnd("!");
			ListIterator<String> iterator = linkedString.iterator();
			assertEquals(true, iterator.hasNext());
			assertEquals("Special", iterator.next());
			assertEquals("Agent", iterator.next());
			assertEquals("P", iterator.next());
			assertEquals("!", iterator.next());
			assertEquals(true, iterator.hasPrevious());
			assertEquals("!", iterator.previous());
			assertEquals("P", iterator.previous());
			assertEquals("Agent", iterator.previous());
			assertEquals("Special", iterator.previous());
			
			try{
				//no more elements in list
				iterator.previous();
				assertTrue("Did not throw a NoSuchElementException",false);
			}
			catch (NoSuchElementException e)
			{
				assertTrue("Successfully threw a NoSuchElementException",true);
			}
			catch (Exception e)
			{
				assertTrue("Threw an exception other than the NoSuchElementException", false);
			}
			
		}
		
		@Test
		public void testIteratorUnsupportedOperationException() {
			ListIterator<String> iterator = linkedString.iterator();
			assertEquals(true, iterator.hasNext());
			
			try{
				//remove is not supported for the iterator
				iterator.remove();
				assertTrue("Did not throw a UnsupportedOperationException",false);
			}
			catch (UnsupportedOperationException e)
			{
				assertTrue("Successfully threw a UnsupportedOperationException",true);
			}
			catch (Exception e)
			{
				assertTrue("Threw an exception other than the UnsupportedOperationException", false);
			}
			
		}
		
		@Test
		public void testRemove() {
			// remove the first
			assertEquals("Agent", linkedString.getFirst());
			assertEquals("P", linkedString.getLast());
			linkedString.addToFront("Special");
			assertEquals("Special", linkedString.getFirst());
			linkedString.remove("Special", comparator);
			assertEquals("Agent", linkedString.getFirst());
		}

		@Test
		public void testRetrieveFirstElement() {
			assertEquals("Agent", linkedString.getFirst());
			linkedString.addToFront("Special");
			assertEquals("Special", linkedString.getFirst());
			assertEquals("Special", linkedString.retrieveFirstElement());
			assertEquals("Agent",linkedString.getFirst());
			assertEquals("Agent", linkedString.retrieveFirstElement());
			assertEquals("P",linkedString.getFirst());
			
		}

		@Test
		public void testRetrieveLastElement() {
			assertEquals("P", linkedString.getLast());
			linkedString.addToEnd("!");
			assertEquals("!", linkedString.getLast());
			assertEquals("!", linkedString.retrieveLastElement());
			assertEquals("P",linkedString.getLast());
		}

		private class StringComparator implements Comparator<String> {

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return arg0.compareTo(arg1);
			}
			
		}
		
		private class DoubleComparator implements Comparator<Double> {

			@Override
			public int compare(Double arg0, Double arg1) {
				// TODO Auto-generated method stub
				return arg0.compareTo(arg1);
			}
			
		}
	
}
