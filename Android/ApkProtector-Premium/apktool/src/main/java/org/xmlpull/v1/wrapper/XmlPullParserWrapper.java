// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:03
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1.wrapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public interface XmlPullParserWrapper extends XmlPullParser {

   String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";
   String XSD_NS = "http://www.w3.org/2001/XMLSchema";


   String getAttributeValue(String var1);

   String getPITarget() throws IllegalStateException;

   String getPIData() throws IllegalStateException;

   String getRequiredAttributeValue(String var1) throws IOException, XmlPullParserException;

   String getRequiredAttributeValue(String var1, String var2) throws IOException, XmlPullParserException;

   String getRequiredElementText(String var1, String var2) throws IOException, XmlPullParserException;

   boolean isNil() throws IOException, XmlPullParserException;

   boolean matches(int var1, String var2, String var3) throws XmlPullParserException;

   void nextStartTag() throws XmlPullParserException, IOException;

   void nextStartTag(String var1) throws XmlPullParserException, IOException;

   void nextStartTag(String var1, String var2) throws XmlPullParserException, IOException;

   void nextEndTag() throws XmlPullParserException, IOException;

   void nextEndTag(String var1) throws XmlPullParserException, IOException;

   void nextEndTag(String var1, String var2) throws XmlPullParserException, IOException;

   String nextText(String var1, String var2) throws IOException, XmlPullParserException;

   void skipSubTree() throws XmlPullParserException, IOException;
}
