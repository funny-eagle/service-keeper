package org.nocoder.servicekeeper.service;

import org.nocoder.servicekeeper.modal.Service;

import java.util.List;

public interface ServiceService {
    Service getById(int id);

    List<Service> getAll();
}
