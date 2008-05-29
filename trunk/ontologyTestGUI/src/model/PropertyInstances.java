/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author sara.garcia
 */
public class PropertyInstances {
    
    private String propertyInstance;
    private String comment;

    public PropertyInstances(String propertyInstance, String comment){
        this.propertyInstance=propertyInstance;
        this.comment=comment;
    }
    
    public PropertyInstances(String propertyInstance){
        this.propertyInstance=propertyInstance;
    }
    
   public PropertyInstances(){
        this.propertyInstance=null;
        this.comment=null;
    }
    
    public String getPropertyInstance() {
        return propertyInstance;
    }

    public void setPropertyInstance(String propertyInstance) {
        this.propertyInstance = propertyInstance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
