package interfaces;

import tools.CClass;

import java.util.HashMap;

public interface IMetric{
  //public IMetricResult calculateMetric(String mainSourceCode);

  IMetricResult calculateMetric(String className, HashMap<String, CClass> classes);
}
