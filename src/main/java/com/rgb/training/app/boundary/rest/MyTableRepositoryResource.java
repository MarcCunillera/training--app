package com.rgb.training.app.boundary.rest;

import com.rgb.training.app.data.model.MyTable;
import com.rgb.training.app.data.repository.MyTableRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST Client: http://localhost:8081/training-app/api/mytable/
 *
 * @author LuisCarlosGonzalez
 */
@Path("mytable")
@Produces(MediaType.APPLICATION_JSON)
//@SecurityKeyAuth
//@RestServiceLog
public class MyTableRepositoryResource {

    @Inject
    private MyTableRepository myTableRepo;

    @HEAD
    public Response headDefault() {
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        MyTable result = myTableRepo.get(id);
        return Response.ok(result).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam(value = "offset") Integer offset, @QueryParam(value = "max-results") Integer maxResults) {
        List<MyTable> results = myTableRepo.getAll();
        return Response.ok(results).build();
    }

    @GET
    @Path("secured/all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllSecured(@Context HttpHeaders httpHeaders) {
        boolean authorized = false;
        List<String> authHeaders = httpHeaders.getRequestHeader("Authorization");
        if (authHeaders == null || authHeaders.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        for (String auth : authHeaders) {
            if (auth.equals("1234")) {
                authorized = true;
            }
        }
        if (!authorized) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        List<MyTable> results = myTableRepo.getAll();
        return Response.ok(results).build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(MyTable newEntry) {
        MyTable result = myTableRepo.create(newEntry);
        if (result != null) {
            return Response.status(Response.Status.CREATED).entity(result).build();
        }
        return Response.notModified().build();
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(MyTable updatedEntry) {
        if (myTableRepo.get(updatedEntry.getId()) != null) {
            MyTable result = myTableRepo.update(updatedEntry);
            if (result != null) {
                return Response.ok(result).build();
            }
        }
        return Response.notModified().build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        Long deleted = myTableRepo.delete(id);
        if (deleted > 0) {
            return Response.ok(id).build();
        }
        return Response.notModified().build();
    }
}
