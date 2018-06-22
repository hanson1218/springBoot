package com.lhs.www.initConfig.initServlet.buildInfo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuildInfoServlet extends HttpServlet{
	public static final long serialVersionUID = 1L;
    private static final String JSON_RESPONSE = "{ \"version\": \"%s\", \"buildTime\": \"%s\", \"branch\": \"%s\", \"applicationVersion\": \"%s\", \"name\": \"%s\" }";
    private BuildInfoProvider versionProvider;

    public BuildInfoServlet() {
    }

    public BuildInfoServlet(BuildInfoProvider versionProvider) {
        this.versionProvider = versionProvider;
    }

    protected BuildInfoProvider getVersionProvider() {
        if(this.versionProvider == null) {
            this.versionProvider = new ManifestBasedVersionProvider(new WebAppManifestPathProvider(this.getServletContext()), new ManifestReader());
        }

        return this.versionProvider;
    }

    protected String getJsonResponse() {
        return String.format("{ \"version\": \"%s\", \"buildTime\": \"%s\", \"branch\": \"%s\", \"applicationVersion\": \"%s\", \"name\": \"%s\" }", new Object[]{this.getVersionProvider().get(), this.versionProvider.getBuildTime(), this.versionProvider.getBranch(), this.versionProvider.getApplicationVersion(), this.versionProvider.getApplicationName()});
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
        response.setStatus(200);
        response.getWriter().write(this.getJsonResponse());
    }
}
