// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:07
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1;

import java.io.PrintStream;

public class XmlPullParserException extends Exception {

   protected Throwable detail;
   protected int row = -1;
   protected int column = -1;


   public XmlPullParserException(String var1) {
      super(var1);
   }

   public XmlPullParserException(String var1, XmlPullParser var2, Throwable var3) {
      super((var1 == null?"":var1 + " ") + (var2 == null?"":"(position:" + var2.getPositionDescription() + ") ") + (var3 == null?"":"caused by: " + var3));
      if(var2 != null) {
         this.row = var2.getLineNumber();
         this.column = var2.getColumnNumber();
      }

      this.detail = var3;
   }

   public Throwable getDetail() {
      return this.detail;
   }

   public int getLineNumber() {
      return this.row;
   }

   public int getColumnNumber() {
      return this.column;
   }

   public void printStackTrace() {
      if(this.detail == null) {
         super.printStackTrace();
      } else {
         PrintStream var1 = System.err;
         synchronized(System.err) {
            System.err.println(super.getMessage() + "; nested exception is:");
            this.detail.printStackTrace();
         }
      }

   }
}
