package org.reactome.server.analysis.service.result;

import org.reactome.server.analysis.core.model.PathwayNode;
import org.reactome.server.analysis.core.model.PathwayNodeData;
import org.reactome.server.analysis.core.model.SpeciesNode;

import java.util.Collection;

/**
 * This class is based on PathwayNode but removing the tree hierarchy to store/retrieve the data
 * faster from/to the hard drive.
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class PathwayNodeSummary {
    private String stId;
    private Long pathwayId;
    private String name;
    private SpeciesNode species;
    private boolean llp;
    private PathwayNodeData data;


    public PathwayNodeSummary(PathwayNode node) {
        this.stId = node.getStId();
        this.pathwayId = node.getPathwayId();
        this.name = node.getName();
        this.species = node.getSpecies();
        this.llp = node.isLowerLevelPathway();
        this.data = node.getPathwayNodeData();
    }

    public String getStId() {
        return stId;
    }

    public Long getPathwayId() {
        return pathwayId;
    }

    public String getName() {
        return name;
    }

    public PathwayNodeData getData() {
        return data;
    }

    public SpeciesNode getSpecies() {
        return species;
    }

    public boolean isLlp() {
        return llp;
    }

    public boolean is(String identifier){
        identifier = identifier.trim();
        if(identifier.startsWith("R-")){
            return stId.equals(identifier);
        } else {
            try {
                Long aux = Long.valueOf(identifier);
                return pathwayId.equals(aux);
            } catch (NumberFormatException ex){
                return false;
            }
        }
    }

    public boolean in(Collection<String> identifiers){
        for (String identifier : identifiers) {
            if(is(identifier)) return true;
        }
        return false;
    }
}
