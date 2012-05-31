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
package com.opensymphony.xwork2.validator.metadata;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <code>FieldExpressionValidatorDescription</code>
 *
 * @author Rainer Hermanns
 * @version $Id: FieldExpressionValidatorDescription.java 894090 2009-12-27 18:18:29Z martinc $
 */
public class FieldExpressionValidatorDescription extends AbstractFieldValidatorDescription {

    public String expression;
    public String key;
    public String message;
    public boolean shortCircuit;

    public FieldExpressionValidatorDescription() {
    }

    /**
     * Creates an AbstractFieldValidatorDescription with the specified field name.
     *
     * @param fieldName
     */
    public FieldExpressionValidatorDescription(String fieldName) {
        super(fieldName);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setShortCircuit(boolean shortCircuit) {
        this.shortCircuit = shortCircuit;
    }


    /**
     * Returns the validator XML definition.
     *
     * @return the validator XML definition.
     */
    @Override
    public String asFieldXml() {
        StringWriter sw = new StringWriter();
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(sw);

            if ( shortCircuit) {
                writer.println("\t\t<field-validator type=\"fieldexpression\">");
            } else {
                writer.println("\t\t<field-validator type=\"fieldexpression\" short-circuit=\"true\">");
            }

            writer.println("\t\t\t<param name=\"expression\">" + expression+ "</param>");

            if ( !"".equals(key)) {
                writer.println("\t\t\t<message key=\"" + key + "\">" + message + "</message>");
            } else {
                writer.println("\t\t\t<message>" + message + "</message>");
            }

            writer.println("\t\t</field-validator>");

        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        return sw.toString();
    }

    /**
     * Returns the validator XML definition.
     *
     * @return the validator XML definition.
     */
    @Override
    public String asSimpleXml() {
        throw new UnsupportedOperationException(getClass().getName() + " cannot be used for simple validators...");
    }

}
