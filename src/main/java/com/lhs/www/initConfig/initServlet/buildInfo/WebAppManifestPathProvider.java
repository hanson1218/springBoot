package com.lhs.www.initConfig.initServlet.buildInfo;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

public class WebAppManifestPathProvider implements ManifestPathProvider {
//	private static final Logger LOGGER = LoggerFactory.getLogger(WebAppManifestPathProvider.class);
    private static final String MANIFEST_PATH = "/META-INF/MANIFEST.MF";
    private final ServletContext context;

    public WebAppManifestPathProvider(ServletContext context) {
        this.context = context;
    }

    public URL getManifestToRead() throws MalformedURLException {
        URL result = this.context.getResource("/META-INF/MANIFEST.MF");
        if(result != null) {
//            LOGGER.info("manifest path from servlet context is {}", result.toString());
        }

        return result;
    }
}
