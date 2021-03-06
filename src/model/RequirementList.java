package model;

import java.util.ArrayList;

public class RequirementList {

	private ArrayList<Requirement> requirements;

	public RequirementList( ) {
		requirements = new ArrayList<>( );
	}

	public ArrayList<Requirement> getRequirements( ) {
		return requirements;
	}

	public Requirement getRequirementByName(String requirementName) {
		for ( Requirement requirement : requirements ) {
			if ( requirement.getName( ).equals(requirementName) ) {
				return requirement;
			}
		}
		return null;
	}

	public RequirementList getPriorityList( ) {
		RequirementList priorityList = new RequirementList( );
		for ( Requirement requirement : requirements ) {
			if ( requirement.getPriority( ) > 0 ) {
				priorityList.addRequirement(requirement);
			}
		}
		return priorityList;
	}

	public void addRequirement(Requirement requirement) {
		requirements.add(requirement);
	}

	public void removeRequirement(String requirementName) {
		for ( Requirement requirement : requirements ) {
			if ( requirement.getName( ).equalsIgnoreCase(requirementName) ) {
				requirements.remove(requirement);
				break;
			}
		}
	}

	public ArrayList<Requirement> getFinishedRequirements( ) {
		ArrayList<Requirement> returnArrayList = new ArrayList<>( );
		for ( Requirement requirement : requirements
		) {
			if ( requirement.getStatus( ).equalsIgnoreCase("Finished") )
				returnArrayList.add(requirement);
		}
		return returnArrayList;
	}
}
