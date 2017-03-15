package tools;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Мария on 13.03.2017.
 */
public class CClass {
  //FIXED - class ATTRIBUTE added!!!
    private List<Attribute> classAtributes;
    private HashMap<String, String> classMethods; //name, source code
    private List<Method> methods;
    private String parentClassName;
    private List<String> classesUsed;
    private List<Stirng> childrenClasses;
    private String className;
    private List<Attribute> publicAttribute = null;
    private List<Methods> publicMethods = null;

    public void setClassName(String name) {
      this.className = name;
    }

    public String getClassName() {
      return this.className;
    }

    public void setChildrenClasses(List<String> children) {
      this.childrenClasses = children;
    }

    public List<String> getChildrenClasses() {
      return this.childrenClasses;
    }

    public List<String> getClassesUsed() {
        return classesUsed;
    }

    public void setClassesUsed(List<String> classesUsed) {
        this.classesUsed = classesUsed;
    }


    public List<Attribute> getClassAtributes() {
        return classAtributes;
    }

    public List<Attribute> getPublicClassAtributes() {
      if (publicAttribute == null) {
        publicAttribute = new LinkedList<>();
        for (Attribute a : this.classAtributes) {
          if (a.getAcessSpec() == Keywords.PUBLIC) {
            publicAttribute.add(a);
          }
        }
      }
      return publicAttribute;
    }

    public List<Attribute> getPublicClassMethods() {
      if (publicMethods == null) {
        publicMethods = new LinkedList<>();
        for (Methods m : this.classMethods) {
          if (a.getAcessSpec() == Keywords.PUBLIC) {
            publicMethods.add(a);
          }
        }
      }
      return publicMethods;
    }

    public void setClassAtributes(List<Attribute> classAtributes) {
        this.classAtributes = classAtributes;
    }

    public List<Method> getMethods() {
        if(methods.isEmpty()){
            parseMetods();
        }
        return methods;
    }

    public void setClassMethods(HashMap<String, String> classMethods) {
        this.classMethods = classMethods;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    private void parseMetods(){
        MethodParser methodParser = new MethodParser();
        for(String methodSourse : classMethods.values()){
            methods.add(methodParser.parseMethod(methodSourse));
        }
    }
}
