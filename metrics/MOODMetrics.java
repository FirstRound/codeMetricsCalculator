package metrics;

import interfaces.IMetric;
import interfaces.IMetricResult;
import constants.Keywords;

public class MOODMetrics implements IMetric {

  private HashMap<String, CClass> classes;
  private String className;

  private double calculateAHF() {
    int tc = classes.size();
    int numberOfMembers = 0;
    double generalSumm = 0;
    for (Map.Entry<String, CClass> entry : classes.entrySet()) {
        numberOfMembers += entry.getValue().getClassAtributes().size();
        for (Attribute at : entry.getValue().getPublicClassAtributes()) {
          //kostili
          if (at.getAcessSpec() != Keywords.PRIVATE) {
            continue;
          }
          int classDepth = 1;
          CClass curClass = entry.getValue();
          Queue<CClass> queue = new LinkedList<>();
          queue.add(curClass);
          while (!queue.isEmpty()) {
            classDepth++;
            curClass = (CClass)queue.remove();
            for (CClass c : curClass.getChildrenClasses()) {
              queue.add(c);
            }
          }
          generalSumm += 1 - (classDepth/(tc-1));
        }
    }

    return generalSumm/numberOfMembers;

  }

  private double calculateMHF() {
    int tc = classes.size();
    int numberOfMethods = 0;
    double generalSumm = 0;
    for (Map.Entry<String, CClass> entry : classes.entrySet()) {
        numberOfMembers += entry.getValue().getClassMethods().size();
        for (Methods me : entry.getValue().getPublicClassMethods()) {
          //kostili
          int classDepth = 1;
          CClass curClass = entry.getValue();
          Queue<CClass> queue = new LinkedList<>();
          queue.add(curClass);
          while (!queue.isEmpty()) {
            classDepth++;
            curClass = (CClass)queue.remove();
            for (CClass c : curClass.getChildrenClasses()) {
              queue.add(c);
            }
          }
          generalSumm += 1 - (classDepth/(tc-1));
        }
    }

    return generalSumm/numberOfMembers;
  }

  public double calculateAIF() {
    int maxAttr = 0;
    int inheritedAttr = 0;
    for (Map.Entry<String, CClass> entry : classes.entrySet()) {
      maxAttr += entry.getValue().getPublicClassAtributes() + classes.get(entry.getValue().getClassName()).getPublicClassAtributes();
      inheritedAttr = classes.get(entry.getValue().getClassName()).getPublicClassAtributes();

    }
    return inheritedAttr/maxAttr;
  }

  public double calculateMIF() {
    int maxMeth = 0;
    int inheritedMeth = 0;
    int override = 0;
    for (Map.Entry<String, CClass> entry : classes.entrySet()) {
      maxMeth += entry.getValue().getPublicClassMethods() + classes.get(entry.getValue().getClassName()).getPublicClassMethods();
      for (Method a: classes.get(entry.getValue().getClassName()).getPublicClassMethods()) {
        for (Method b: entry.getValue().getPublicClassMethods()) {
          if ((a.getName()).equals(b.getName())) {
            override++;
          }
        }
      }
      inheritedMeth = classes.get(entry.getValue().getClassName()).getPublicClassAtributes() - override;
    }
    return inheritedMeth/maxMeth;
  }

  @Override
  public IMetricResult calculateMetric(String className, HashMap<String, CClass> classes) {
    this.classes = classes;
    this.className = className;

    return null;
  }

}
