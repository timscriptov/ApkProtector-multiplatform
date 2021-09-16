// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:06
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1.wrapper.classic;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class XmlSerializerDelegate implements XmlSerializer {

   protected XmlSerializer xs;


   public XmlSerializerDelegate(XmlSerializer var1) {
      this.xs = var1;
   }

   public String getName() {
      return this.xs.getName();
   }

   public void setPrefix(String var1, String var2) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.setPrefix(var1, var2);
   }

   public void setOutput(OutputStream var1, String var2) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.setOutput(var1, var2);
   }

   public void endDocument() throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.endDocument();
   }

   public void comment(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.comment(var1);
   }

   public int getDepth() {
      return this.xs.getDepth();
   }

   public void setProperty(String var1, Object var2) throws IllegalArgumentException, IllegalStateException {
      this.xs.setProperty(var1, var2);
   }

   public void cdsect(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.cdsect(var1);
   }

   public void setFeature(String var1, boolean var2) throws IllegalArgumentException, IllegalStateException {
      this.xs.setFeature(var1, var2);
   }

   public void entityRef(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.entityRef(var1);
   }

   public void processingInstruction(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.processingInstruction(var1);
   }

   public void setOutput(Writer var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.setOutput(var1);
   }

   public void docdecl(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.docdecl(var1);
   }

   public void flush() throws IOException {
      this.xs.flush();
   }

   public Object getProperty(String var1) {
      return this.xs.getProperty(var1);
   }

   public XmlSerializer startTag(String var1, String var2) throws IOException, IllegalArgumentException, IllegalStateException {
      return this.xs.startTag(var1, var2);
   }

   public void ignorableWhitespace(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.ignorableWhitespace(var1);
   }

   public XmlSerializer text(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      return this.xs.text(var1);
   }

   public boolean getFeature(String var1) {
      return this.xs.getFeature(var1);
   }

   public XmlSerializer attribute(String var1, String var2, String var3) throws IOException, IllegalArgumentException, IllegalStateException {
      return this.xs.attribute(var1, var2, var3);
   }

   public void startDocument(String var1, Boolean var2) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.startDocument(var1, var2);
   }

   public String getPrefix(String var1, boolean var2) throws IllegalArgumentException {
      return this.xs.getPrefix(var1, var2);
   }

   public String getNamespace() {
      return this.xs.getNamespace();
   }

   public XmlSerializer endTag(String var1, String var2) throws IOException, IllegalArgumentException, IllegalStateException {
      return this.xs.endTag(var1, var2);
   }

   public XmlSerializer text(char[] var1, int var2, int var3) throws IOException, IllegalArgumentException, IllegalStateException {
      return this.xs.text(var1, var2, var3);
   }
}
