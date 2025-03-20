package ru.taheoport.survey_manager.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "SURVEY_STATION", indexes = {
        @Index(name = "IDX_SURVEY_STATION_BASE", columnList = "BASE_ID"),
        @Index(name = "IDX_SURVEY_STATION_LANDMARK", columnList = "LANDMARK_ID")
})
@Entity
public class SurveyStation {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "TOOL_HEIGHT", nullable = false)
    @NotNull
    private String toolHeight;

    @NotNull
    @Column(name = "LANDMARK_DIRECTION")
    private String landmarkDirection;

    @JoinColumn(name = "BASE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private BasePoint base;

    @JoinColumn(name = "LANDMARK_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private BasePoint landmark;

    @Composition
    @OneToMany(mappedBy = "surveyStation")
    private List<Picket> pickets;

    public List<Picket> getPickets() {
        return pickets;
    }

    public void setPickets(List<Picket> pickets) {
        this.pickets = pickets;
    }

    public BasePoint getLandmark() {
        return landmark;
    }

    public void setLandmark(BasePoint landmark) {
        this.landmark = landmark;
    }

    public BasePoint getBase() {
        return base;
    }

    public void setBase(BasePoint base) {
        this.base = base;
    }

    public String getLandmarkDirection() {
        return landmarkDirection;
    }

    public void setLandmarkDirection(String landmarkDirection) {
        this.landmarkDirection = landmarkDirection;
    }

    public String getToolHeight() {
        return toolHeight;
    }

    public void setToolHeight(String toolHeight) {
        this.toolHeight = toolHeight;
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