// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:06
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1.wrapper.classic;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.util.XmlPullUtil;
import org.xmlpull.v1.wrapper.XmlPullParserWrapper;

import java.io.IOException;

public class StaticXmlPullParserWrapper extends XmlPullParserDelegate implements XmlPullParserWrapper {

   public StaticXmlPullParserWrapper(XmlPullParser var1) {
      super(var1);
   }

   public String getAttributeValue(String var1) {
      return XmlPullUtil.getAttributeValue(this.pp, var1);
   }

   public String getRequiredAttributeValue(String var1) throws IOException, XmlPullParserException {
      return XmlPullUtil.getRequiredAttributeValue(this.pp, (String)null, var1);
   }

   public String getRequiredAttributeValue(String var1, String var2) throws IOException, XmlPullParserException {
      return XmlPullUtil.getRequiredAttributeValue(this.pp, var1, var2);
   }

   public String getRequiredElementText(String var1, String var2) throws IOException, XmlPullParserException {
      if(var2 == null) {
         throw new XmlPullParserException("元素名不能为 null");
      } else {
         String var3 = null;
         this.nextStartTag(var1, var2);
         if(this.isNil()) {
            this.nextEndTag(var1, var2);
         } else {
            var3 = this.pp.nextText();
         }

         this.pp.require(3, var1, var2);
         return var3;
      }
   }

   public boolean isNil() throws IOException, XmlPullParserException {
      boolean var1 = false;
      String var2 = this.pp.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
      if("true".equals(var2)) {
         var1 = true;
      }

      return var1;
   }

   public String getPITarget() throws IllegalStateException {
      return XmlPullUtil.getPITarget(this.pp);
   }

   public String getPIData() throws IllegalStateException {
      return XmlPullUtil.getPIData(this.pp);
   }

   public boolean matches(int var1, String var2, String var3) throws XmlPullParserException {
      return XmlPullUtil.matches(this.pp, var1, var2, var3);
   }

   public void nextStartTag() throws XmlPullParserException, IOException {
      if(this.pp.nextTag() != 2) {
         throw new XmlPullParserException("需要 START_TAG 而不是 " + this.pp.getPositionDescription());
      }
   }

   public void nextStartTag(String var1) throws XmlPullParserException, IOException {
      this.pp.nextTag();
      this.pp.require(2, (String)null, var1);
   }

   public void nextStartTag(String var1, String var2) throws XmlPullParserException, IOException {
      this.pp.nextTag();
      this.pp.require(2, var1, var2);
   }

   public void nextEndTag() throws XmlPullParserException, IOException {
      XmlPullUtil.nextEndTag(this.pp);
   }

   public void nextEndTag(String var1) throws XmlPullParserException, IOException {
      XmlPullUtil.nextEndTag(this.pp, (String)null, var1);
   }

   public void nextEndTag(String var1, String var2) throws XmlPullParserException, IOException {
      XmlPullUtil.nextEndTag(this.pp, var1, var2);
   }

   public String nextText(String var1, String var2) throws IOException, XmlPullParserException {
      return XmlPullUtil.nextText(this.pp, var1, var2);
   }

   public void skipSubTree() throws XmlPullParserException, IOException {
      XmlPullUtil.skipSubTree(this.pp);
   }

   public double readDouble() throws XmlPullParserException, IOException {
      String var1 = this.pp.nextText();

      double var2;
      try {
         var2 = Double.parseDouble(var1);
      } catch (NumberFormatException var5) {
         if(!var1.equals("INF") && !var1.toLowerCase().equals("infinity")) {
            if(!var1.equals("-INF") && !var1.toLowerCase().equals("-infinity")) {
               if(!var1.equals("NaN")) {
                  throw new XmlPullParserException("无法解析 double 数值 \'" + var1 + "\'", this, var5);
               }

               var2 = Double.NaN;
            } else {
               var2 = Double.NEGATIVE_INFINITY;
            }
         } else {
            var2 = Double.POSITIVE_INFINITY;
         }
      }

      return var2;
   }

   public float readFloat() throws XmlPullParserException, IOException {
      String var1 = this.pp.nextText();

      float var2;
      try {
         var2 = Float.parseFloat(var1);
      } catch (NumberFormatException var4) {
         if(!var1.equals("INF") && !var1.toLowerCase().equals("infinity")) {
            if(!var1.equals("-INF") && !var1.toLowerCase().equals("-infinity")) {
               if(!var1.equals("NaN")) {
                  throw new XmlPullParserException("无法解析 float 数值 \'" + var1 + "\'", this, var4);
               }

               var2 = Float.NaN;
            } else {
               var2 = Float.NEGATIVE_INFINITY;
            }
         } else {
            var2 = Float.POSITIVE_INFINITY;
         }
      }

      return var2;
   }

   private int parseDigits(String var1, int var2, int var3) throws XmlPullParserException {
      int var4 = 0;
      char var6;
      if(var3 > 9) {
         try {
            var4 = Integer.parseInt(var1.substring(var2, var2 + var3));
         } catch (NumberFormatException var7) {
            throw new XmlPullParserException(var7.getMessage());
         }
      } else {
         for(int var5 = var2 + var3; var2 < var5; var4 = var4 * 10 + (var6 - 48)) {
            var6 = var1.charAt(var2++);
            if(var6 < 48 || var6 > 57) {
               throw new XmlPullParserException("数值中不含小数点", this, (Throwable)null);
            }
         }
      }

      return var4;
   }

   private int parseInt(String var1) throws XmlPullParserException {
      int var2 = 0;
      int var3 = var1.length();
      if(var3 == 0) {
         throw new XmlPullParserException("空的数值", this, (Throwable)null);
      } else {
         boolean var4 = false;
         char var5 = var1.charAt(0);
         if(var5 == 45) {
            if(var3 > 9) {
               try {
                  return Integer.parseInt(var1);
               } catch (NumberFormatException var7) {
                  throw new XmlPullParserException(var7.getMessage(), this, (Throwable)null);
               }
            }

            var4 = true;
            ++var2;
         } else if(var5 == 43) {
            ++var2;
         }

         if(var2 >= var3) {
            throw new XmlPullParserException("错误的数值格式", this, (Throwable)null);
         } else {
            int var6 = this.parseDigits(var1, var2, var3 - var2);
            return var4?-var6:var6;
         }
      }
   }

   public int readInt() throws XmlPullParserException, IOException {
      try {
         int var1 = this.parseInt(this.pp.nextText());
         return var1;
      } catch (NumberFormatException var2) {
         throw new XmlPullParserException("无法解析 int 数值", this, var2);
      }
   }

   public String readString() throws XmlPullParserException, IOException {
      String var1 = this.pp.getAttributeValue("http://www.w3.org/2001/XMLSchema", "nil");
      if("true".equals(var1)) {
         this.nextEndTag();
         return null;
      } else {
         return this.pp.nextText();
      }
   }

   public double readDoubleElement(String var1, String var2) throws XmlPullParserException, IOException {
      this.pp.require(2, var1, var2);
      return this.readDouble();
   }

   public float readFloatElement(String var1, String var2) throws XmlPullParserException, IOException {
      this.pp.require(2, var1, var2);
      return this.readFloat();
   }

   public int readIntElement(String var1, String var2) throws XmlPullParserException, IOException {
      this.pp.require(2, var1, var2);
      return this.readInt();
   }

   public String readStringElemet(String var1, String var2) throws XmlPullParserException, IOException {
      this.pp.require(2, var1, var2);
      return this.readString();
   }
}
