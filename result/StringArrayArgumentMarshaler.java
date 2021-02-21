package result;
import static result.ArgsException.ErrorCode.*;

import java.util.*;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
	private List<String> stringArray = new ArrayList<String>();
	
	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			stringArray.add(currentArgument.next());
		} catch (NoSuchElementException e) {
			throw new ArgsException(MISSING_STRING);
		}
	}
	
	public static String[] getValue(ArgumentMarshaler am) {
		if (am != null && am instanceof StringArrayArgumentMarshaler)
			return ((StringArrayArgumentMarshaler) am).stringArray.toArray(new String[0]);
		else
			return new String[0];
	}
}
