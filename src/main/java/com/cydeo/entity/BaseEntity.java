package com.cydeo.entity;

import java.time.LocalDateTime;

public class BaseEntity {

    private Long id;
    private LocalDateTime insertDateTime;
    private Long insertUserId;
    private LocalDateTime lastUpdateDateTime;
    private Long lastUpdateUserId;

    public BaseEntity(Long id, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId) {
        this.id = id;
        this.insertDateTime = insertDateTime;
        this.insertUserId = insertUserId;
        this.lastUpdateDateTime = lastUpdateDateTime;
        this.lastUpdateUserId = lastUpdateUserId;
    }
}
