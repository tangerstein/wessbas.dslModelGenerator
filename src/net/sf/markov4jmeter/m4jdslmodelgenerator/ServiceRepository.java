package net.sf.markov4jmeter.m4jdslmodelgenerator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import m4jdsl.M4jdslFactory;
import m4jdsl.Service;

public class ServiceRepository {

    private final M4jdslFactory m4jdslFactory;
    private final List<Service> services;


    public ServiceRepository (final M4jdslFactory m4jdslFactory) {

        this.m4jdslFactory = m4jdslFactory;
        this.services      = new LinkedList<Service>();
    }


    public List<Service> getServices () {

        return this.sortServices(this.services);
    }

    public Service registerServiceByName (final String name) {

        Service service = this.findServiceByName(name);

        if (service != null) {

            return service;
        }

        service = this.m4jdslFactory.createService();
        service.setName(name);

        this.services.add(service);

        return service;
    }

    public Service findServiceByName (final String name) {

        for (final Service service : this.services) {

            if ( name.equals(service.getName()) ) {

                return service;
            }
        }

        return null;  // no match;
    }

    private LinkedList<Service> sortServices (final List<Service> servicesSet) {

        final LinkedList<Service> servicesList =
                new LinkedList<Service>(servicesSet);

        java.util.Collections.sort(servicesList, new Comparator<Service>() {

            @Override
            public int compare (final Service s1, final Service s2) {

                return s1.getName().compareTo(s2.getName());
            }
        });

        return servicesList;
    }
}