/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

/**
 *
 * @author sara.garcia
 */
public class ClassInstances {
    
    private String classInstance="";
    private String comment="";

    public ClassInstances(String classInstance, String comment){
        this.classInstance=classInstance;
        this.comment=comment;
    }
    
    public ClassInstances(String classInstance){
        this.classInstance=classInstance;
    }
    
    public ClassInstances(){
        this.classInstance="";
        this.comment="";
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
