/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author sara.garcia
 */
public class ClassInstances {
    
    private String classInstance;
    private String comment;

    public ClassInstances(String classInstance, String comment){
        this.classInstance=classInstance;
        this.comment=comment;
    }
    
    public ClassInstances(String classInstance){
        this.classInstance=classInstance;
    }
    
    public ClassInstances(){
        this.classInstance=null;
        this.comment=null;
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
}
