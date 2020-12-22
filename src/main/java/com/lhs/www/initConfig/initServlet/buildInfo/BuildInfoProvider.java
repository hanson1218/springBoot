package com.lhs.www.initConfig.initServlet.buildInfo;

public interface BuildInfoProvider extends CurrentVersionProvider {
	String getImplementationVersion();

    String getApplicationVersion();

    String getApplicationName();

    String getBuildTime();

    String getBranch();
}
