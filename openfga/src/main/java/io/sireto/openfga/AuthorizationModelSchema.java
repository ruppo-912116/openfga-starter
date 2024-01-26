package io.sireto.openfga;

import java.util.List;

import java.util.List;

public class AuthorizationModelSchema {
    private String schema_version;
    private List<TypeDefinition> type_definitions;

    // Getter and Setter methods
}

class TypeDefinition {
    private String type;
    private Relations relations;
    private Metadata metadata;

    // Getter and Setter methods
}

class Relations {
    private Relation reader;
    private Relation writer;
    private Relation owner;

    // Getter and Setter methods
}

class Relation {
    private ThisRelation thisRelation;

    // Getter and Setter methods
}

class ThisRelation {
    // This class can be empty or include properties specific to the relation
}

class Metadata {
    private Relations metadataRelations;

    // Getter and Setter methods
}

class MetadataRelations {
    private DirectlyRelatedUserType reader;
    private DirectlyRelatedUserType writer;
    private DirectlyRelatedUserType owner;

    // Getter and Setter methods
}

class DirectlyRelatedUserType {
    private List<UserType> directly_related_user_types;

    // Getter and Setter methods
}

class UserType {
    private String type;

    // Getter and Setter methods
}

