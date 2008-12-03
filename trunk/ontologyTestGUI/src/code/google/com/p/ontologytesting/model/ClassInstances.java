/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.io.Serializable;

/**
 *
 * @author sara.garcia
 */
public class ClassInstances implements Serializable{
    
    private String classInstance="";
    private String comment="";

    public ClassInstances(String classInstance, String comment){
        this.classInstance=classInstance;
        this.comment=comment;
    }
    
    public ClassInstances(ClassInstances clasInstan){
        this.classInstance=clasInstan.getClassInstance();
        this.comment=clasInstan.getComment();
    }
    
    public ClassInstances(){
        this("","");
    }
    
    public ClassInstances(String classInstance){
        this.classInstance=classInstance;
    }

    public String getClassInstance() {
        return classInstance;
    }

    public void setClassInstance(String classInstance) {
        this.classInstance = classInstance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    @Override
    public boolean equals(Object object){
        if((object!=null) && (object instanceof ClassInstances) ) {
            ClassInstances comp = (ClassInstances)object;
            if(this.getClassInstance().equals(comp.getClassInstance())){
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
        hash = 79 * hash + (this.classInstance != null ? this.classInstance.hashCode() : 0);
        hash = 79 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        return hash;
    }
}
