package metrics;

import interfaces.IMetric;
import interfaces.IMetricResult;
import tools.CClass;
import tools.Method;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Мария on 13.03.2017.
 */
public class CKMetric implements IMetric {
    HashMap<String, CClass> classes;
    CClass cclass;

    //A method count for a class
    private int weightedMethodPerClass(){
        return cclass.getMethods().size();
    }

    //A maximum inheritance path from the given class to its root class
    private int depthOfInheritanceTree(String className){
        String parent = classes.get(className).getParentClassName();
        if(parent.isEmpty())
            return 0;
        else
            return depthOfInheritanceTree(parent) + 1;
    }

    //A number of immediate sub-classes of a class
    private int numberOfChildren(){
        int numChildren = 0;
        Set<CClass> c = (Set<CClass>) classes.values();
        for(CClass className : c){
            if(className.getParentClassName().equals(className))
                numChildren++;
        }
        return numChildren;
    }

    /*
    // A number of classes to which a given class is coupled. Two classes
    // are coupled when the methods declared in one class use the methods
    // or instance variables defined by the other class.
    */
    private int couplingBetwwenObjectClasses(){
        return cclass.getClassesUsed().size();
    }

    /*
    // The response set of a class is a set of methods that can potentially
    // be executed in response to a message received by an object of that
    // class.
    */
    private List<String> responceOfClass(){
        List<String> responce = new LinkedList<>();
        List<Method> methods = cclass.getMethods();
        for(Method method : methods){
            if(!responce.contains(method.getName()))
                responce.add(method.getName());
            for(String m : method.getMethodsUsed()){
                if(!responce.contains(m)) responce.add(m);
            }
        }
        return responce;
    }

    /*
    // The number of pairs of methods in a class that don't have at least one
    // field in common minus the number of pairs of methods in the class that
    // do share at least one field.
     */
    private int lackOfCohessionMethods(){
        int shareField = 0;
        int dontShareFild = 0;
        List<Method> m1 = cclass.getMethods();
        for(int i = 0; i < m1.size(); i++){
            for(int j = i+1; j < m1.size(); j++){
                if(haveSameAttributes(m1.get(i), m1.get(j)))
                    shareField++;
                else dontShareFild++;
            }
        }
        return dontShareFild - shareField;
    }

    private boolean haveSameAttributes(Method method1, Method method2) {
        List<String> m1Global = method1.getGlobalFields();
        List<String> m2Global = method2.getGlobalFields();
        for(String field : m2Global){
            if(m1Global.contains(field)){
                return true;
            }
        }
        return false;
    }

    @Override
    public IMetricResult calculateMetric(String className, HashMap<String, CClass> classes) {
        this.classes = classes;
        this.cclass = classes.get(className);
        int weighted = weightedMethodPerClass();
        int depth = depthOfInheritanceTree(className);
        int numChildren = numberOfChildren();
        int coupling = couplingBetwwenObjectClasses();
        List<String> responce = responceOfClass();
        int cohession = lackOfCohessionMethods();
        //TODO: Result
        return null;
    }
}
