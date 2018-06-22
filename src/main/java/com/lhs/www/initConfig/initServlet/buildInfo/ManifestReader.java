package com.lhs.www.initConfig.initServlet.buildInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.Manifest;

public class ManifestReader {
	 public ManifestReader() {
	    }

	    public Attributes readAttributes(URL url) throws IOException {
//	        Validate.notNull(url);
	        Manifest manifest = this.readManifest(url);
	        Attributes attributes = manifest.getMainAttributes();
	        this.print(attributes);
	        return attributes;
	    }

	    protected Manifest readManifest(URL url) throws IOException {
	        if(url == null) {
	            throw new IllegalArgumentException("url is required");
	        } else {
	            InputStream inputStream = url.openStream();

	            Manifest var3;
	            try {
	                var3 = new Manifest(inputStream);
	            } finally {
	                inputStream.close();
	            }

	            return var3;
	        }
	    }

	    private void print(Attributes attributes) {
	        if(attributes != null) {
	            Iterator i$ = attributes.keySet().iterator();

	            while(i$.hasNext()) {
	                Object o = i$.next();
	                Name attrName = (Name)o;
	                String attrValue = attributes.getValue(attrName);
//	                LOGGER.debug(String.format("Read manifest attribute %s = %s", new Object[]{attrName, attrValue}));
	            }
	        }

	    }

	    public String readValue(Attributes attributes, String name) {
//	        Validate.notNull(attributes, "Please invoke read(URL) before calling getAttribute(name) method");
	        return attributes.getValue(name);
	    }
}
