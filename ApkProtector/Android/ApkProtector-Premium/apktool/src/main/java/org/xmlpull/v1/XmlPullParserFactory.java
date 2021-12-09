// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:09:03
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.xmlpull.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class XmlPullParserFactory {

   static final Class referenceContextClass;
   public static final String PROPERTY_NAME = "org.xmlpull.v1.XmlPullParserFactory";
   private static final String RESOURCE_NAME = "/META-INF/services/org.xmlpull.v1.XmlPullParserFactory";
   protected ArrayList parserClasses;
   protected String classNamesLocation;
   protected ArrayList serializerClasses;
   protected HashMap features = new HashMap();


   public void setFeature(String var1, boolean var2) throws XmlPullParserException {
      this.features.put(var1, new Boolean(var2));
   }

   public boolean getFeature(String var1) {
      Boolean var2 = (Boolean)this.features.get(var1);
      return var2 != null?var2.booleanValue():false;
   }

   public void setNamespaceAware(boolean var1) {
      this.features.put("http://xmlpull.org/v1/doc/features.html#process-namespaces", new Boolean(var1));
   }

   public boolean isNamespaceAware() {
      return this.getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
   }

   public void setValidating(boolean var1) {
      this.features.put("http://xmlpull.org/v1/doc/features.html#validation", new Boolean(var1));
   }

   public boolean isValidating() {
      return this.getFeature("http://xmlpull.org/v1/doc/features.html#validation");
   }

   public XmlPullParser newPullParser() throws XmlPullParserException {
      if(this.parserClasses == null) {
         throw new XmlPullParserException("工厂初始化不完整 " + this.classNamesLocation);
      } else if(this.parserClasses.size() == 0) {
         throw new XmlPullParserException("在 " + this.classNamesLocation + " 没有找到适合的解析器");
      } else {
         StringBuffer var1 = new StringBuffer();
         int var2 = 0;

         while(var2 < this.parserClasses.size()) {
            Class var3 = (Class)this.parserClasses.get(var2);

            try {
               XmlPullParser var4 = (XmlPullParser)var3.newInstance();
               Iterator var5 = this.features.keySet().iterator();

               while(var5.hasNext()) {
                  String var6 = (String)var5.next();
                  Boolean var7 = (Boolean)this.features.get(var6);
                  if(var7 != null && var7.booleanValue()) {
                     var4.setFeature(var6, true);
                  }
               }

               return var4;
            } catch (Exception var8) {
               var1.append(var3.getName() + ": " + var8.toString() + "; ");
               ++var2;
            }
         }

         throw new XmlPullParserException("无法创建解析器: " + var1);
      }
   }

   public XmlSerializer newSerializer() throws XmlPullParserException {
      if(this.serializerClasses == null) {
         throw new XmlPullParserException("工厂初始化不完整 " + this.classNamesLocation);
      } else if(this.serializerClasses.size() == 0) {
         throw new XmlPullParserException("在 " + this.classNamesLocation + " 没有找到适合的序列化程序");
      } else {
         StringBuffer var1 = new StringBuffer();
         int var2 = 0;

         while(var2 < this.serializerClasses.size()) {
            Class var3 = (Class)this.serializerClasses.get(var2);

            try {
               XmlSerializer var4 = (XmlSerializer)var3.newInstance();
               return var4;
            } catch (Exception var5) {
               var1.append(var3.getName() + ": " + var5.toString() + "; ");
               ++var2;
            }
         }

         throw new XmlPullParserException("无法创建序列化程序: " + var1);
      }
   }

   public static XmlPullParserFactory newInstance() throws XmlPullParserException {
      return newInstance((String)null, (Class)null);
   }

   public static XmlPullParserFactory newInstance(String var0, Class var1) throws XmlPullParserException {
      if(var1 == null) {
         var1 = referenceContextClass;
      }

      var0 = "org.kxml2.io.KXmlParser,org.kxml2.io.KXmlSerializer";
      XmlPullParserFactory var2 = null;
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();

      int var6;
      for(int var5 = 0; var5 < var0.length(); var5 = var6 + 1) {
         var6 = var0.indexOf(44, var5);
         if(var6 == -1) {
            var6 = var0.length();
         }

         String var7 = var0.substring(var5, var6);
         Class var8 = null;
         Object var9 = null;

         try {
            var8 = Class.forName(var7);
            var9 = var8.newInstance();
         } catch (Exception var11) {
            ;
         }

         if(var8 != null) {
            boolean var10 = false;
            if(var9 instanceof XmlPullParser) {
               var3.add(var8);
               var10 = true;
            }

            if(var9 instanceof XmlSerializer) {
               var4.add(var8);
               var10 = true;
            }

            if(var9 instanceof XmlPullParserFactory) {
               if(var2 == null) {
                  var2 = (XmlPullParserFactory)var9;
               }

               var10 = true;
            }

            if(!var10) {
               throw new XmlPullParserException("不兼容的类: " + var7);
            }
         }
      }

      if(var2 == null) {
         var2 = new XmlPullParserFactory();
      }

      var2.parserClasses = var3;
      var2.serializerClasses = var4;
      var2.classNamesLocation = "org.kxml2.io.kXmlParser,org.kxml2.io.KXmlSerializer";
      return var2;
   }

   static {
      XmlPullParserFactory var0 = new XmlPullParserFactory();
      referenceContextClass = var0.getClass();
   }
}
