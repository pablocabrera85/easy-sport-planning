package org.esp.webservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import org.esp.core.model.Phone;
import org.esp.core.model.User;
import org.esp.core.model.competition.Tournament;
import org.esp.core.model.trainning.Group;
import org.esp.core.model.trainning.Plan;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext> {
    private final JAXBContext context;
    private final Set<Class<?>> types;
    private Class<?>[] ctypes = { User.class,Group.class,Tournament.class,Plan.class,Phone.class}; //your pojo class
    public JAXBContextResolver() throws Exception {
        this.types = new HashSet<Class<?>>(Arrays.asList(ctypes));
        this.context = new JSONJAXBContext(JSONConfiguration.natural().build(),
                ctypes); //json configuration
    }

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        return (types.contains(objectType)) ? context : null;
    }
}
