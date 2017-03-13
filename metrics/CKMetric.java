package metrics;

import interfaces.IMetric;
import interfaces.IMetricResult;
import tools.CClass;
import tools.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Мария on 13.03.2017.
 */
public class CKMetric implements IMetric {
    HashMap<String, CClass> classes;
    String className;

    //A method count for a class
    int weightedMethodPerClass(){
        return classes.get(className).getMethods().size();
    }

    //A maximum inheritance path from the given class to its root class
    int depthOfInheritanceTree(String className){
        String parent = classes.get(className).getParentClassName();
        if(parent.isEmpty())
            return 0;
        else
            return depthOfInheritanceTree(parent) + 1;
    }

    //A number of immediate sub-classes of a class
    int numberOfChildren(){
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
    int couplingBetwwenObjectClasses(){
        return classes.get(className).getClassesUsed().size();
    }

    /*
    // The response set of a class is a set of methods that can potentially
    // be executed in response to a message received by an object of that
    // class.
    */
    int responceOfClass(){
        List<Method> methods = classes.get(className).getMethods();
        int count = methods.size();
        for(Method method : methods){
            count += method.getMethodsUsed().size();
        }
        return count;
    }

    /*
    // The number of pairs of methods in a class that don't have at least one
    // field in common minus the number of pairs of methods in the class that
    // do share at least one field.
     */
    int lackOfCohessionMethods(){
        return 0;
    }

    @Override
    public IMetricResult calculateMetric(String className, HashMap<String, CClass> classes) {
        this.classes = classes;
        this.className = className;
        return null;
    }
}
