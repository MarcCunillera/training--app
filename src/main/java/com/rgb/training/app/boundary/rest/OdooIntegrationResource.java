package com.rgb.training.app.boundary.rest;

import com.rgb.training.app.common.odoo.model.OdooAccount;
import com.rgb.training.app.common.odoo.model.OdooContact;
import com.rgb.training.app.controller.odoo.OdooIntegrationController;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Se exponen métodos para ser utilizados para desarrollo o pruebas internas
 * REST Client: http://localhost:8081/training-app/api/backend/
 *
 * @author LuisCarlosGonzalez
 */
@Path("/backend")
@Produces(MediaType.APPLICATION_JSON)
public class OdooIntegrationResource {

    private OdooIntegrationController odooConnector = new OdooIntegrationController();

    @HEAD
    public Response headDefault() {
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path(value = "/odoo/account")
    @Consumes(MediaType.APPLICATION_JSON)
    public OdooAccount getAccountFromOdoo(@QueryParam("id") Long accountId) {
        OdooAccount result = null;
        try {
            result = odooConnector.getAccount(accountId, Boolean.TRUE);
            if (result == null) {
                result = odooConnector.getAccount(accountId, Boolean.FALSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path(value = "/odoo/contact")
    @Consumes(MediaType.APPLICATION_JSON)
    public OdooContact getContactFromOdoo(@QueryParam("id") Long contactId) {
        OdooContact result = null;
        try {
            result = odooConnector.getContact(contactId, Boolean.TRUE);
            if (result == null) {
                result = odooConnector.getContact(contactId, Boolean.FALSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
