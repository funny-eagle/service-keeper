package org.nocoder.servicekeeper.application.service;

import org.nocoder.servicekeeper.application.dto.DeploymentLogDto;

/**
 * @author jason
 * @date 2019/4/25.
 */
public interface DeploymentLogService {
    void add(DeploymentLogDto dto);
}
