package gui.validators.shared;

import org.apache.commons.lang3.StringUtils;

import gui.validators.LBJValueValidator;

/**
 * Validates that components {@link String} value is a number
 */
public class LBJNumbersOnlyValidator implements LBJValueValidator<String> {

	@Override
	public boolean isValid(String value) {
		return StringUtils.isNumeric(value);
	}

}
