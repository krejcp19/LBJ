package gui.components;

import org.apache.commons.lang3.StringUtils;

public class LBJLowerCaseStringValidator implements LBJValidator<String> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.isAllLowerCase(value);
	}

}
