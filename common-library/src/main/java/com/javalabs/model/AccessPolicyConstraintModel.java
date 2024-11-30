package com.javalabs.model;
import java.io.Serializable;

public interface AccessPolicyConstraintModel extends Serializable {
    String getRole();

    void setRole(String role);

    String getName();

    void setName(String name);

    String getReferredType();

    void setReferredType(String referredType);
}