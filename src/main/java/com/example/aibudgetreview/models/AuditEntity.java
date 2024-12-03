package com.example.aibudgetreview.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.sql.Timestamp;  // Importujemy java.sql.Timestamp
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AuditEntity {

    /**
     * Data utworzenia rekordu
     */
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    /**
     * Data ostatniej aktualizacji rekordu
     */
    @Column(name = "updated_at", insertable = false)
    private Timestamp updatedAt;

    @PrePersist
    void onCreate() {
        this.setCreatedAt(new Timestamp((new Date()).getTime()));
    }

    @PreUpdate
    void onPersist() {
        this.setUpdatedAt(new Timestamp((new Date()).getTime()));
    }
}
