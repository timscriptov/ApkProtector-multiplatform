// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:05
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1.wrapper.classic;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.wrapper.XmlPullParserWrapper;
import org.xmlpull.v1.wrapper.XmlPullWrapperFactory;
import org.xmlpull.v1.wrapper.XmlSerializerWrapper;

import java.io.IOException;
import java.io.StringReader;

public class StaticXmlSerializerWrapper extends XmlSerializerDelegate implements XmlSerializerWrapper {

   private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/features.html#xmldecl-standalone";
   private static final boolean TRACE_SIZING = false;
   protected String currentNs;
   protected XmlPullWrapperFactory wf;
   protected XmlPullParserWrapper fragmentParser;
   protected int namespaceEnd = 0;
   protected String[] namespacePrefix = new String[8];
   protected String[] namespaceUri;
   protected int[] namespaceDepth;


   public StaticXmlSerializerWrapper(XmlSerializer var1, XmlPullWrapperFactory var2) {
      super(var1);
      this.namespaceUri = new String[this.namespacePrefix.length];
      this.namespaceDepth = new int[this.namespacePrefix.length];
      this.wf = var2;
   }

   public String getCurrentNamespaceForElements() {
      return this.currentNs;
   }

   public String setCurrentNamespaceForElements(String var1) {
      String var2 = this.currentNs;
      this.currentNs = var1;
      return var2;
   }

   public XmlSerializerWrapper attribute(String var1, String var2) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.attribute((String)null, var1, var2);
      return this;
   }

   public XmlSerializerWrapper startTag(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.startTag(this.currentNs, var1);
      return this;
   }

   public XmlSerializerWrapper endTag(String var1) throws IOException, IllegalArgumentException, IllegalStateException {
      this.endTag(this.currentNs, var1);
      return this;
   }

   public XmlSerializerWrapper element(String var1, String var2) throws IOException, XmlPullParserException {
      return this.element(this.currentNs, var1, var2);
   }

   public XmlSerializerWrapper element(String var1, String var2, String var3) throws IOException, XmlPullParserException {
      if(var2 == null) {
         throw new XmlPullParserException("name for element can not be null");
      } else {
         this.xs.startTag(var1, var2);
         if(var3 == null) {
            this.xs.attribute("http://www.w3.org/2001/XMLSchema-instance", "nil", "true");
         } else {
            this.xs.text(var3);
         }

         this.xs.endTag(var1, var2);
         return this;
      }
   }

   private void ensureNamespacesCapacity() {
      int var1 = this.namespaceEnd > 7?2 * this.namespaceEnd:8;
      String[] var2 = new String[var1];
      String[] var3 = new String[var1];
      int[] var4 = new int[var1];
      if(this.namespacePrefix != null) {
         System.arraycopy(this.namespacePrefix, 0, var2, 0, this.namespaceEnd);
         System.arraycopy(this.namespaceUri, 0, var3, 0, this.namespaceEnd);
         System.arraycopy(this.namespaceDepth, 0, var4, 0, this.namespaceEnd);
      }

      this.namespacePrefix = var2;
      this.namespaceUri = var3;
      this.namespaceDepth = var4;
   }

   public void setPrefix(String var1, String var2) throws IOException, IllegalArgumentException, IllegalStateException {
      this.xs.setPrefix(var1, var2);
      int var3 = this.getDepth();

      for(int var4 = this.namespaceEnd - 1; var4 >= 0 && this.namespaceDepth[var4] > var3; --var4) {
         --this.namespaceEnd;
      }

      if(this.namespaceEnd >= this.namespacePrefix.length) {
         this.ensureNamespacesCapacity();
      }

      this.namespacePrefix[this.namespaceEnd] = var1;
      this.namespaceUri[this.namespaceEnd] = var2;
      ++this.namespaceEnd;
   }

   public void fragment(String var1) throws IOException, IllegalArgumentException, IllegalStateException, XmlPullParserException {
      StringBuffer var2 = new StringBuffer(var1.length() + this.namespaceEnd * 30);
      var2.append("<fragment");
      int var3 = this.namespaceEnd - 1;

      while(var3 >= 0) {
         String var4 = this.namespacePrefix[var3];
         int var5 = this.namespaceEnd - 1;

         while(true) {
            if(var5 > var3) {
               if(!var4.equals(this.namespacePrefix[var5])) {
                  --var5;
                  continue;
               }
            } else {
               var2.append(" xmlns");
               if(var4.length() > 0) {
                  var2.append(':').append(var4);
               }

               var2.append("=\'");
               var2.append(this.escapeAttributeValue(this.namespaceUri[var3]));
               var2.append("\'");
            }

            --var3;
            break;
         }
      }

      var2.append(">");
      var2.append(var1);
      var2.append("</fragment>");
      if(this.fragmentParser == null) {
         this.fragmentParser = this.wf.newPullParserWrapper();
      }

      String var6 = var2.toString();
      this.fragmentParser.setInput(new StringReader(var6));
      this.fragmentParser.nextTag();
      this.fragmentParser.require(2, (String)null, "fragment");

      while(true) {
         this.fragmentParser.nextToken();
         if(this.fragmentParser.getDepth() == 1 && this.fragmentParser.getEventType() == 3) {
            this.fragmentParser.require(3, (String)null, "fragment");
            return;
         }

         this.event(this.fragmentParser);
      }
   }

   public void event(XmlPullParser var1) throws XmlPullParserException, IOException {
      int var2 = var1.getEventType();
      switch(var2) {
      case 0:
         Boolean var3 = (Boolean)var1.getProperty("http://xmlpull.org/v1/doc/features.html#xmldecl-standalone");
         this.startDocument(var1.getInputEncoding(), var3);
         break;
      case 1:
         this.endDocument();
         break;
      case 2:
         this.writeStartTag(var1);
         break;
      case 3:
         this.endTag(var1.getNamespace(), var1.getName());
         break;
      case 4:
         if(var1.getDepth() > 0) {
            this.text(var1.getText());
         } else {
            this.ignorableWhitespace(var1.getText());
         }
         break;
      case 5:
         this.cdsect(var1.getText());
         break;
      case 6:
         this.entityRef(var1.getName());
         break;
      case 7:
         String var4 = var1.getText();
         this.ignorableWhitespace(var4);
         break;
      case 8:
         this.processingInstruction(var1.getText());
         break;
      case 9:
         this.comment(var1.getText());
         break;
      case 10:
         this.docdecl(var1.getText());
      }

   }

   private void writeStartTag(XmlPullParser var1) throws XmlPullParserException, IOException {
      int var2;
      if(!var1.getFeature("http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes")) {
         var2 = var1.getNamespaceCount(var1.getDepth() - 1);
         int var3 = var1.getNamespaceCount(var1.getDepth());

         for(int var4 = var2; var4 < var3; ++var4) {
            String var5 = var1.getNamespacePrefix(var4);
            String var6 = var1.getNamespaceUri(var4);
            this.setPrefix(var5, var6);
         }
      }

      this.startTag(var1.getNamespace(), var1.getName());

      for(var2 = 0; var2 < var1.getAttributeCount(); ++var2) {
         this.attribute(var1.getAttributeNamespace(var2), var1.getAttributeName(var2), var1.getAttributeValue(var2));
      }

   }

   public String escapeAttributeValue(String var1) {
      int var2 = var1.indexOf(60);
      int var3 = var1.indexOf(38);
      int var4 = var1.indexOf(34);
      int var5 = var1.indexOf(39);
      if(var2 == -1 && var3 == -1 && var4 == -1 && var5 == -1) {
         return var1;
      } else {
         StringBuffer var6 = new StringBuffer(var1.length() + 10);
         int var7 = 0;

         for(int var8 = var1.length(); var7 < var8; ++var7) {
            char var9 = var1.charAt(var7);
            switch(var9) {
            case 34:
               var6.append("&quot;");
               break;
            case 38:
               var6.append("&amp;");
               break;
            case 39:
               var6.append("&apos;");
               break;
            case 60:
               var6.append("&lt;");
               break;
            default:
               var6.append(var9);
            }
         }

         return var6.toString();
      }
   }

   public String escapeText(String var1) {
      int var2 = var1.indexOf(60);
      int var3 = var1.indexOf(38);
      if(var2 == -1 && var3 == -1) {
         return var1;
      } else {
         StringBuffer var4 = new StringBuffer(var1.length() + 10);
         int var5 = 0;

         while(true) {
            while(var2 != -1 || var3 != -1) {
               if(var2 != -1 && (var2 == -1 || var3 == -1 || var3 >= var2)) {
                  if(var3 != -1 && (var2 == -1 || var3 == -1 || var2 >= var3)) {
                     throw new IllegalStateException("wrong state posLt=" + var2 + " posAmp=" + var3 + " for " + var1);
                  }

                  if(var5 < var2) {
                     var4.append(var1.substring(var5, var2));
                  }

                  var4.append("&lt;");
                  var5 = var2 + 1;
                  var2 = var1.indexOf(60, var5);
               } else {
                  if(var5 < var3) {
                     var4.append(var1.substring(var5, var3));
                  }

                  var4.append("&amp;");
                  var5 = var3 + 1;
                  var3 = var1.indexOf(38, var5);
               }
            }

            var4.append(var1.substring(var5));
            return var4.toString();
         }
      }
   }

   public void writeDouble(double var1) throws XmlPullParserException, IOException, IllegalArgumentException {
      if(var1 == Double.POSITIVE_INFINITY) {
         this.xs.text("INF");
      } else if(var1 == Double.NEGATIVE_INFINITY) {
         this.xs.text("-INF");
      } else {
         this.xs.text(Double.toString(var1));
      }

   }

   public void writeFloat(float var1) throws XmlPullParserException, IOException, IllegalArgumentException {
      if(var1 == Float.POSITIVE_INFINITY) {
         this.xs.text("INF");
      } else if(var1 == Float.NEGATIVE_INFINITY) {
         this.xs.text("-INF");
      } else {
         this.xs.text(Float.toString(var1));
      }

   }

   public void writeInt(int var1) throws XmlPullParserException, IOException, IllegalArgumentException {
      this.xs.text(Integer.toString(var1));
   }

   public void writeString(String var1) throws XmlPullParserException, IOException, IllegalArgumentException {
      if(var1 == null) {
         throw new IllegalArgumentException("null string can not be written");
      } else {
         this.xs.text(var1);
      }
   }

   public void writeDoubleElement(String var1, String var2, double var3) throws XmlPullParserException, IOException, IllegalArgumentException {
      this.xs.startTag(var1, var2);
      this.writeDouble(var3);
      this.xs.endTag(var1, var2);
   }

   public void writeFloatElement(String var1, String var2, float var3) throws XmlPullParserException, IOException, IllegalArgumentException {
      this.xs.startTag(var1, var2);
      this.writeFloat(var3);
      this.xs.endTag(var1, var2);
   }

   public void writeIntElement(String var1, String var2, int var3) throws XmlPullParserException, IOException, IllegalArgumentException {
      this.xs.startTag(var1, var2);
      this.writeInt(var3);
      this.xs.endTag(var1, var2);
   }

   public void writeStringElement(String var1, String var2, String var3) throws XmlPullParserException, IOException, IllegalArgumentException {
      this.xs.startTag(var1, var2);
      if(var3 == null) {
         this.xs.attribute("http://www.w3.org/2001/XMLSchema", "nil", "true");
      } else {
         this.writeString(var3);
      }

      this.xs.endTag(var1, var2);
   }
}
