package com.nnk.springboot.annotations;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import com.ibm.icu.impl.locale.XCldrStub.Joiner;

/**
 * Class permettant de valider un password 
 * @author maure
 *
 */
public class PasswordConstraintsValidator implements ConstraintValidator<ValidatorPassword, String> {

	
	@Override
    public void initialize(ValidatorPassword arg0) {
    }
	/**
	 * @param password le mot de passe 
	 * @param context Le context de Validation du mot de passe 
	 */
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		LengthRule lengthRule = new LengthRule();
		lengthRule.setMinimumLength(8);
		PasswordValidator validator = new PasswordValidator(Arrays.asList(
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				lengthRule,
				new CharacterRule(EnglishCharacterData.Special,1),
				new CharacterRule(EnglishCharacterData.Digit)
				));
		PasswordData passwordData= new PasswordData(password);
		RuleResult  result = validator.validate(passwordData);
		if(result.isValid()) {
			return true;
		}
		 context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate(
	          Joiner.on(",").join(validator.getMessages(result)))
	          .addConstraintViolation();
		return false;
	}

}
