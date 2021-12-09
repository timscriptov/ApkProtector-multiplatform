// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:07
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1.wrapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.wrapper.classic.StaticXmlPullParserWrapper;
import org.xmlpull.v1.wrapper.classic.StaticXmlSerializerWrapper;

public class XmlPullWrapperFactory {

   private static final boolean DEBUG = false;
   protected XmlPullParserFactory f;


   public static XmlPullWrapperFactory newInstance() throws XmlPullParserException {
      return new XmlPullWrapperFactory((XmlPullParserFactory)null);
   }

   public static XmlPullWrapperFactory newInstance(XmlPullParserFactory var0) throws XmlPullParserException {
      return new XmlPullWrapperFactory(var0);
   }

   public static XmlPullWrapperFactory newInstance(String var0, Class var1) throws XmlPullParserException {
      XmlPullParserFactory var2 = XmlPullParserFactory.newInstance(var0, var1);
      return new XmlPullWrapperFactory(var2);
   }

   protected XmlPullWrapperFactory(XmlPullParserFactory var1) throws XmlPullParserException {
      if(var1 != null) {
         this.f = var1;
      } else {
         this.f = XmlPullParserFactory.newInstance();
      }

   }

   public XmlPullParserFactory getFactory() throws XmlPullParserException {
      return this.f;
   }

   public void setFeature(String var1, boolean var2) throws XmlPullParserException {
      this.f.setFeature(var1, var2);
   }

   public boolean getFeature(String var1) {
      return this.f.getFeature(var1);
   }

   public void setNamespaceAware(boolean var1) {
      this.f.setNamespaceAware(var1);
   }

   public boolean isNamespaceAware() {
      return this.f.isNamespaceAware();
   }

   public void setValidating(boolean var1) {
      this.f.setValidating(var1);
   }

   public boolean isValidating() {
      return this.f.isValidating();
   }

   public XmlPullParserWrapper newPullParserWrapper() throws XmlPullParserException {
      XmlPullParser var1 = this.f.newPullParser();
      return new StaticXmlPullParserWrapper(var1);
   }

   public XmlPullParserWrapper newPullParserWrapper(XmlPullParser var1) throws XmlPullParserException {
      return new StaticXmlPullParserWrapper(var1);
   }

   public XmlSerializerWrapper newSerializerWrapper() throws XmlPullParserException {
      XmlSerializer var1 = this.f.newSerializer();
      return new StaticXmlSerializerWrapper(var1, this);
   }

   public XmlSerializerWrapper newSerializerWrapper(XmlSerializer var1) throws XmlPullParserException {
      return new StaticXmlSerializerWrapper(var1, this);
   }
}
