/*
 * $Id: StrutsBodyTagSupport.java 726715 2008-12-15 15:39:15Z musachy $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.struts2.views.jsp;

import java.io.PrintWriter;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts2.components.Component;
import org.apache.struts2.util.FastByteArrayOutputStream;

import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.util.ValueStack;


/**
 * Contains common functonalities for Struts JSP Tags.
 *
 */
public class StrutsBodyTagSupport extends BodyTagSupport {

    private static final long serialVersionUID = -1201668454354226175L;

    protected ValueStack getStack() {
        return TagUtils.getStack(pageContext);
    }

    protected String findString(String expr) {
        return (String) findValue(expr, String.class);
    }

    protected Object findValue(String expr) {
    	expr = Component.stripExpressionIfAltSyntax(getStack(), expr);

        return getStack().findValue(expr);
    }

    protected Object findValue(String expr, Class toType) {
        if (Component.altSyntax(getStack()) && toType == String.class) {
        	return TextParseUtil.translateVariables('%', expr, getStack());
            //return translateVariables(expr, getStack());
        } else {
        	expr = Component.stripExpressionIfAltSyntax(getStack(), expr);

            return getStack().findValue(expr, toType);
        }
    }

    protected String toString(Throwable t) {
        FastByteArrayOutputStream bout = new FastByteArrayOutputStream();
        PrintWriter wrt = new PrintWriter(bout);
        t.printStackTrace(wrt);
        wrt.close();

        return bout.toString();
    }

    protected String getBody() {
        if (bodyContent == null) {
            return "";
        } else {
            return bodyContent.getString().trim();
        }
    }
}
