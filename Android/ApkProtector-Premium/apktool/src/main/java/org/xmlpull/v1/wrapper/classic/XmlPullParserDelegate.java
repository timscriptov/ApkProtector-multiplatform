// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:07
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1.wrapper.classic;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class XmlPullParserDelegate implements XmlPullParser {

   protected XmlPullParser pp;


   public XmlPullParserDelegate(XmlPullParser var1) {
      this.pp = var1;
   }

   public String getText() {
      return this.pp.getText();
   }

   public void setFeature(String var1, boolean var2) throws XmlPullParserException {
      this.pp.setFeature(var1, var2);
   }

   public char[] getTextCharacters(int[] var1) {
      return this.pp.getTextCharacters(var1);
   }

   public int getColumnNumber() {
      return this.pp.getColumnNumber();
   }

   public int getNamespaceCount(int var1) throws XmlPullParserException {
      return this.pp.getNamespaceCount(var1);
   }

   public String getNamespacePrefix(int var1) throws XmlPullParserException {
      return this.pp.getNamespacePrefix(var1);
   }

   public String getAttributeName(int var1) {
      return this.pp.getAttributeName(var1);
   }

   public String getName() {
      return this.pp.getName();
   }

   public boolean getFeature(String var1) {
      return this.pp.getFeature(var1);
   }

   public String getInputEncoding() {
      return this.pp.getInputEncoding();
   }

   public String getAttributeValue(int var1) {
      return this.pp.getAttributeValue(var1);
   }

   public String getNamespace(String var1) {
      return this.pp.getNamespace(var1);
   }

   public void setInput(Reader var1) throws XmlPullParserException {
      this.pp.setInput(var1);
   }

   public int getLineNumber() {
      return this.pp.getLineNumber();
   }

   public Object getProperty(String var1) {
      return this.pp.getProperty(var1);
   }

   public boolean isEmptyElementTag() throws XmlPullParserException {
      return this.pp.isEmptyElementTag();
   }

   public boolean isAttributeDefault(int var1) {
      return this.pp.isAttributeDefault(var1);
   }

   public String getNamespaceUri(int var1) throws XmlPullParserException {
      return this.pp.getNamespaceUri(var1);
   }

   public int next() throws XmlPullParserException, IOException {
      return this.pp.next();
   }

   public int nextToken() throws XmlPullParserException, IOException {
      return this.pp.nextToken();
   }

   public void defineEntityReplacementText(String var1, String var2) throws XmlPullParserException {
      this.pp.defineEntityReplacementText(var1, var2);
   }

   public int getAttributeCount() {
      return this.pp.getAttributeCount();
   }

   public boolean isWhitespace() throws XmlPullParserException {
      return this.pp.isWhitespace();
   }

   public String getPrefix() {
      return this.pp.getPrefix();
   }

   public void require(int var1, String var2, String var3) throws XmlPullParserException, IOException {
      this.pp.require(var1, var2, var3);
   }

   public String nextText() throws XmlPullParserException, IOException {
      return this.pp.nextText();
   }

   public String getAttributeType(int var1) {
      return this.pp.getAttributeType(var1);
   }

   public int getDepth() {
      return this.pp.getDepth();
   }

   public int nextTag() throws XmlPullParserException, IOException {
      return this.pp.nextTag();
   }

   public int getEventType() throws XmlPullParserException {
      return this.pp.getEventType();
   }

   public String getAttributePrefix(int var1) {
      return this.pp.getAttributePrefix(var1);
   }

   public void setInput(InputStream var1, String var2) throws XmlPullParserException {
      this.pp.setInput(var1, var2);
   }

   public String getAttributeValue(String var1, String var2) {
      return this.pp.getAttributeValue(var1, var2);
   }

   public void setProperty(String var1, Object var2) throws XmlPullParserException {
      this.pp.setProperty(var1, var2);
   }

   public String getPositionDescription() {
      return this.pp.getPositionDescription();
   }

   public String getNamespace() {
      return this.pp.getNamespace();
   }

   public String getAttributeNamespace(int var1) {
      return this.pp.getAttributeNamespace(var1);
   }
}
