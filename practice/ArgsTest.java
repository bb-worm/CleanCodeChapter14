package practice;

import java.text.ParseException;
import junit.framework.TestCase;

public class ArgsTest extends TestCase {
	
	public void testWithNoSchemaButWithOneArgument() throws Exception {
		Args args = new Args("", new String[] {"-x"});
		assertEquals(args.errorMessage(), "Argument(s) -x unexpected");
	}
	
	public void testWithNoSchemaButWithMultipleArguments() throws Exception {
		Args args = new Args("", new String[] {"-x", "-y"});
		assertEquals(args.errorMessage(), "Argument(s) -xy unexpected");
	}
	
	public void testInvalidArgumentFormat() throws Exception {
		try {
			new Args("*", new String[] {});
			fail("Args constructor should have throws exception");
		} catch (ParseException e) {
			assertEquals("Bad character: * in Args format: *", e.getMessage());
		}
	}
	
	public void testSimpleBooleanPresent() throws Exception {
		Args args = new Args("x", new String[] {"-x"});
		assertEquals(true, args.getBoolean('x'));
	}
	
	public void testSimpleStringPresent() throws Exception {
		Args args = new Args("x*", new String[] {"-x", "param"});
		assertTrue(args.has('x'));
		assertEquals("param", args.getString('x'));
	}
	
	public void testMissingStringArgument() throws Exception {
		Args args = new Args("x*", new String[] {"-x"});
		assertEquals(args.errorMessage(), "Could not find string parameter for -x.");
	}
	
	public void testSpaceInFormat() throws Exception {
		Args args = new Args("x, y", new String[] {"-xy"});
		assertTrue(args.has('x'));
		assertTrue(args.has('y'));
	}
}