package com.javalabs.data;
import com.javalabs.model.AccessPolicyConstraintModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessPolicyConstraintData implements AccessPolicyConstraintModel {
    
	private static final long serialVersionUID = 1L;
	private String role;
    private String name;
    private String referredType = "accessRole";
}