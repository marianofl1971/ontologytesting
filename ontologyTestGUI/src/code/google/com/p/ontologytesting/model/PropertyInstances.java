/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

/**
 *
 * @author sara.garcia
 */
public class PropertyInstances {
    
    private String propertyInstance="";
    private String comment="";

    public PropertyInstances(String propertyInstance, String comment){
        this.propertyInstance=propertyInstance;
        this.comment=comment;
    }
    
    public PropertyInstances(String propertyInstance){
        this.propertyInstance=propertyInstance;
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
    
    @Override
    public boolean equals(Object object){
        if((object!=null) && (object instanceof PropertyInstances) ) {
            PropertyInstances comp = (PropertyInstances)object;
            if(this.getPropertyInstance().equals(comp.getPropertyInstance())){
               if(this.getComment().equals(comp.getComment())){
                    return true;
               }
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.propertyInstance != null ? this.propertyInstance.hashCode() : 0);
        hash = 37 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        return hash;
    }
}
