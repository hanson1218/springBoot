package com.lhs.www.initConfig.initServlet.buildInfo;

import java.net.MalformedURLException;
import java.net.URL;

public interface ManifestPathProvider {
	URL getManifestToRead() throws MalformedURLException;
}
