// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:07
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1.wrapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public interface XmlSerializerWrapper extends XmlSerializer {

   String NO_NAMESPACE = "";
   String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";
   String XSD_NS = "http://www.w3.org/2001/XMLSchema";


   String getCurrentNamespaceForElements();

   String setCurrentNamespaceForElements(String var1);

   XmlSerializerWrapper attribute(String var1, String var2) throws IOException, IllegalArgumentException, IllegalStateException;

   XmlSerializerWrapper startTag(String var1) throws IOException, IllegalArgumentException, IllegalStateException;

   XmlSerializerWrapper endTag(String var1) throws IOException, IllegalArgumentException, IllegalStateException;

   XmlSerializerWrapper element(String var1, String var2, String var3) throws IOException, XmlPullParserException;

   XmlSerializerWrapper element(String var1, String var2) throws IOException, XmlPullParserException;

   void fragment(String var1) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException;

   void event(XmlPullParser var1) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException;

   String escapeText(String var1) throws IllegalArgumentException;

   String escapeAttributeValue(String var1) throws IllegalArgumentException;
}
