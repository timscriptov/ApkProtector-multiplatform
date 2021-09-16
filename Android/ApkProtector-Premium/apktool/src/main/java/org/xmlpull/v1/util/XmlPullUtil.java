// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:08
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1.util;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class XmlPullUtil {

   public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";


   public static String getAttributeValue(XmlPullParser var0, String var1) {
      return var0.getAttributeValue("", var1);
   }

   public static String getPITarget(XmlPullParser var0) throws IllegalStateException {
      int var1;
      try {
         var1 = var0.getEventType();
      } catch (XmlPullParserException var4) {
         throw new IllegalStateException("could not determine parser state: " + var4 + var0.getPositionDescription());
      }

      if(var1 != 8) {
         throw new IllegalStateException("parser must be on processing instruction and not " + XmlPullParser.TYPES[var1] + var0.getPositionDescription());
      } else {
         String var2 = var0.getText();

         for(int var3 = 0; var3 < var2.length(); ++var3) {
            if(isS(var2.charAt(var3))) {
               return var2.substring(0, var3);
            }
         }

         return var2;
      }
   }

   public static String getPIData(XmlPullParser var0) throws IllegalStateException {
      int var1;
      try {
         var1 = var0.getEventType();
      } catch (XmlPullParserException var5) {
         throw new IllegalStateException("could not determine parser state: " + var5 + var0.getPositionDescription());
      }

      if(var1 != 8) {
         throw new IllegalStateException("parser must be on processing instruction and not " + XmlPullParser.TYPES[var1] + var0.getPositionDescription());
      } else {
         String var2 = var0.getText();
         int var3 = -1;

         for(int var4 = 0; var4 < var2.length(); ++var4) {
            if(isS(var2.charAt(var4))) {
               var3 = var4;
            } else if(var3 > 0) {
               return var2.substring(var4);
            }
         }

         return "";
      }
   }

   private static boolean isS(char var0) {
      return var0 == 32 || var0 == 10 || var0 == 13 || var0 == 9;
   }

   public static void skipSubTree(XmlPullParser var0) throws XmlPullParserException, IOException {
      var0.require(2, (String)null, (String)null);
      int var1 = 1;

      while(var1 > 0) {
         int var2 = var0.next();
         if(var2 == 3) {
            --var1;
         } else if(var2 == 2) {
            ++var1;
         }
      }

   }

   public static void nextStartTag(XmlPullParser var0) throws XmlPullParserException, IOException {
      if(var0.nextTag() != 2) {
         throw new XmlPullParserException("expected START_TAG and not " + var0.getPositionDescription());
      }
   }

   public static void nextStartTag(XmlPullParser var0, String var1) throws XmlPullParserException, IOException {
      var0.nextTag();
      var0.require(2, (String)null, var1);
   }

   public static void nextStartTag(XmlPullParser var0, String var1, String var2) throws XmlPullParserException, IOException {
      var0.nextTag();
      var0.require(2, var1, var2);
   }

   public static void nextEndTag(XmlPullParser var0, String var1, String var2) throws XmlPullParserException, IOException {
      var0.nextTag();
      var0.require(3, var1, var2);
   }

   public static String nextText(XmlPullParser var0, String var1, String var2) throws IOException, XmlPullParserException {
      if(var2 == null) {
         throw new XmlPullParserException("name for element can not be null");
      } else {
         var0.require(2, var1, var2);
         return var0.nextText();
      }
   }

   public static String getRequiredAttributeValue(XmlPullParser var0, String var1, String var2) throws IOException, XmlPullParserException {
      String var3 = var0.getAttributeValue(var1, var2);
      if(var3 == null) {
         throw new XmlPullParserException("required attribute " + var2 + " is not present");
      } else {
         return var3;
      }
   }

   public static void nextEndTag(XmlPullParser var0) throws XmlPullParserException, IOException {
      if(var0.nextTag() != 3) {
         throw new XmlPullParserException("expected END_TAG and not" + var0.getPositionDescription());
      }
   }

   public static boolean matches(XmlPullParser var0, int var1, String var2, String var3) throws XmlPullParserException {
      boolean var4 = var1 == var0.getEventType() && (var2 == null || var2.equals(var0.getNamespace())) && (var3 == null || var3.equals(var0.getName()));
      return var4;
   }

   public static void writeSimpleElement(XmlSerializer var0, String var1, String var2, String var3) throws IOException, XmlPullParserException {
      if(var2 == null) {
         throw new XmlPullParserException("name for element can not be null");
      } else {
         var0.startTag(var1, var2);
         if(var3 == null) {
            var0.attribute("http://www.w3.org/2001/XMLSchema-instance", "nil", "true");
         } else {
            var0.text(var3);
         }

         var0.endTag(var1, var2);
      }
   }
}
