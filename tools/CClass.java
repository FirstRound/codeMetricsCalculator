package tools;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Мария on 13.03.2017.
 */
public class CClass {
    private List<String> classAtributes;
    private HashMap<String, String> classMethods; //name, source code
    private List<Method> methods;
    private String parentClassName;
    private List<String> classesUsed;

    public List<String> getClassesUsed() {
        return classesUsed;
    }

    public void setClassesUsed(List<String> classesUsed) {
        this.classesUsed = classesUsed;
    }


    public List<String> getClassAtributes() {
        return classAtributes;
    }

    public void setClassAtributes(List<String> classAtributes) {
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
