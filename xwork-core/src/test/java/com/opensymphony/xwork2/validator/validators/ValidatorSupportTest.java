/*
 * Copyright 2002-2006,2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.opensymphony.xwork2.validator.validators;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.XWorkTestCase;
import com.opensymphony.xwork2.ognl.OgnlValueStack;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;
import com.opensymphony.xwork2.validator.ValidationException;

/**
 * @author tmjee
 * @version $Date: 2009-12-27 19:18:29 +0100 (Sun, 27 Dec 2009) $ $Id: ValidatorSupportTest.java 894090 2009-12-27 18:18:29Z martinc $
 */
public class ValidatorSupportTest extends XWorkTestCase {

	public void testConditionalParseExpression()  throws Exception {
		ValueStack oldStack = ActionContext.getContext().getValueStack();
		try {
			OgnlValueStack stack = (OgnlValueStack) container.getInstance(ValueStackFactory.class).createValueStack();
			stack.getContext().put(ActionContext.CONTAINER, container);
			stack.getContext().put("something", "somevalue");
			ActionContext.getContext().setValueStack(stack);
			ValidatorSupport validator = new ValidatorSupport() {
				public void validate(Object object) throws ValidationException {
				}
			};
            validator.setValueStack(ActionContext.getContext().getValueStack());

            validator.setParse(true);
			String result1 = validator.conditionalParse("${#something}").toString();

			validator.setParse(false);
			String result2 = validator.conditionalParse("${#something}").toString();

			assertEquals(result1, "somevalue");
			assertEquals(result2, "${#something}");
		}
		finally {
			ActionContext.getContext().setValueStack(oldStack);
		}
	}

}
