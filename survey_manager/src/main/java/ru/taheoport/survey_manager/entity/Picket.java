package ru.taheoport.survey_manager.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "PICKET", indexes = {
        @Index(name = "IDX_PICKET_SURVEY_STATION", columnList = "SURVEY_STATION_ID")
})
@Entity
public class Picket {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "DIRECTION", nullable = false)
    @NotNull
    private String direction;

    @Column(name = "DISTANCE", nullable = false)
    @NotNull
    private String distance;

    @Column(name = "TILT_ANGLE", nullable = false)
    @NotNull
    private String tiltAngle;
    @Column(name = "TARGET_HEIGHT")
    private String targetHeight;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "SURVEY_STATION_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SurveyStation surveyStation;

    public SurveyStation getSurveyStation() {
        return surveyStation;
    }

    public void setSurveyStation(SurveyStation surveyStation) {
        this.surveyStation = surveyStation;
    }

    public String getTargetHeight() {
        return targetHeight;
    }

    public void setTargetHeight(String targetHeight) {
        this.targetHeight = targetHeight;
    }

    public String getTiltAngle() {
        return tiltAngle;
    }

    public void setTiltAngle(String tiltAngle) {
        this.tiltAngle = tiltAngle;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}