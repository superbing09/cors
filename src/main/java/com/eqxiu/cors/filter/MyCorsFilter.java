package com.eqxiu.cors.filter;

import com.eqxiu.cors.configuration.AutoCorsConfiguration;
import com.thetransactioncompany.cors.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * @author xubing
 * @description //TODO 设计说明
 * @date 19-1-29
 * @copyright 中网易企秀
 */
public class MyCorsFilter implements Filter {

    private AutoCorsConfiguration.MyCorsProperties properties;

    private CORSRequestHandler corsRequestHandler;

    private CORSConfiguration corsConfiguration;

    public MyCorsFilter(AutoCorsConfiguration.MyCorsProperties properties) {
        this.properties = properties;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Properties prop = new Properties();
        prop.put("cors.allowSubdomains", properties.getAllowSubdomains());
        prop.put("cors.allowOrigin", properties.getAllowOrigins());
        try {
            corsConfiguration = new CORSConfiguration(prop);
            corsRequestHandler = new CORSRequestHandler(corsConfiguration);
        } catch (CORSConfigurationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse, filterChain);
        } else {
            throw new ServletException("Connot filter non-HTTP requests/responses");
        }
    }

    private void doFilter(final HttpServletRequest request,
                          final HttpServletResponse response,
                          final FilterChain chain) {
        CORSRequestType corsRequestType = CORSRequestType.detect(request);
        try {
            if (corsRequestType.equals(CORSRequestType.ACTUAL)) {
                corsRequestHandler.handleActualRequest(request, response);
                chain.doFilter(request, response);
            } else if (corsRequestType.equals(CORSRequestType.PREFLIGHT)) {
                corsRequestHandler.handlePreflightRequest(request, response);
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
