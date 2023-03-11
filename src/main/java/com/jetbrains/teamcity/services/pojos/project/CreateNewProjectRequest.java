package com.jetbrains.teamcity.services.pojos.project;

import lombok.Builder;
import lombok.Data;

import java.util.Locale;

@Builder
public @Data class CreateNewProjectRequest {
    private ParentProject parentProject;
    private String name;
    private String id;
    private boolean copyAllAssociatedSettings;

}
