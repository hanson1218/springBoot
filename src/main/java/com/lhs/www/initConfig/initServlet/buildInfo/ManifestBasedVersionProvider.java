package com.lhs.www.initConfig.initServlet.buildInfo;

import java.net.URL;
import java.util.jar.Attributes;

public class ManifestBasedVersionProvider implements BuildInfoProvider {

//	private static final Logger LOGGER = LoggerFactory.getLogger(ManifestBasedVersionProvider.class);
    private final String implementationVersion;
    private final String applicationVersion;
    private final String applicationName;
    private final String buildTime;
    private final String branch;

    public ManifestBasedVersionProvider(ManifestPathProvider manifestPathProvider, ManifestReader manifestReader) {
//        Validate.notNull(manifestPathProvider);
//        Validate.notNull(manifestReader);
//        LOGGER.info("ManifestBasedVersionProvider initialized with {}", manifestPathProvider.getClass());
        String applicationName = "";
        String implementationVersion = "";
        String applicationVersion = "";
        String buildTime = "";
        String branch = "";

        try {
            URL absoluteDiskPath = manifestPathProvider.getManifestToRead();
            if(absoluteDiskPath == null) {
//                LOGGER.warn("No manifest found so unable to determine current version of the application.");
            } else {
                Attributes attributes = manifestReader.readAttributes(absoluteDiskPath);
                applicationName = manifestReader.readValue(attributes, "Application-Name");
                implementationVersion = manifestReader.readValue(attributes, "Implementation-Version");
                applicationVersion = manifestReader.readValue(attributes, "Application-Version");
                buildTime = manifestReader.readValue(attributes, "Build-Time");
                branch = manifestReader.readValue(attributes, "Branch");
            }
        } catch (Exception var10) {
//            LOGGER.warn("reading manifest to find current version of the application. This may return unexpected results for isActive check.", var10);
        }

        this.implementationVersion = implementationVersion;
        this.applicationVersion = applicationVersion;
        this.applicationName = applicationName;
        this.buildTime = buildTime;
        this.branch = branch;
    }

    public String get() {
        return this.implementationVersion;
    }

    public String getImplementationVersion() {
        return this.implementationVersion;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public String getBuildTime() {
        return this.buildTime;
    }

    public String getBranch() {
        return this.branch;
    }

}
