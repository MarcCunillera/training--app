package com.rgb.training.app.common.rest;

import jakarta.annotation.Priority;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *
 * @author LuisCarlosGonzalez
 */
@SecurityKeyAuth
@Provider
@Priority(value = 100)
public class SecurityKeyAuthorizer implements ContainerRequestFilter {

    public static final String AUTHENTICATION_HEADER = "Authorization";
    public static final String AUTH_KEY = "3U5BdXDUH43yqWwgz7QzYQTd1ePUmM5QCnEqRlrBkYqtkKNK0zxR0DGfc87Zau4i";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Boolean authorized = Boolean.FALSE;
        String authToken = requestContext.getHeaderString(AUTHENTICATION_HEADER);
        if (requestContext.getRequest().getMethod().equals("OPTIONS") || requestContext.getRequest().getMethod().equals("HEAD")) {
            requestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }
        if (authToken != null) {
            authorized = AUTH_KEY.equals(authToken);
        } else {
            authorized = Boolean.FALSE;
        }
        if (Boolean.FALSE.equals(authorized)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
