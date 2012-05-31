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

/**
 * <code>ValidatorDescription</code>
 *
 * @author Rainer Hermanns
 * @version $Id: ValidatorDescription.java 894090 2009-12-27 18:18:29Z martinc $
 */
public interface ValidatorDescription {


    /**
     * Returns the validator XML definition.
     *
     * @return the validator XML definition.
     */
    String asXml();

    /**
     * Returns the field name to create the validation rule for.
     *
     * @return The field name to create the validation rule for
     */
    String getFieldName();

    /**
     * Sets the I18N message key.
     * @param key the I18N message key
     */
    void setKey(String key);

    /**
     * Sets the default validator failure message.
     *
     * @param message the default validator failure message
     */
    void setMessage(String message);

    /**
     * Set the shortCircuit flag.
     *
     * @param shortCircuit the shortCircuit flag.
     */
    void setShortCircuit(boolean shortCircuit);

    boolean isSimpleValidator();
}
