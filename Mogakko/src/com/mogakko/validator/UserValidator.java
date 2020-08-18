package com.mogakko.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mogakko.beans.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean) target;
		
		String beanName = errors.getObjectName();
		
		//bean의 이름이 joinUserBean일 겨우, 유효성 검사하고, 그렇지 않으면 하지 않는다.
		if(beanName.equals("joinUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
			
			if(userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}			
		}		
	}

}
